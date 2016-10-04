package org.newspeaklanguage.compiler.ast;

import java.util.List;

import org.newspeaklanguage.compiler.semantics.NameDefinition;
import org.newspeaklanguage.compiler.semantics.NameMeaning;

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
  private NameMeaning meaning;

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
  
  /**
   * A message send with no receiver and no arguments is potentially a name:
   * something that in the regular Smalltalk understanding would be a variable
   * reference. However, in Newspeak it still can be just a self send with no
   * receiver.
   */
  public boolean isName() {
    return arity() == 0;
  }
  
  /**
   * If the definition of the selector of this message is visible lexically,
   * return that definition.
   */
  public NameDefinition lexicalDefition() {return lexicalDefinition; }
  public void setLexicalDefinition(NameDefinition def) { lexicalDefinition = def; }
  
  public NameMeaning meaning() { return meaning; }
  public void setMeaning(NameMeaning meaning) { this.meaning = meaning; }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitMessageSendNoReceiver(this);
  }
}