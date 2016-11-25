/*
 * Copyright (c) 2016 Vassili Bykov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.newspeaklanguage.compiler.codegen;

import org.newspeaklanguage.compiler.Descriptor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.invoke.MethodHandle;
import java.util.LinkedList;
import java.util.List;

/**
 * Responsible for defining and initializing a static field that will hold onto
 * a method handle for a CPS slice of an expression.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
public class CpsSliceDefiner implements StaticFieldDefiner {

  public static List<CpsSliceDefiner> createDefiners(final CpsSlice slice, int expressionIndex, String internalClassName, String hostMethodName) {
    assert slice instanceof CpsSlice.InitialSlice;
    List<CpsSliceDefiner> result = new LinkedList<>();
    CpsSlice here = slice;
    while (here != null) {
      CpsSliceDefiner definer = new CpsSliceDefiner(here, expressionIndex, internalClassName, hostMethodName);
      here.setDefiner(definer);
      result.add(definer);
      here = here.nextSlice();
    }
    return result;
  }

  /*
   *  Instance side
   */

  private final CpsSlice slice;
  private final int expressionIndex;
  private final String internalClassName;
  private final String hostMethodName;
  private final String fieldAndMethodName;

  private CpsSliceDefiner(CpsSlice slice, int expressionIndex, String internalClassName, String hostMethodName) {
    this.slice = slice;
    this.expressionIndex = expressionIndex;
    this.internalClassName = internalClassName;
    this.hostMethodName = hostMethodName;
    this.fieldAndMethodName = generateFieldName();
  }

  public String fieldOrMethodName() {
    return fieldAndMethodName;
  }

  @Override
  public void generateField(ClassWriter classWriter) {
    if (slice instanceof CpsSlice.InitialSlice) {
      return;
    }
    FieldVisitor fieldWriter = classWriter.visitField(
        Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL,
        fieldAndMethodName,
        Descriptor.ofType(MethodHandle.class),
        null, null);
    fieldWriter.visitEnd();
  }

  @Override
  public void generateClinitFragment(MethodVisitor methodWriter) {
    if (slice instanceof CpsSlice.InitialSlice) {
      return;
    }
    methodWriter.visitLdcInsn(new Handle(
        Opcodes.H_INVOKEVIRTUAL,
        internalClassName,
        fieldAndMethodName,
        descriptor(),
        false));
    methodWriter.visitFieldInsn(
        Opcodes.PUTSTATIC,
        internalClassName,
        fieldAndMethodName,
        Descriptor.ofType(MethodHandle.class));
  }

  private String generateFieldName() {
    return hostMethodName + "$slice" + expressionIndex + "_" + slice.index();
  }

  public String descriptor() {
    StringBuilder builder = new StringBuilder(200);
    builder
        .append("(")
        .append(Descriptor.ofType(MethodHandle.class)); // the continuation
    int totalArgCount = slice.arity();
    for (int i = 0; i < totalArgCount; i++ ) {
      builder
          .append(Descriptor.OBJECT_TYPE_DESCRIPTOR)
          .append(Descriptor.INT_TYPE_DESCRIPTOR);
    }
    builder
        .append(")V");
    return builder.toString();
  }


}
