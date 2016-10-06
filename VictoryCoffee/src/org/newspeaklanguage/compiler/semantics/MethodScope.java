package org.newspeaklanguage.compiler.semantics;

public class MethodScope extends Scope {
  
  protected int lastVarIndex = 0;
  
  MethodScope(Scope parent) {
    super(parent);
  }
  
  @Override
  public boolean isMethodScope() { return true; }
  
  @Override
  protected MethodScopeEntry createScopeEntry(String name) {
    return new MethodScopeEntry(name, this, ++lastVarIndex);
  }
}
