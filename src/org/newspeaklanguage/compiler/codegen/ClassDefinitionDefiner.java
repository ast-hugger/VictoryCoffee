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

import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.runtime.ClassDefinition;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


class ClassDefinitionDefiner implements StaticFieldDefiner {
  
  private final ClassDecl classNode;
  
  ClassDefinitionDefiner(ClassDecl classNode) {
    this.classNode = classNode;
  }

  @Override
  public void generateField(ClassWriter classWriter) {
    FieldVisitor fieldWriter = classWriter.visitField(
        Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL,
        NamingPolicy.CLASS_DEF_FIELD_NAME,
        ClassDefinition.TYPE_DESCRIPTOR,
        null, null);
    fieldWriter.visitEnd();
  }

  @Override
  public void generateClinitFragment(MethodVisitor methodWriter) {
    methodWriter.visitTypeInsn(Opcodes.NEW, ClassDefinition.INTERNAL_CLASS_NAME);
    methodWriter.visitInsn(Opcodes.DUP);
    // ClassDefinition(String name, Class implementationClass)
    methodWriter.visitLdcInsn(classNode.name());
    methodWriter.visitLdcInsn(Type.getType("L" + classNode.implementationClassName() + ";"));
    methodWriter.visitMethodInsn(
        Opcodes.INVOKESPECIAL, 
        ClassDefinition.INTERNAL_CLASS_NAME, 
        "<init>", 
        ClassDefinition.CONSTRUCTOR_DESCRIPTOR, 
        false);
    methodWriter.visitFieldInsn(
        Opcodes.PUTSTATIC,
        classNode.implementationClassName(), 
        NamingPolicy.CLASS_DEF_FIELD_NAME,
        ClassDefinition.TYPE_DESCRIPTOR);
  }

}
