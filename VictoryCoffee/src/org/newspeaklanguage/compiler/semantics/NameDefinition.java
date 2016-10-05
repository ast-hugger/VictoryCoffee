package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.ast.AstNode;

/**
 * A scope entry. Keeps track of the scope where the name is defined and the AST
 * node such as a SlotDefinition which defined it.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class NameDefinition {
  
  private final String name;
  private final Scope definitionScope;
  
  private AstNode definingNode;
  
  NameDefinition(String name, Scope definitionScope) {
    this.name = name;
    this.definitionScope = definitionScope;
  }

  public String name() { return name; }
  public Scope definitionScope() { return definitionScope; }
  
  public AstNode definingNode() { return definingNode; }
  
  public void setDefiningNode(AstNode node) {
    definingNode = node;
  }
  
}
