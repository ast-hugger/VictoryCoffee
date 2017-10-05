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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.NameDefinition;

public class BlockScope extends CodeScope {

  /**
   * The local variables in the implementation method frame which are the
   * synthetic arguments added to accept values and bindings (boxes) copied from
   * outer scopes.
   */
  private final List<LocalVariable> copiedVariables = new ArrayList<>();

  BlockScope(Block definition, Scope<? extends ScopeEntry> parent) {
    super(definition, parent);
  }

  @Override
  public boolean isBlockScope() {
    return true;
  }

  @Override
  public CodeScope methodScope() {
    return parent.methodScope();
  }

  @Override
  public Optional<LocalVariable> localVariableNamed(String name) {
    Optional<LocalVariable> local = super.localVariableNamed(name);
    return local.isPresent() ? local : copiedVariableNamed(name);
  }

  public LocalVariable registerCopiedVariable(NameDefinition def) {
    return find(def.name(), copiedVariables).orElseGet(() -> {
      LocalVariable newVar = new LocalVariable(def.name(), false, def.isMutable());
      copiedVariables.add(newVar);
      return newVar;
    });
  }

  public int copiedVariableCount() {
    return copiedVariables.size();
  }

  public Optional<LocalVariable> copiedVariableNamed(String name) {
    return find(name, copiedVariables);
  }

  public void forEachCopiedVariable(Consumer<LocalVariable> action) {
    copiedVariables.forEach(action);
  }

  public List<LocalVariable> copiedVariables() {
    return Collections.unmodifiableList(copiedVariables);
  }

  @Override
  public void assignLocalVariableIndices() {
    int index = 1;
    for (LocalVariable var : copiedVariables) {
      var.setIndex(index++);
    }
    super.assignLocalVariableIndices();
  }

  public void forEachBoxedTemp(Consumer<LocalVariable> action) {
    ownVariables.stream().filter(LocalVariable::isBoxed).forEach(action);
  }
  
  @Override
  protected int firstOwnVariableIndex() {
    return copiedVariables.size() + 1;
  }

  // visible for testing
  List<String> copiedVariableNames() {
    return copiedVariables.stream()
        .map(each -> each.name())
        .collect(Collectors.toList());
  }

}