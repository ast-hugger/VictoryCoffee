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
  protected final Scope<? extends ScopeEntry> definitionScope;
  protected AstNode definingNode;
  
  ScopeEntry(String name, Scope<? extends ScopeEntry> definitionScope) {
    this.name = name;
    this.definitionScope = definitionScope;
  }

  public String name() { return name; }
  public Scope<? extends ScopeEntry> scope() { return definitionScope; }
  public AstNode astNode() { return definingNode; }
  public void setDefiningNode(AstNode node) { definingNode = node; }
  
  /**
   * Return true if the receiver is a method argument or a temp variable.
   * (It can also be a class slot or a method node).
   */
  public boolean isLocalVariable() {
    return definitionScope.isMethodScope() || definitionScope.isBlockScope();
  }
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "(" + name + ")";
  }
}
