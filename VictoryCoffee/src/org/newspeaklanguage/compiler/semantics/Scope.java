package org.newspeaklanguage.compiler.semantics;

import java.util.HashMap;
import java.util.Map;

public abstract class Scope {
  
  protected final Scope parent;
  protected final int level;
  protected final Map<String, ScopeEntry> names = new HashMap<String, ScopeEntry>();
  
  Scope(Scope parent, int level) {
    this.parent = parent;
    this.level = level;
  }

  Scope(Scope parent) {
    this(parent, parent.level() + 1);
  }
  
  public Scope parent() { return parent; }
  public int level() { return level; }
  
  public boolean isClassScope() { return false; }
  public boolean isMethodScope() { return false; }
  
  public ScopeEntry lookup(String name) {
    ScopeEntry local = names.get(name);
    return local != null
        ? local 
        : parent != null ? parent.lookup(name) : null;
  }
  
  public abstract ClassScope lookupClass(String name);
  
  public ScopeEntry define(String name) {
    ScopeEntry def = createScopeEntry(name);
    names.put(name, def);
    return def;
  }
  
  protected ScopeEntry createScopeEntry(String name) {
    return new ScopeEntry(name, this);
  }
}
