package org.newspeaklanguage.compiler.ast;

import java.util.List;

public class Block extends CodeUnit {
  
  private final List<String> arguments;
  
  Block(List<String> arguments, List<Slot> temps, List<AstNode> body) {
    super(temps, body);
    this.arguments = arguments;
  }
  
  public List<String> arguments() { return arguments; }

}
