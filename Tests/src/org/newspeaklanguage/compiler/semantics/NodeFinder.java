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

package org.newspeaklanguage.compiler.semantics;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.compiler.ast.Argument;
import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.AstNodeVisitorSkeleton;
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
import org.newspeaklanguage.compiler.ast.NameDefinition;
import org.newspeaklanguage.compiler.ast.Outer;
import org.newspeaklanguage.compiler.ast.Return;
import org.newspeaklanguage.compiler.ast.Self;
import org.newspeaklanguage.compiler.ast.SlotDefinition;
import org.newspeaklanguage.compiler.ast.Super;


public class NodeFinder extends AstNodeVisitorSkeleton {
  
  public static AstNode find(Class<? extends AstNode> klass, AstNode treeRoot) {
    NodeFinder finder = new NodeFinder(treeRoot);
    return finder.find(some -> some.getClass() == klass);
  }
  
  public static AstNode findClass(String name, AstNode treeRoot) {
    NodeFinder finder = new NodeFinder(treeRoot);
    return finder.find(some ->
      some.getClass() == ClassDecl.class
      && ((ClassDecl) some).name().equals(name));
  }
  
  public static MessageSendNoReceiver findLocalVarReference(String name, AstNode treeRoot) {
    NodeFinder finder = new NodeFinder(treeRoot);
    return (MessageSendNoReceiver) finder.find(some ->
      some instanceof MessageSendNoReceiver
      && NamingPolicy.slotForSelector(((MessageSendNoReceiver) some).selector()).equals(name)); 
  }
  
  public static NameDefinition findLocalVarDefinition(String name, AstNode treeRoot) {
    NodeFinder finder = new NodeFinder(treeRoot);
    return (NameDefinition) finder.find(some -> {
      if (some instanceof Argument && ((Argument) some).name().equals(name)) {
        return true;
      }
      if (some instanceof SlotDefinition && ((SlotDefinition) some).name().equals(name)) {
        return true;
      }
      return false;
    }); 
  }
  
  private final AstNode treeRoot;
  private Predicate<AstNode> criterion;
  private List<AstNode> results;
  
  public NodeFinder(AstNode treeRoot) {
    this.treeRoot = treeRoot;
  }

  public List<AstNode> findAll(Predicate<AstNode> criterion) {
    assert this.criterion == null;
    this.criterion = criterion;
    results = new LinkedList<AstNode>();
    try {
      treeRoot.accept(this);
      return results;
    } 
    finally {
      this.criterion = null;
      this.results = null;
    }
  }
  
  public AstNode find(Predicate<AstNode> criterion) {
    List<AstNode> results = findAll(criterion);
    return results.get(0);
  }
  
  @Override
  public void visitArgument(Argument argument) {
    consider(argument);
    super.visitArgument(argument);
  }

  @Override
  public void visitBlock(Block block) {
    consider(block);
    super.visitBlock(block);
  }

  @Override
  public void visitCategory(Category category) {
    consider(category);
    super.visitCategory(category);
  }

  @Override
  public void visitClassDecl(ClassDecl classDecl) {
    consider(classDecl);
    super.visitClassDecl(classDecl);
  }

  @Override
  public void visitLiteralNumber(LiteralNumber literalNumber) {
    consider(literalNumber);
    super.visitLiteralNumber(literalNumber);
  }

  @Override
  public void visitLiteralString(LiteralString literalString) {
    consider(literalString);
    super.visitLiteralString(literalString);
  }

  @Override
  public void visitMessagePattern(MessagePattern messagePattern) {
    consider(messagePattern);
    super.visitMessagePattern(messagePattern);
  }

  @Override
  public void visitMessageSendNoReceiver(MessageSendNoReceiver messageSendNoReceiver) {
    consider(messageSendNoReceiver);
    super.visitMessageSendNoReceiver(messageSendNoReceiver);
  }

  @Override
  public void visitMessageSendWithReceiver(MessageSendWithReceiver messageSendWithReceiver) {
    consider(messageSendWithReceiver);
    super.visitMessageSendWithReceiver(messageSendWithReceiver);
  }

  @Override
  public void visitMethod(Method method) {
    consider(method);
    super.visitMethod(method);
  }

  @Override
  public void visitSlotDefinition(SlotDefinition slotDefinition) {
    consider(slotDefinition);
    super.visitSlotDefinition(slotDefinition);
  }

  @Override
  public void visitLiteralNil(LiteralNil literalNil) {
    consider(literalNil);
    super.visitLiteralNil(literalNil);
  }

  @Override
  public void visitLiteralBoolean(LiteralBoolean literalBoolean) {
    consider(literalBoolean);
    super.visitLiteralBoolean(literalBoolean);
  }

  @Override
  public void visitSelf(Self self) {
    consider(self);
    super.visitSelf(self);
  }

  @Override
  public void visitSuper(Super superNode) {
    consider(superNode);
    super.visitSuper(superNode);
  }

  @Override
  public void visitOuter(Outer outer) {
    consider(outer);
    super.visitOuter(outer);
  }

  @Override
  public void visitReturn(Return returnNode) {
    consider(returnNode);
    super.visitReturn(returnNode);
  }
  
  private void consider(AstNode node) {
    if (criterion.test(node)) {
      results.add(node);
    }
  }

}
