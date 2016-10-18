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

import org.newspeaklanguage.compiler.ast.ClassDecl;

public class ClassScope extends Scope<ScopeEntry> {

  ClassScope(ClassDecl definition, Scope<? extends ScopeEntry> parent, int level) {
    super(definition, parent, level);
  }

  ClassScope(ClassDecl classNode, Scope<? extends ScopeEntry> parent) {
    super(classNode, parent);
  }

  public ClassDecl classNode() {
    return (ClassDecl) definition;
  }

  public ClassScope outerClassNamed(String name) {
    if (classNode().name().equals(name)) {
      return this;
    }
    return parent == null ? null : parent.outerClassNamed(name);
  }

  @Override
  public boolean isClassScope() {
    return true;
  }

  @Override
  public CodeScope methodScope() {
    return null;
  }

  @Override
  protected ScopeEntry createScopeEntry(String name) {
    return new ScopeEntry(name, this);
  }
}
