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

import java.lang.invoke.MethodHandle;

import org.newspeaklanguage.compiler.Descriptor;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.Method;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * A record created by a {@link CodeGenerator} to keep track of blocks in
 * a method. One is associated with every Block node and used by the
 * generator to produce the associated code.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class BlockDefiner implements StaticFieldDefiner {

  /** The node of the block we are defining. */
  private final Block blockNode;
  /** The method containing the block. */
  private final Method hostMethod;
  private final String internalClassName;
  /** The name of the method we will generate with the code of this block. */
  private final String methodName;
  
  BlockDefiner(Block blockNode, Method hostMethod, String internalClassName, String methodName) {
    this.blockNode = blockNode;
    this.hostMethod = hostMethod;
    this.internalClassName = internalClassName;
    this.methodName = methodName;
  }
  
  public Block blockNode() { return blockNode; }
  public Method hostMethod() { return hostMethod; }
  public String internalClassName() { return internalClassName; }
  public String methodName() { return methodName; }
  public String fieldName() { return methodName; }
  
  public String descriptor() {
    StringBuilder builder = new StringBuilder(200);
    builder.append("(");
    int totalArgCount = blockNode.scope().copiedVariableCount() + blockNode.arity();
    for (int i = 0; i < totalArgCount; i++ ) {
      builder
          .append(Descriptor.INT_TYPE_DESCRIPTOR)
          .append(Descriptor.OBJECT_TYPE_DESCRIPTOR);
    }
    builder
        .append(")")
        .append(Descriptor.OBJECT_TYPE_DESCRIPTOR);
    return builder.toString();
  }

  @Override
  public void generateField(ClassWriter classWriter) {
    FieldVisitor fieldWriter = classWriter.visitField(
        Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
        fieldName(),
        Descriptor.ofType(MethodHandle.class),
        null, null);
    fieldWriter.visitEnd();
  }

  @Override
  public void generateClinitFragment(MethodVisitor methodWriter) {
    methodWriter.visitLdcInsn(new Handle(
        Opcodes.H_INVOKEVIRTUAL,
        internalClassName(),
        methodName(),
        descriptor(),
        false));
    methodWriter.visitFieldInsn(
        Opcodes.PUTSTATIC,
        internalClassName(), 
        fieldName(),
        Descriptor.ofType(MethodHandle.class));
  }
}
