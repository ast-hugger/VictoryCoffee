package org.newspeaklanguage.compiler.codegen;

import java.util.List;

import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.semantics.BlockScope;
import org.newspeaklanguage.runtime.Builtins;
import org.newspeaklanguage.runtime.Object;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class BlockGenerator extends CodeGenerator {

  BlockGenerator(ClassGenerator classGenerator, Block blockNode, MethodVisitor methodVisitor) {
    super(classGenerator, blockNode, methodVisitor);
  }

  /**
   * A block is different from a method in that an empty block returns nil
   * rather than self, and a block with no explicit return returns the value of
   * the last expression rather than self.
   */
  @Override
  protected void generateCode() {
    List<AstNode> body = rootNode.body();
    if (body.isEmpty()) {
      // And empty block: return nil.
      methodWriter.visitFieldInsn(
          Opcodes.GETSTATIC,
          Builtins.INTERNAL_CLASS_NAME,
          "NIL",
          org.newspeaklanguage.runtime.Object.TYPE_DESCRIPTOR);
    } else {
      // A non-empty block. Don't pop the last statement result; leave it on the
      // stack to be returned.
      int size = body.size();
      int index = 0;
      for (AstNode each : body) {
        visit(each);
        if (++index != size) {
          methodWriter.visitInsn(Opcodes.POP);
        }
      }
    }
    methodWriter.visitInsn(Opcodes.ARETURN);
  }

}
