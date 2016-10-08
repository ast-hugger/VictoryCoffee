package org.newspeaklanguage.compiler.codegen;

import java.util.List;

import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.runtime.Builtins;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClosureBodyGenerator extends CodeGenerator {
  
  private final MethodGenerator hostMethodGenerator;
  
  ClosureBodyGenerator(MethodGenerator hostMethodGenerator, Block blockNode, MethodVisitor methodVisitor) {
    super(hostMethodGenerator.classGenerator(), blockNode, methodVisitor);
    this.hostMethodGenerator = hostMethodGenerator;
  }

  @Override
  protected MethodGenerator hostMethodGenerator() {
    return hostMethodGenerator;
  }

  @Override
  protected void visitStatements() {
    List<AstNode> body = rootNode.body();
    if (body.isEmpty()) {
      // Empty block: return nil.
      methodWriter.visitFieldInsn(
          Opcodes.GETSTATIC, 
          Builtins.INTERNAL_CLASS_NAME, 
          "NIL", 
          org.newspeaklanguage.runtime.Object.TYPE_DESCRIPTOR);
      return;
    }
    // Don't pop last statement result
    int size = body.size();
    int index = 0;
    for (AstNode each : body) {
      visit(each);
      if (++index != size) {
        methodWriter.visitInsn(Opcodes.POP);
      }
    }
    methodWriter.visitInsn(Opcodes.ARETURN);
  }
}
