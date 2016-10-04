package org.newspeaklanguage.compiler.semantics;

import java.util.HashMap;
import java.util.Map;

public abstract class Scope {
  
  private final Scope parent;
  private final int level;
  private final Map<String, NameDefinition> names = new HashMap<String, NameDefinition>();
  
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
  public boolean isBlockScope() { return false; }
  
  public NameDefinition lookup(String name) {
    NameDefinition local = names.get(name);
    return local != null
        ? local 
        : parent != null ? parent.lookup(name) : null;
  }
  
  public NameDefinition define(String name) {
    NameDefinition def = new NameDefinition(name, this);
    names.put(name, def);
    return def;
  }
  
}
