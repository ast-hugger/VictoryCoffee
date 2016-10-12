package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.ast.Argument;
import org.newspeaklanguage.compiler.ast.SlotDefinition;

public class CodeScopeEntry extends ScopeEntry {

  CodeScopeEntry(String name, CodeScope definitionScope) {
    super(name, definitionScope);
  }

  public boolean isMethodArgument() {
    return astNode instanceof Argument;
  }

  public boolean isMethodTemp() {
    return astNode instanceof SlotDefinition;
  }
}
