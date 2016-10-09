package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.ast.NameDefinition;

/**
 * This is attached to a {@link MessageSendNoReceiver} to indicate meaning of
 * the send. The possibilities are:
 * <ul>
 * <li>A local variable reference (an argument or a method temp).
 * <li>A message send to an enclosing object (the selector is lexically visible
 * in an outer class). This includes an outer class slot access.
 * <li>A message send to self (the selector is not lexically visible).
 * </ul>
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public abstract class NameMeaning {

  /*
   * Factories
   */

  public static NameMeaning localVarReference(ScopeEntry definition, 
      Scope<? extends ScopeEntry> sourceScope) 
  {
    return new LocalVarReference(definition, sourceScope);
  }

  public static NameMeaning sendToEnclosingObject(ScopeEntry def) {
    return new SendToEnclosingObject(def);
  }

  public static NameMeaning selfSend() {
    return new SelfSend();
  }

  /*
   * Concrete subclasses
   */

  public static class LocalVarReference extends NameMeaning {

    /** The scope entry for the definition of the referenced variable. */
    private final ScopeEntry definition;
    /** The scope from which the reference is made. */
    private final Scope<? extends ScopeEntry> sourceScope;

    private LocalVarReference(ScopeEntry definition, Scope<? extends ScopeEntry> sourceScope) {
      assert definition.astNode() instanceof NameDefinition;
      this.definition = definition;
      this.sourceScope = sourceScope;
    }

    public ScopeEntry definition() {
      return definition;
    }
    
    public Scope<? extends ScopeEntry> sourceScope() {
      return sourceScope;
    }

    @Override
    public boolean isLocalVarReference() {
      return true;
    }
    
    public boolean isClean() {
      return sourceScope.equals(definition.scope());
    }
    
    public boolean isCopiable() {
      NameDefinition nameDef = (NameDefinition) definition.astNode();
      return sourceScope.parent().equals(definition.scope())
          && nameDef.isImmutable();
    }
  }

  public static class SendToEnclosingObject extends NameMeaning {

    private final ScopeEntry targetDefinition;

    private SendToEnclosingObject(ScopeEntry def) {
      this.targetDefinition = def;
    }

    public ScopeEntry definition() {
      return targetDefinition;
    }

    @Override
    public boolean isSendToEnclosingObject() {
      return true;
    }
  }

  public static class SelfSend extends NameMeaning {

    private SelfSend() {
    }

    @Override
    public boolean isSelfSend() {
      return true;
    }

  }

  /*
   * Instance side
   */

  public boolean isLocalVarReference() {
    return false;
  }

  public boolean isSendToEnclosingObject() {
    return false;
  }

  public boolean isSelfSend() {
    return false;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }

}
