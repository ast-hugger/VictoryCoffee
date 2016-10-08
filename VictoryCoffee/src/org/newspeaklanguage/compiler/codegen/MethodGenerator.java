package org.newspeaklanguage.compiler.codegen;

import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.compiler.ast.Method;
import org.objectweb.asm.MethodVisitor;

public class MethodGenerator extends CodeGenerator {

  private int nextClosureIndex = 0;
  
  MethodGenerator(ClassGenerator classGenerator, Method methodNode, MethodVisitor methodVisitor) {
    super(classGenerator, methodNode, methodVisitor);
  }

  public String nextClosureBodyMethodName() {
    return NamingPolicy.methodNameForClosure(
        ((Method) rootNode).messagePattern().selector(), nextClosureIndex++);
  }
  
  @Override
  protected MethodGenerator hostMethodGenerator() {
    return this;
  }
}
