package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.ast.Argument;
import org.newspeaklanguage.compiler.ast.SlotDefinition;

public class CodeScopeEntry extends ScopeEntry {
  
  protected final int index;

  CodeScopeEntry(String name, CodeScope definitionScope, int index) {
    super(name, definitionScope);
    this.index = index;
  }

  public int index() { return index; }
  
  public boolean isMethodArgument() {
    return definingNode instanceof Argument;
  }
  
  public boolean isMethodTemp() {
    return definingNode instanceof SlotDefinition;
  }
}
