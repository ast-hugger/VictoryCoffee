package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.ast.AstNode;

/**
 * Keeps track of the scope where the name is defined and the AST
 * node such as a SlotDefinition which defined it.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class ScopeEntry {
  
  protected final String name;
  protected final Scope definitionScope;
  protected AstNode definingNode;
  
  ScopeEntry(String name, Scope definitionScope) {
    this.name = name;
    this.definitionScope = definitionScope;
  }

  public String name() { return name; }
  public Scope definitionScope() { return definitionScope; }
  public AstNode definingNode() { return definingNode; }
  public void setDefiningNode(AstNode node) { definingNode = node; }
  
  /**
   * Return true if the receiver is a method argument or a temp variable.
   */
  public boolean isLocalVariable() {
    return definitionScope.isMethodScope();
  }
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "(" + name + ")";
  }
}
