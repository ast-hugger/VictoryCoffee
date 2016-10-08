package org.newspeaklanguage.compiler.codegen;

import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.runtime.ClassDefinition;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


class ClassDefFieldInitializerSnippet implements ClassInitializerSnippet {
  
  private final ClassDecl classNode;
  
  ClassDefFieldInitializerSnippet(ClassDecl classNode) {
    this.classNode = classNode;
  }

  @Override
  public void generateInitializerCode(MethodVisitor methodWriter) {
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
