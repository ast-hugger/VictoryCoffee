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

import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.AstNodeVisitor;
import org.newspeaklanguage.compiler.ast.NameDefinition;

/**
 * This is a syntax tree node a tree rewriter inserts in place of a receiverless
 * message send when it determines that the send is really a variable reference.
 * Variable references are references to the arguments or the temps of a method
 * or a block (i.e. variables of a JVM method frame).
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
public class VariableReference extends AstNode {

  /** The scope entry for the definition of the referenced variable. */
  private final CodeScopeEntry definition;

  /** The scope from which the reference is made. */
  private final CodeScope sourceScope;

  /**
   * The binding made locally in the method or block to
   * hold the variable. If the variable is local, it's a temp.
   * If it's copied, it's an argument.
   */
  private LocalVariable localVariable;

  VariableReference(CodeScopeEntry definition, CodeScope sourceScope) {
    assert definition.astNode() instanceof NameDefinition;
    this.definition = definition;
    this.sourceScope = sourceScope;
    if (isLocal()) {
      String varName = definition.astNode().name();
      localVariable = sourceScope.ownVariableNamed(varName).get();
    } else {
      NameDefinition varNode = definition.astNode();
      // the sourceScope is a BlockScope by definition if the variable is not local
      localVariable = ((BlockScope) sourceScope).registerCopiedVariable(varNode);
      propagateCopiedVariable(varNode);
    }
  }

  private void propagateCopiedVariable(NameDefinition varNode) {
    CodeScope here = (CodeScope) sourceScope.parent();
    CodeScope definingScope = (CodeScope) definition.scope();
    while (!here.equals(definingScope)) {
      ((BlockScope) here).registerCopiedVariable(varNode);
      here = (CodeScope) here.parent();
    }
    if (varNode.isMutable()) {
      here.markVariableAsBoxed(varNode.name());
    }
  }

  public CodeScopeEntry definition() {
    return definition;
  }

  public Scope<? extends ScopeEntry> sourceScope() {
    return sourceScope;
  }

  public LocalVariable localVariable() {
    return localVariable;
  }

  public boolean isLocal() {
    return sourceScope.equals(definition.scope());
  }

  public boolean isCopiable() {
    return ((NameDefinition) definition.astNode()).isImmutable();
  }

  @Override
  public void accept(AstNodeVisitor visitor) {
    throw new IllegalArgumentException("This node should not be visited by a basic AstVisitor");
  }

  @Override
  public void accept(RewrittenNodeVisitor visitor) {
    visitor.visitVariableReference(this);
  }
}
