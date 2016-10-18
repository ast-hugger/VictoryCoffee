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

import java.util.Optional;

import org.newspeaklanguage.compiler.ast.NameDefinition;

/**
 * A reference to a lexically visible method or block argument or a temp
 * variable (not a class slot).
 *
 */
public class LexicalVarReference extends NameMeaning {

  /** The scope entry for the definition of the referenced variable. */
  private final CodeScopeEntry definition;

  /** The scope from which the reference is made. */
  private final Scope<? extends ScopeEntry> sourceScope;

  /**
   * The binding which will be made locally in the implementation method to
   * access the variable.
   */
  private LocalVariable localVariable;

  LexicalVarReference(CodeScopeEntry definition,
      Scope<? extends ScopeEntry> sourceScope)
  {
    assert definition.astNode() instanceof NameDefinition;
    this.definition = definition;
    this.sourceScope = sourceScope;
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
  
  public void setLocalVariable(LocalVariable var) {
    assert this.localVariable == null; // no resetting in a valid program
    this.localVariable = var;
  }

  @Override
  public boolean isLexicalVarReference() {
    return true;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Optional<CodeScopeEntry> lexicalDefinition() {
    return Optional.of(definition);
  }

  public boolean isClean() {
    return sourceScope.equals(definition.scope());
  }

  public boolean isCopiable() {
    return ((NameDefinition) definition.astNode()).isImmutable();
  }
}