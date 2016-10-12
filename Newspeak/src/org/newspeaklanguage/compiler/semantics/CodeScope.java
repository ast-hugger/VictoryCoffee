package org.newspeaklanguage.compiler.semantics;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.compiler.ast.CodeUnit;

/**
 * A scope established by a method or a block. The names defined by this scope
 * are accessor selectors to the method's arguments and temps. A variable always
 * adds a getter name to the scope. A setter name is only added is the variable
 * is not read-only.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public abstract class CodeScope extends Scope<CodeScopeEntry> {

  /**
   * The local variables of the implementation method frame which correspond to
   * the actual Newspeak arguments and temps of the block or method.
   */
  protected final List<LocalVariable> ownVariables = new ArrayList<>();

  CodeScope(CodeUnit definition, Scope<? extends ScopeEntry> parent) {
    super(definition, parent);
    definition.arguments().forEach(
        each -> ownVariables.add(new LocalVariable(each.name(), false)));
    definition.temps().forEach(
        each -> ownVariables.add(new LocalVariable(each.name(), false)));
  }

  @Override
  public ClassScope outerClassNamed(String name) {
    return parent == null ? null : parent.outerClassNamed(name);
  }

  public Optional<LocalVariable> ownVariableNamed(String name) {
    return find(name, ownVariables);
  }

  public void assignLocalVariableIndices() {
    int index = firstOwnVariableIndex();
    for (LocalVariable var : ownVariables) {
      var.setIndex(index++);
    }
  }

  @Override
  public CodeScopeEntry define(String name) {
    if (NamingPolicy.isSetterSelector(name)) {
      CodeScopeEntry newEntry = new CodeScopeEntry(name, this);
      names.put(name, newEntry);
      return newEntry;
    } else {
      return super.define(name);
    }
  }

  /**
   * Mark a variable defined in this scope (an argument or a temp) as boxed. The
   * variable must exist.
   */
  public void markVariableAsBoxed(String name) {
    ownVariableNamed(name).get().setIsBoxed(true);
  }

  protected int firstOwnVariableIndex() {
    return 1;
  }

  @Override
  protected CodeScopeEntry createScopeEntry(String name) {
    return new CodeScopeEntry(name, this);
  }

  // visible for testing
  List<String> ownVariableNames() {
    return ownVariables.stream()
        .map(each -> each.name())
        .collect(Collectors.toList());
  }

  protected Optional<LocalVariable> find(String name, List<LocalVariable> vars) {
    return vars.stream()
        .filter(some -> some.name().equals(name))
        .findAny();
  }

}
