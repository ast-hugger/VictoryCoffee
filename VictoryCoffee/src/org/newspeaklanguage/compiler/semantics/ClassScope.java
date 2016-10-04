package org.newspeaklanguage.compiler.semantics;

public class ClassScope extends Scope {
  
  ClassScope(Scope parent, int level) {
    super(parent, level);
  }
  
  ClassScope(Scope parent) {
    super(parent);
  }
  
  @Override
  public boolean isClassScope() { return true; }

}
