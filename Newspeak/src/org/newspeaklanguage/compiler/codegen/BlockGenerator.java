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

import java.util.List;

import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.runtime.Builtins;
import org.newspeaklanguage.runtime.NsObject;
import org.objectweb.asm.Label;
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
  protected void generateBody() {
    List<AstNode> body = rootNode.body();
    if (body.isEmpty()) {
      // An empty block: return nil.
      methodWriter.visitInsn(Opcodes.ACONST_NULL);
    } else {
      // A non-empty block. Don't pop the last statement result; leave it on the
      // stack to be returned.
      int size = body.size();
      int index = 0;
      for (AstNode each : body) {
        visit(each);
        if (++index != size) {
          methodWriter.visitInsn(Opcodes.POP2);
        }
      }
      // stack: int, Object
      methodWriter.visitInsn(Opcodes.DUP); // stack: int, Object, Object
      generateLoadUndefined(methodWriter); // stack: int, Object, Object, Undefined
      Label objectPresent = new Label();
      Label end = new Label();
      methodWriter.visitJumpInsn(Opcodes.IF_ACMPNE, objectPresent); // stack: int, Object
      // Object undefined, int is the return value
      prepareToReturnIntFromPairOnStack(methodWriter); // stack: Object
      methodWriter.visitJumpInsn(Opcodes.GOTO, end);
// objectPresent:
      methodWriter.visitLabel(objectPresent); // stack: int, Object
      methodWriter.visitInsn(Opcodes.SWAP); // stack: Object, int
      methodWriter.visitInsn(Opcodes.POP); // stack: Object
// end:
      methodWriter.visitLabel(end);
    }
    methodWriter.visitInsn(Opcodes.ARETURN);
  }

}
