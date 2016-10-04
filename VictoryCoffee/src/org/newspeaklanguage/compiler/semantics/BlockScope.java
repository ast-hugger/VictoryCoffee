package org.newspeaklanguage.compiler.semantics;

public class BlockScope extends Scope {
  
  private final int methodLevel;
  
  BlockScope(Scope parent) {
    super(parent);
    methodLevel = parent.isClassScope()
        ? 0
        : ((BlockScope) parent).methodLevel() + 1;
  }
  
  public int methodLevel() { return methodLevel; }

  @Override
  public boolean isBlockScope() { return true; }
}
