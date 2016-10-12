package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.ast.Method;

public class MethodScope extends CodeScope {

  MethodScope(Method definition, Scope<? extends ScopeEntry> parent) {
    super(definition, parent);
  }

  @Override
  public boolean isMethodScope() { return true; }

  @Override
  public CodeScope methodScope() {
    return this;
  }

}
