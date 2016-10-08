package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.NamingPolicy;

/**
 * A scope established by a method. The names defined by this scope are accessor
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
public class MethodScope extends Scope<MethodScopeEntry> {
  
  protected int lastVarIndex = 0;
  
  MethodScope(Scope<? extends ScopeEntry> parent) {
    super(parent);
  }
  
  @Override
  public ClassScope lookupClass(String name) {
    return parent == null ? null : parent.lookupClass(name);
  }
  
  @Override
  public boolean isMethodScope() { return true; }
  
  @Override
  public MethodScopeEntry define(String name) {
    if (NamingPolicy.isSetterSelector(name)) {
      MethodScopeEntry getterEntry = lookupLocally(NamingPolicy.getterForSetter(name));
      assert getterEntry != null;
      MethodScopeEntry newEntry = new MethodScopeEntry(name, this, getterEntry.index());
      names.put(name, newEntry);
      return newEntry;
    } else {
      return super.define(name);
    }
  }
  
  @Override
  protected MethodScopeEntry createScopeEntry(String name) {
    return new MethodScopeEntry(name, this, ++lastVarIndex);
  }
}
