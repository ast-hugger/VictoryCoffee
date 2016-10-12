package org.newspeaklanguage.compiler.semantics;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.NameDefinition;

public class BlockScope extends CodeScope {
  
  private final List<LocalVariable> copiedVariables = new ArrayList<LocalVariable>();

  BlockScope(Block definition, Scope<? extends ScopeEntry> parent) {
    super(definition, parent);
  }

  @Override
  public boolean isBlockScope() { return true; }

  @Override
  public CodeScope methodScope() {
    return parent.methodScope();
  }

  public LocalVariable registerCopiedVariable(NameDefinition def) {
    return find(def.name(), copiedVariables).orElseGet(
        () -> {
          LocalVariable newVar = new LocalVariable(def.name(), def.isMutable());
          copiedVariables.add(newVar);
          return newVar;
        });
  }
  
  public Optional<LocalVariable> localVariableNamed(String name) {
    return find(name, copiedVariables);
  }
  
  private Optional<LocalVariable> find(String name, List<LocalVariable> vars) {
    return vars.stream()
        .filter(some -> some.name().equals(name))
        .findAny();
  }
}