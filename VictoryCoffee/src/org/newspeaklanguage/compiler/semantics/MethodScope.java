package org.newspeaklanguage.compiler.semantics;

public class MethodScope extends Scope {
  
  protected int lastVarIndex = 0;
  
  MethodScope(Scope parent) {
    super(parent);
  }
  
  @Override
  public ClassScope lookupClass(String name) {
    return parent == null ? null : parent.lookupClass(name);
  }
  
  @Override
  public boolean isMethodScope() { return true; }
  
  @Override
  protected MethodScopeEntry createScopeEntry(String name) {
    return new MethodScopeEntry(name, this, ++lastVarIndex);
  }
}
