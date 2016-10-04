package org.newspeaklanguage.compiler.ast;

import java.util.List;

public class Block extends CodeUnit {
  
  private final List<Argument> arguments;
  
  Block(List<Argument> arguments, List<SlotDefinition> temps, List<AstNode> body) {
    super(temps, body);
    this.arguments = arguments;
  }
  
  public List<Argument> arguments() { return arguments; }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitBlock(this);
  }

}
