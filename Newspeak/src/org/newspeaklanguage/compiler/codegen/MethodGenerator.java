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
  protected void generateBody() {
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
