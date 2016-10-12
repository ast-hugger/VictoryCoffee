package org.newspeaklanguage.compiler.semantics;

import java.util.Optional;

import org.newspeaklanguage.compiler.ast.NameDefinition;

/**
 * A reference to a lexically visible method or block argument or a temp
 * variable (not a class slot).
 *
 */
public class LexicalVarReference extends NameMeaning {

  /** The scope entry for the definition of the referenced variable. */
  private final CodeScopeEntry definition;

  /** The scope from which the reference is made. */
  private final Scope<? extends ScopeEntry> sourceScope;

  /**
   * The binding which will be made locally in the implementation method to
   * access the variable.
   */
  private LocalVariable localVariable;

  LexicalVarReference(CodeScopeEntry definition,
      Scope<? extends ScopeEntry> sourceScope)
  {
    assert definition.astNode() instanceof NameDefinition;
    this.definition = definition;
    this.sourceScope = sourceScope;
  }

  public CodeScopeEntry definition() {
    return definition;
  }

  public Scope<? extends ScopeEntry> sourceScope() {
    return sourceScope;
  }
  
  public LocalVariable localVariable() {
    return localVariable;
  }
  
  public void setLocalVariable(LocalVariable var) {
    assert this.localVariable == null; // no resetting in a valid program
    this.localVariable = var;
  }

  @Override
  public boolean isLocalVarReference() {
    return true;
  }

  @Override
  public Optional<ScopeEntry> lexicalDefinition() {
    return Optional.of(definition);
  }

  public boolean isClean() {
    return sourceScope.equals(definition.scope());
  }

  public boolean isCopiable() {
    return ((NameDefinition) definition.astNode()).isImmutable();
  }
}