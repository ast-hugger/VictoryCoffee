/*
 * Copyright (c) 2016 Vassili Bykov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

  public boolean isLexicalVarReference() {
    return false;
  }

  public boolean isSendToEnclosingObject() {
    return false;
  }

  public boolean isSelfSend() {
    return false;
  }

  public LexicalVarReference asLexicalVarReference() {
    return (LexicalVarReference) this;
  }

  public SendToEnclosingObject asSendToEnclosingObject() {
    return (SendToEnclosingObject) this;
  }

  public SelfSend asSelfSend() {
    return (SelfSend) this;
  }

  public <T extends ScopeEntry> Optional<T> lexicalDefinition() {
    return Optional.empty();
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }

}
