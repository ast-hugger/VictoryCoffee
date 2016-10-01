package org.newspeaklanguage.compiler.ast;

public class LiteralString extends AstNode {
  
  private final String value;
  
  LiteralString(String value) {
    this.value = value;
  }
  
  public String value() { return value; }

}
