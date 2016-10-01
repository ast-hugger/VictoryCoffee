package org.newspeaklanguage.compiler.ast;

public class SetterSend extends AstNode {

  private final String selector;
  private final AstNode argument;
  
  SetterSend(String selector, AstNode argument) {
    this.selector = selector;
    this.argument = argument;
  }
  
  public String selector() { return selector; }
  public AstNode argument() { return argument; }
  
}
