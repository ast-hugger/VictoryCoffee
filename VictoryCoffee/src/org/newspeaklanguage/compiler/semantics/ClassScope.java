package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.ast.ClassDecl;

public class ClassScope extends Scope<ScopeEntry> {
  
  /**
   * The class this scope is associated with.
   */
  protected final ClassDecl classNode;
  
  ClassScope(ClassDecl classNode, Scope<? extends ScopeEntry> parent, int level) {
    super(parent, level);
    this.classNode = classNode;
  }
  
  ClassScope(ClassDecl classNode, Scope<? extends ScopeEntry> parent) {
    super(parent);
    this.classNode = classNode;
  }
  
  public ClassDecl classNode() { return classNode; }
  
  public ClassScope lookupClass(String name) {
    if (classNode.name().equals(name)) {
      return this;
    }
    return parent == null ? null : parent.lookupClass(name);
  }
  
  @Override
  public boolean isClassScope() { return true; }
  
  @Override
  public CodeScope enclosingMethodScope() {
    return null;
  }

  @Override
  protected ScopeEntry createScopeEntry(String name) {
    return new ScopeEntry(name, this);
  }
}
