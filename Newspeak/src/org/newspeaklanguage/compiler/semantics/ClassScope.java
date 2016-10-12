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
