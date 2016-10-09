package org.newspeaklanguage.compiler.semantics;

import java.util.HashMap;
import java.util.Map;

public abstract class Scope<E extends ScopeEntry> {
  
  protected final Scope<? extends ScopeEntry> parent;
  protected final int level;
  protected final Map<String, E> names = new HashMap<String, E>();
  
  Scope(Scope<? extends ScopeEntry> parent, int level) {
    this.parent = parent;
    this.level = level;
  }

  Scope(Scope<? extends ScopeEntry> parent) {
    this(parent, parent.level() + 1);
  }
  
  public Scope<? extends ScopeEntry> parent() { return parent; }
  public int level() { return level; }
  
  public boolean isClassScope() { return false; }
  public boolean isMethodScope() { return false; }
  public boolean isBlockScope() { return false; }
  
  public abstract CodeScope enclosingMethodScope();
  
  public ScopeEntry lookup(String name) {
    E local = names.get(name);
    return local != null
        ? local 
        : parent != null ? parent.lookup(name) : null;
  }
  
  public E lookupLocally(String name) {
    return names.get(name);
  }
  
  public abstract ClassScope lookupClass(String name);
  
  public E define(String name) {
    E def = createScopeEntry(name);
    names.put(name, def);
    return def;
  }
  
  protected abstract E createScopeEntry(String name);
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName()
        + "(level: " + level 
        + ", entries: " + names.size() + ")";
  }
}
