package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.ast.ClassDecl;

public class ClassScope extends Scope {
  
  protected final ClassDecl classNode;
  
  ClassScope(ClassDecl classNode, Scope parent, int level) {
    super(parent, level);
    this.classNode = classNode;
  }
  
  ClassScope(ClassDecl classNode, Scope parent) {
    super(parent);
    this.classNode = classNode;
  }
  
  public ClassDecl classNode() { return classNode; }
  
  @Override
  public boolean isClassScope() { return true; }

}
