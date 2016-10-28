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

import org.newspeaklanguage.compiler.Descriptor;
import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.Method;
import org.newspeaklanguage.compiler.ast.Return;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Generates code for a method. In addition to the declared arguments (all doubled
 * in Object/int pairs), the method needs an argument to receive the continuation
 * MethodHandle. The continuation has the type Object * int -> void.
 */
public class MethodGenerator extends CodeGenerator {

  private final String methodName;

  MethodGenerator(ClassGenerator classGenerator, Method methodNode, String methodName, MethodVisitor methodVisitor) {
    super(classGenerator, methodNode, methodVisitor);
    this.methodName = methodName;
  }

  /**
   * The method-specific version of generating the method body. The value
   * returned by the generated method is self if there is no explicit return.
   */
  @Override
  protected void generateBody() {
    int statementIndex = 0;
    boolean seenReturn = false;
    for (AstNode statement : rootNode.body()) {
      AstNode expression;
      boolean inReturnStatement;
      if (statement instanceof Return) {
        seenReturn = true;
        expression = ((Return) statement).expression();
        inReturnStatement = true;
      } else {
        expression = statement;
        inReturnStatement = false;
      }
      List<CpsSlice> slices = CpsMonkey.translate(expression);
      if (slices.isEmpty()) {
        // the expression is simple value-producing one, send it to the current continuation
        if (inReturnStatement) {
          generateLoadCurrentContinuation();
        } else {
          generateLoadTerminatingContinuation(); // or we could even drop the expression entirely!
        }
        visit(expression);
        generateCallContinuationAndReturn(inReturnStatement);
      } else {
        CpsSliceDefiner.createDefiners(slices.get(0), statementIndex, classGenerator.internalClassName(), methodName)
            .forEach(each -> {
              each.generateField(classWriter());
              classGenerator.addStaticFieldDefiner(each);
            });
        for (CpsSlice slice : slices) {
          if (slice instanceof CpsSlice.InitialSlice) {
            // code goes into the same method
            generateSliceCode(slice);
          } else {
            CpsSliceDefiner definer = slice.definer();
            MethodVisitor sliceMethodVisitor = classWriter().visitMethod(
                Opcodes.ACC_PUBLIC,
                definer.fieldOrMethodName(),
                definer.descriptor(),
                null, null);
            MethodGenerator sliceGenerator = new MethodGenerator(classGenerator, (Method) rootNode, methodName, sliceMethodVisitor);
            sliceMethodVisitor.visitCode();
            sliceGenerator.generateSliceCode(slice);
            sliceMethodVisitor.visitInsn(Opcodes.RETURN);
            sliceMethodVisitor.visitMaxs(0, 0);
            sliceMethodVisitor.visitEnd();
          }
        }
      }
      statementIndex++;
    }
    if (!seenReturn) {
      generateReturnSelf();
    } else {
      methodWriter.visitInsn(Opcodes.RETURN);
    }
  }

  private void generateReturnSelf() {
    generateLoadCurrentContinuation();
    methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
    generateLoadInt(methodWriter, 0);
    generateCallContinuationAndReturn(true);
  }

  private void generateLoadCurrentContinuation() {
    methodWriter.visitVarInsn(Opcodes.ALOAD, 1);
  }

  private void generateLoadTerminatingContinuation() {
    methodWriter.visitFieldInsn(
        Opcodes.GETSTATIC,
        CpsMonkey.INTERNAL_CLASS_NAME,
        "TERMINATING_CONTINUATION",
        CpsMonkey.CONTINUATION_DESCRIPTOR
    );
  }

  private void generateCallContinuationAndReturn(boolean andReturn) {
    methodWriter.visitMethodInsn(
        Opcodes.INVOKEVIRTUAL,
        Descriptor.METHOD_HANDLE_INTERNAL_CLASS_NAME,
        "invokeExact",
        Descriptor.ofMethod(void.class, Object.class, int.class),
        false
    );
    if (andReturn) {
      methodWriter.visitInsn(Opcodes.RETURN);
    }
  }
//  protected void generateBody() {
//    List<AstNode> body = rootNode.body();
//    body.forEach(each -> {
//      visit(each);
//      methodWriter.visitInsn(Opcodes.POP2);
//    });
//    if (body.isEmpty() || !(body.get(body.size() - 1) instanceof Return)) {
//      methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
//      methodWriter.visitInsn(Opcodes.ARETURN);
//    }
//  }

}
