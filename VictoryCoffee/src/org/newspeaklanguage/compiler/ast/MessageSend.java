package org.newspeaklanguage.compiler.ast;

import java.util.List;

/**
 * A message send. Note that in AST this also covers slot and temporary variable
 * references, which syntactically are implicit receiver sends. Those are
 * recognized as slot references only later during semantic analysis once we
 * have the scopes to know what name means what.
 * 
 * @author vassili
 *
 */
public class MessageSend extends AstNode {
  
  /** The message receiver; {@code null} for here sends. */
  private final AstNode receiver;
  private final String selector;
  private final List<AstNode> arguments;
  
  MessageSend(AstNode receiver, String selector, List<AstNode> arguments) {
    this.receiver = receiver;
    this.selector = selector;
    this.arguments = arguments;
  }

  public AstNode receiver() { return receiver; }
  public String selector() { return selector; }
  public List<AstNode> arguments() { return arguments; }
  
  public boolean isImplicitReceiverSend() { return receiver == null; }
  public boolean isSingleName() { return receiver == null && arguments.isEmpty(); }
}
