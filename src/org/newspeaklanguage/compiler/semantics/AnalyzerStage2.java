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

import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.AstNodeVisitorSkeleton;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.ast.MessageSendNoReceiver;
import org.newspeaklanguage.compiler.ast.MessageSendWithReceiver;
import org.newspeaklanguage.compiler.ast.Method;
import org.newspeaklanguage.compiler.ast.NameDefinition;
import org.newspeaklanguage.compiler.ast.Outer;
import org.newspeaklanguage.compiler.ast.Self;

/**
 * A visitor for the AST which is mostly concerned with rewriting {@link MessageSendNoReceiver} nodes
 * to something that reflects their meaning. The AST must have been previously visited by an
 * {@link AnalyzerStage1}.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class AnalyzerStage2 extends AstNodeVisitorSkeleton {

  /*
   * Static main access point
   */

  public static void analyze(ClassDecl classDecl) {
    new AnalyzerStage2().visit(classDecl);
  }

  /*
   * Instance side
   */

  private Scope<? extends ScopeEntry> currentScope;

  private AnalyzerStage2() {
  }

  @Override
  public void visitClassDecl(ClassDecl classDecl) {
    Scope<? extends ScopeEntry> original = currentScope;
    currentScope = classDecl.scope();
    try {
      super.visitClassDecl(classDecl);
    } finally {
      currentScope = original;
    }
  }

  @Override
  public void visitBlock(Block blockNode) {
    Scope<? extends ScopeEntry> original = currentScope;
    currentScope = blockNode.scope();
    try {
      super.visitBlock(blockNode);
      // Now that all the locals are finalized, we know their indices
      blockNode.scope().assignLocalVariableIndices();
    } finally {
      currentScope = original;
    }
  }

  @Override
  public void visitMethod(Method methodNode) {
    Scope<? extends ScopeEntry> original = currentScope;
    currentScope = methodNode.scope();
    try {
      super.visitMethod(methodNode);
      // Now that all the locals are finalized, we know their indices
      methodNode.scope().assignLocalVariableIndices();
    } finally {
      currentScope = original;
    }
  }

  @Override
  public void visitOuter(Outer outerNode) {
    ClassScope target = currentScope.outerClassNamed(outerNode.name());
    if (target == null) {
      throw new IllegalArgumentException("no such outer class");
    }
    outerNode.setTargetClassScope(target);
  }

  @Override
  public void visitMessageSendNoReceiver(MessageSendNoReceiver messageSend) {
    ScopeEntry lexicalDef = currentScope.lookup(messageSend.selector());
    if (lexicalDef == null) {
      // no lexically visible definition; this is a self send
      messageSend.setRewritten(rewriteWithReceiver(messageSend, new Self()));
    } else {
      if (lexicalDef.isImplementableAsLocalVariable()) {
        AstNode rewritten;
        if (NamingPolicy.isSetterSelector(messageSend.selector())) {
          assert messageSend.arguments().size() == 1;
          rewritten = new VariableAssignment(
              messageSend.arguments().get(0),
              messageSend.isSetterSend(),
              (CodeScopeEntry) lexicalDef,
              (CodeScope) currentScope);
        } else {
          assert messageSend.arguments().size() == 0;
          rewritten = new VariableReference((CodeScopeEntry) lexicalDef, (CodeScope) currentScope);
        }
        messageSend.setRewritten(rewritten);
      } else if (!lexicalDef.scope().equals(nearestClassScope())) {
        // a lexical reference to something outside of the current class
        int level = lexicalDef.scope().level();
        messageSend.setRewritten(rewriteWithReceiver(messageSend, new EnclosingObjectReference(level)));
      } else {
        // a lexical reference to something in the current class
        messageSend.setRewritten(rewriteWithReceiver(messageSend, new Self()));
      }
    }
    super.visitMessageSendNoReceiver(messageSend);
  }

  private MessageSendWithReceiver rewriteWithReceiver(MessageSendNoReceiver node, AstNode receiver) {
    return new MessageSendWithReceiver(receiver, node.selector(), node.isSetterSend(), node.arguments());
  }

  private ClassScope nearestClassScope() {
    Scope<? extends ScopeEntry> here = currentScope;
    while (here != null && !here.isClassScope()) {
      here = here.parent();
    }
    return (ClassScope) here;
  }

  // private CodeScope nearestMethodScope() {
  // Scope<? extends ScopeEntry> here = currentScope;
  // while (here != null && !here.isMethodScope()) {
  // here = here.parent();
  // }
  // return (CodeScope) here;
  // }

}
