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

package org.newspeaklanguage.compiler.ast;

import java.util.List;

/**
 * A message send with an explicit receiver, just like in Smalltalk.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class MessageSendWithReceiver extends MessageSendNoReceiver {
  
  private final AstNode receiver;
  
  public MessageSendWithReceiver(AstNode receiver, String selector, List<AstNode> arguments) {
    super(selector, false, arguments);
    this.receiver = receiver;
  }

  public MessageSendWithReceiver(AstNode receiver, String selector, boolean isSetterSend, List<AstNode> arguments) {
    super(selector, isSetterSend, arguments);
    this.receiver = receiver;
  }

  public AstNode receiver() { return receiver; }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitMessageSendWithReceiver(this);
  }
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName()
        + "(" + receiver + " " + selector + ")";
  }
  
}
