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

import org.newspeaklanguage.compiler.ast.Argument;
import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.Category;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.ast.LiteralBoolean;
import org.newspeaklanguage.compiler.ast.LiteralNil;
import org.newspeaklanguage.compiler.ast.LiteralNumber;
import org.newspeaklanguage.compiler.ast.LiteralString;
import org.newspeaklanguage.compiler.ast.MessagePattern;
import org.newspeaklanguage.compiler.ast.MessageSendNoReceiver;
import org.newspeaklanguage.compiler.ast.MessageSendWithReceiver;
import org.newspeaklanguage.compiler.ast.Method;
import org.newspeaklanguage.compiler.ast.Outer;
import org.newspeaklanguage.compiler.ast.Return;
import org.newspeaklanguage.compiler.ast.Self;
import org.newspeaklanguage.compiler.ast.SlotDefinition;
import org.newspeaklanguage.compiler.ast.Super;
import org.newspeaklanguage.compiler.semantics.EnclosingObjectReference;
import org.newspeaklanguage.compiler.semantics.RewrittenNodeVisitor;
import org.newspeaklanguage.compiler.semantics.VariableAssignment;
import org.newspeaklanguage.compiler.semantics.VariableReference;

import java.util.LinkedList;
import java.util.List;

/**
 * A CPS monkey assists CodeGenerator by keeping track of what part of the original expression
 * sliced into its CPS pieces we are in, and arranges for code from the generator to go to the right
 * place.
 * <p>
 * In a serious corporate context the monkey would be called a builder or a manager.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
public class CpsMonkey implements RewrittenNodeVisitor {

  public static CpsMonkey convert(AstNode statement) {
    CpsMonkey instance = new CpsMonkey(statement);
    instance.collectSlices();
    instance.linkSliceArguments();
    return instance;
  }

  /*
   * Instance side
   */

  private final AstNode statement;
  private CpsSlice currentSlice = new CpsSlice.InitialSlice();
  /** Transfers to the calling method the result of visiting an argument of a message send */
  private CpsSlice.OutboundArgument outboundArgument;


  /**
   * Private; use the static utility method.
   */
  private CpsMonkey(AstNode statement) {
    this.statement = statement;
  }

  public CpsSlice currentSlice() {
    return currentSlice;
  }

  public void collectSlices() {
    visit(statement);
    // If the currentSlice is not the initial one, then it's an empty
    // (no terminating node) slice created by the final visit of the
    // topmost message send node in the expression. Logically, the slice
    // stands for the continuation that receives the value of the entire
    // expression, but we have no use for it so we drop it.
    if (!(currentSlice instanceof CpsSlice.InitialSlice)) {
      currentSlice = currentSlice().precedingSlice();
    }
  }

  public void linkSliceArguments() {
    currentSlice.linkArguments(); // cascades down the whole chain
  }

  public String details() {
    StringBuilder builder = new StringBuilder();
    currentSlice.printDetailsTo(builder);
    return builder.toString();
  }

  protected void visit(AstNode node) {
    node.accept(this);
  }

  private void beginNewSlice() {
    CpsSlice newSlice = new CpsSlice(currentSlice);
    currentSlice = newSlice;
  }

  @Override
  public void visitArgument(Argument argument) {
    unexpectedVisit(argument);
  }

  @Override
  public void visitBlock(Block block) {
    unimplemented(block);
  }

  @Override
  public void visitCategory(Category category) {
    unexpectedVisit(category);
  }

  @Override
  public void visitClassDecl(ClassDecl classDecl) {
    unexpectedVisit(classDecl);
  }

  @Override
  public void visitLiteralNumber(LiteralNumber literalNumber) {
    unimplemented(literalNumber);
  }

  @Override
  public void visitLiteralString(LiteralString literalString) {
    unimplemented(literalString);
  }

  @Override
  public void visitMessagePattern(MessagePattern messagePattern) {
    unexpectedVisit(messagePattern);
  }

  @Override
  public void visitMessageSendNoReceiver(MessageSendNoReceiver messageSendNoReceiver) {
    // all these should have been rewritten to regular sends
    unexpectedVisit(messageSendNoReceiver);
  }

  @Override
  public void visitMessageSendWithReceiver(MessageSendWithReceiver node) {
    List<CpsSlice.OutboundArgument> args = new LinkedList<>();
    outboundArgument = null;
    visit(node.receiver());
    assert outboundArgument != null;
    args.add(outboundArgument);
    for (AstNode arg : node.arguments()) {
      outboundArgument = null;
      visit(arg);
      assert outboundArgument != null;
      args.add(outboundArgument);
    }
    currentSlice.setTerminator(node);
    currentSlice.addArgumentHandles(args);
    CpsSlice.IntermediateResult result = new CpsSlice.IntermediateResult(node.selector());
    currentSlice.setResult(result);
    beginNewSlice();
    outboundArgument = result;
  }

  @Override
  public void visitMethod(Method method) {
    unexpectedVisit(method);
  }

  @Override
  public void visitSlotDefinition(SlotDefinition slotDefinition) {
    unexpectedVisit(slotDefinition);
  }

  @Override
  public void visitLiteralNil(LiteralNil literalNil) {
    outboundArgument = new CpsSlice.PervasiveReference(literalNil);
  }

  @Override
  public void visitLiteralBoolean(LiteralBoolean literalBoolean) {
    outboundArgument = new CpsSlice.PervasiveReference(literalBoolean);
  }

  @Override
  public void visitSelf(Self self) {
    outboundArgument = new CpsSlice.PervasiveReference(self);
  }

  @Override
  public void visitSuper(Super super1) {
    outboundArgument = new CpsSlice.PervasiveReference(super1);
  }

  @Override
  public void visitOuter(Outer outer) {
    outboundArgument = new CpsSlice.PervasiveReference(outer);
  }

  @Override
  public void visitReturn(Return return1) {
    visit(return1.expression());
  }

  @Override
  public void visitEnclosingObjectReference(EnclosingObjectReference enclosingObjectReference) {
    outboundArgument = new CpsSlice.PervasiveReference(enclosingObjectReference);
  }

  @Override
  public void visitVariableReference(VariableReference localVariableReference) {
    outboundArgument = new CpsSlice.MethodVarReference(localVariableReference);
  }

  @Override
  public void visitVariableAssignment(VariableAssignment variableAssignment) {
    outboundArgument = new CpsSlice.MethodVarReference(variableAssignment);
  }

  private void unimplemented(AstNode node) {
    throw new UnsupportedOperationException("Visiting not yet implemented for this node: " + node);
  }

  private void unexpectedVisit(AstNode node) {
    throw new IllegalArgumentException("Unexpected node visited: " + node);
  }
}
