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
        Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
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
