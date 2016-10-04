package org.newspeaklanguage.compiler.ast;

import java.util.List;

import org.newspeaklanguage.compiler.semantics.NameDefinition;

/**
 * A message send with implicit receiver. This includes what would count as slot references.
 * 
 * @author vassili
 *
 */
public class MessageSendNoReceiver extends AstNode {

  protected final String selector;
  protected final List<AstNode> arguments;
  protected final boolean isSetter;
  
  /**
   * Set at the analysis stage as needed to keep track of the name interpretation.
   */
  private NameDefinition lexicalDefinition;

  public MessageSendNoReceiver(String selector, List<AstNode> arguments, boolean isSetter) {
    this.selector = selector;
    this.arguments = arguments;
    this.isSetter = isSetter;
  }

  public String selector() { return selector; }
  public List<AstNode> arguments() { return arguments; }

  public int arity() {
    return arguments.size();
  }
  
  public NameDefinition lexicalDefition() {return lexicalDefinition; }
  public void setLexicalDefinition(NameDefinition def) { lexicalDefinition = def; }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitMessageSendNoReceiver(this);
  }
}