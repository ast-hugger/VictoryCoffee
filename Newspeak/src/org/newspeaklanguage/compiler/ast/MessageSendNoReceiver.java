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

import org.newspeaklanguage.compiler.semantics.RewrittenNodeVisitor;

/**
 * A message send with implicit receiver. This includes what would count as slot references.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class MessageSendNoReceiver extends AstNode {

  protected final String selector;
  protected final List<AstNode> arguments;
  protected final boolean isSetterSend;
  
  private AstNode rewritten;

  public MessageSendNoReceiver(String selector, boolean isSetterSend, List<AstNode> arguments) {
    this.selector = selector;
    this.arguments = arguments;
    this.isSetterSend = isSetterSend;
  }

  public String selector() { return selector; }
  public List<AstNode> arguments() { return arguments; }
  public boolean isSetterSend() { return isSetterSend; }

  public int arity() {
    return arguments.size();
  }
  
  /**
   * A message send with no receiver and no arguments is potentially a name:
   * something that in the regular Smalltalk understanding would be a variable
   * reference. However, in Newspeak it still can be just a self send with no
   * receiver.
   */
  public boolean isName() {
    return arity() == 0;
  }

  public AstNode rewritten() {
    return rewritten;
  }

  public void setRewritten(AstNode rewrittenNode) {
    this.rewritten = rewrittenNode;
  }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitMessageSendNoReceiver(this);
  }

  @Override
  public void accept(RewrittenNodeVisitor visitor) {
    if (rewritten != null) {
      rewritten.accept(visitor);
    } else {
      super.accept(visitor);
    }
  }
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName()
        + "(" + selector + ")"
        + "->" + rewritten;
  }
}