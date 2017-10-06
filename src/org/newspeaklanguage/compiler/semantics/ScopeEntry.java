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

import org.newspeaklanguage.compiler.ast.NameDefinition;

/**
 * Keeps track of the scope where the name is defined and the AST node such as a
 * SlotDefinition which defined it.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class ScopeEntry {

  protected final String name;
  protected final Scope<? extends ScopeEntry> definitionScope;
  protected NameDefinition astNode;

  ScopeEntry(String name, Scope<? extends ScopeEntry> definitionScope) {
    this.name = name;
    this.definitionScope = definitionScope;
  }

  public String name() {
    return name;
  }

  public Scope<? extends ScopeEntry> scope() {
    return definitionScope;
  }

  public NameDefinition astNode() {
    return astNode;
  }

  public void setAstNode(NameDefinition node) {
    astNode = node;
  }

  /**
   * Return true if the receiver is implementable as a local variable in a Java
   * method (potentially copied down from the defining method and into the
   * closure implementation one). Method and block arguments and temps are
   * implemented this way.
   */
  public boolean isImplementableAsLocalVariable() {
    return definitionScope.isMethodScope() || definitionScope.isBlockScope();
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName()
        + "(" + name + ")"
        + astNode;
  }
}
