package org.newspeaklanguage.compiler.ast;

import java.util.List;

/**
 * A message send with an explicit receiver, just like in Smalltalk.
 * 
 * @author vassili
 *
 */
public class MessageSendWithReceiver extends MessageSendNoReceiver {
  
  private final AstNode receiver;
  
  MessageSendWithReceiver(AstNode receiver, String selector, List<AstNode> arguments) {
    super(selector, arguments, false);
    this.receiver = receiver;
  }

  public AstNode receiver() { return receiver; }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitMessageSendWithReceiver(this);
  }
  
}
