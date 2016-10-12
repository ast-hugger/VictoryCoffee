package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.ast.NameDefinition;

/**
 * Keeps track of the scope where the name is defined and the AST node such as a
 * SlotDefinition which defined it.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class ScopeEntry {

  protected final String name;
  protected final Scope<? extends ScopeEntry> definitionScope;
  protected NameDefinition astNode;

  ScopeEntry(String name, Scope<? extends ScopeEntry> definitionScope) {
    this.name = name;
    this.definitionScope = definitionScope;
  }

  public String name() {
    return name;
  }

  public Scope<? extends ScopeEntry> scope() {
    return definitionScope;
  }

  public NameDefinition astNode() {
    return astNode;
  }

  public void setAstNode(NameDefinition node) {
    astNode = node;
  }

  /**
   * Return true if the receiver is implemented as a local variable in a Java
   * method (potentially copied down from the defining method and into the
   * closure implementation one). Method and block arguments and temps are
   * implemented this way.
   */
  public boolean isImplementedAsLocalVar() {
    return definitionScope.isMethodScope() || definitionScope.isBlockScope();
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName()
        + "(" + name + ")"
        + astNode;
  }
}
