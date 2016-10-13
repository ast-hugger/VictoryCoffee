package org.newspeaklanguage.compiler.codegen;

import java.util.List;

import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.Method;
import org.newspeaklanguage.compiler.ast.Return;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodGenerator extends CodeGenerator {

  MethodGenerator(ClassGenerator classGenerator, Method methodNode, MethodVisitor methodVisitor) {
    super(classGenerator, methodNode, methodVisitor);
  }

  /**
   * The method-specific version of generating the method body. The value
   * returned by the generated method is self if there is no explicit return.
   */
  @Override
  protected void generateCode() {
    List<AstNode> body = rootNode.body();
    body.forEach(each -> {
      visit(each);
      methodWriter.visitInsn(Opcodes.POP);
    });
    if (body.isEmpty() || !(body.get(body.size() - 1) instanceof Return)) {
      methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
      methodWriter.visitInsn(Opcodes.ARETURN);
    }
  }

}
