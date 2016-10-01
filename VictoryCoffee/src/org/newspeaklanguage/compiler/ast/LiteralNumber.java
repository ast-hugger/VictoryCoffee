package org.newspeaklanguage.compiler.ast;

public class LiteralNumber extends AstNode {
  
  private final Number value;
  
  LiteralNumber(Number value) {
    this.value = value;
  }
  
  public Number value() { return value; }


}
