package org.newspeaklanguage.compiler.semantics;

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
  
  public static NameMeaning localVarReference(ScopeEntry def) {
    return new LocalVarReference(def);
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
    private final ScopeEntry definition;
    private LocalVarReference(ScopeEntry def) {
      this.definition = def;
    }
    public ScopeEntry definition() { return definition; }
    @Override
    public boolean isLocalVarReference() { return true; }
  }
  
  public static class SendToEnclosingObject extends NameMeaning {
    private final ScopeEntry targetDefinition;
    private SendToEnclosingObject(ScopeEntry def) {
      this.targetDefinition = def;
    }
    public ScopeEntry targetDefinition() { return targetDefinition; }
    @Override
    public boolean isSendToEnclosingObject() { return true; }
  }
  
  public static class SelfSend extends NameMeaning {
    private SelfSend() {
    }
    @Override
    public boolean isSelfSend() { return true; } 
  }
  
  /*
   * Instance side
   */
  
  public boolean isLocalVarReference() { return false; }
  public boolean isSendToEnclosingObject() { return false; }
  public boolean isSelfSend() { return false; }

}
