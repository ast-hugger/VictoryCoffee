package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.NamingPolicy;

/**
 * A scope established by a method or a block. The names defined by this scope are accessor
 * selectors to the method's arguments and temps. The scope, together with the
 * entries, keeps track of the index of those variables. The index used in the
 * local var instructions to address them in the frame.
 * <p>
 * Because of the way the AST is traversed, the index simply starts off at 1 (0
 * is the receiver, and it has no AST node) and is incremented for every name
 * defined.
 * <p>
 * One nuance is that both getter and setter selectors for a field (unless the
 * field is read-only) should be defined in the scope, but setters should not be
 * allocated a new variable index. Instead, they should get the same index as
 * the corresponding getter.
 * <p>
 * A variable always adds a getter name to the scope. A setter name is only
 * added is the variable is not read-only.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class CodeScope extends Scope<CodeScopeEntry> {
  
  private final boolean isMethod;
  protected int lastVarIndex = 0;
  
  CodeScope(Scope<? extends ScopeEntry> parent, boolean isMethod) {
    super(parent);
    this.isMethod = isMethod;
  }
  
  @Override
  public ClassScope lookupClass(String name) {
    return parent == null ? null : parent.lookupClass(name);
  }
  
  @Override
  public boolean isMethodScope() { return isMethod; }

  @Override
  public boolean isBlockScope() { return !isMethod; }

  @Override
  public CodeScope enclosingMethodScope() {
    return isMethod ? this : parent.enclosingMethodScope();
  }
  
  @Override
  public CodeScopeEntry define(String name) {
    if (NamingPolicy.isSetterSelector(name)) {
      CodeScopeEntry getterEntry = lookupLocally(NamingPolicy.getterForSetter(name));
      assert getterEntry != null;
      CodeScopeEntry newEntry = new CodeScopeEntry(name, this, getterEntry.index());
      names.put(name, newEntry);
      return newEntry;
    } else {
      return super.define(name);
    }
  }
  
  @Override
  protected CodeScopeEntry createScopeEntry(String name) {
    return new CodeScopeEntry(name, this, ++lastVarIndex);
  }
}
