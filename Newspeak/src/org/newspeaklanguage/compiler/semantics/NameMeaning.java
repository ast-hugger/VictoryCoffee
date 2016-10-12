package org.newspeaklanguage.compiler.semantics;

import java.util.Optional;

import org.newspeaklanguage.compiler.ast.MessageSendNoReceiver;

/**
 * This is attached to a {@link MessageSendNoReceiver} to indicate the meaning
 * of the send. The possibilities are:
 * <ul>
 * <li>A local variable reference (an argument or a method temp). The variable
 * might be from one or more scopes up.
 * <li>A message send to an enclosing object (the selector is lexically visible
 * in an outer class). This includes an outer class slot access.
 * <li>A message send to self (the selector is not lexically visible).
 * </ul>
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public abstract class NameMeaning {

  public boolean isLocalVarReference() {
    return false;
  }

  public Optional<ScopeEntry> lexicalDefinition() {
    return Optional.empty();
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
