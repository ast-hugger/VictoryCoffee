package org.newspeaklanguage.compiler.semantics;

import java.util.HashMap;
import java.util.Map;

import org.newspeaklanguage.compiler.ast.AstNode;

/**
 * An analyzer record associated with an AST node that defines a number of
 * lexically visible names.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 * @param <T>
 */
public abstract class Scope<T extends ScopeEntry> {
  
  /**
   * The AstNode this scope is associated with.
   */
  protected final AstNode definition;
  
  /**
   * The lexical scope one level up. {@code null} for the top-level class.
   */
  protected final Scope<? extends ScopeEntry> parent;
  
  /**
   * The nesting level of this scope. The absolute value is not really
   * important, what matters is that the level of a child is 1 greater than the
   * level of the parent. Used in figuring out things like the index of the
   * lexical receiver in the {@code enclosingObjects} array.
   */
  protected final int level;
  
  /**
   * Scope entries for the names defined by this scope, keyed by name.
   */
  protected final Map<String, T> names = new HashMap<>();
  
  Scope(AstNode definition, Scope<? extends ScopeEntry> parent, int level) {
    this.definition = definition;
    this.parent = parent;
    this.level = level;
  }

  Scope(AstNode definition, Scope<? extends ScopeEntry> parent) {
    this(definition, parent, parent.level() + 1);
  }

  public AstNode astNode() {
    return definition;
  }
  
  public Scope<? extends ScopeEntry> parent() {
    return parent;
  }

  public int level() {
    return level;
  }

  public boolean isClassScope() { return false; }
  public boolean isMethodScope() { return false; }
  public boolean isBlockScope() { return false; }
  
  public abstract CodeScope methodScope();
  
  public ScopeEntry lookup(String name) {
    T local = names.get(name);
    return local != null
        ? local 
        : parent != null ? parent.lookup(name) : null;
  }
  
  public T lookupLocally(String name) {
    return names.get(name);
  }
  
  public abstract ClassScope outerClassNamed(String name);
  
  public T define(String name) {
    T def = createScopeEntry(name);
    names.put(name, def);
    return def;
  }
  
  protected abstract T createScopeEntry(String name);
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName()
        + "(level: " + level 
        + ", entries: " + names.size() + ")";
  }
}
