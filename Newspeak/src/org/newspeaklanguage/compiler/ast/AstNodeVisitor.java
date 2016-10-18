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

package org.newspeaklanguage.compiler.ast;

public interface AstNodeVisitor {
  
  public void visitArgument(Argument argument);

  public void visitBlock(Block block);

  public void visitCategory(Category category);

  public void visitClassDecl(ClassDecl classDecl);

  public void visitLiteralNumber(LiteralNumber literalNumber);

  public void visitLiteralString(LiteralString literalString);

  public void visitMessagePattern(MessagePattern messagePattern);

  public void visitMessageSendNoReceiver(MessageSendNoReceiver messageSendNoReceiver);

  public void visitMessageSendWithReceiver(MessageSendWithReceiver messageSendWithReceiver);

  public void visitMethod(Method method);

  public void visitSlotDefinition(SlotDefinition slotDefinition);

  public void visitLiteralNil(LiteralNil literalNil);

  public void visitLiteralBoolean(LiteralBoolean literalBoolean);

  public void visitSelf(Self self);

  public void visitSuper(Super super1);

  public void visitOuter(Outer outer);

  public void visitReturn(Return return1);

}
