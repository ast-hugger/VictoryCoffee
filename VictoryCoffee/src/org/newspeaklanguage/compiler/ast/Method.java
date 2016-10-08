package org.newspeaklanguage.compiler.ast;

import java.util.List;

public class Method extends CodeUnit {
  
  private final MessagePattern messagePattern;

  Method(MessagePattern messagePattern, List<SlotDefinition> temps, List<AstNode> body) {
    super(temps, body);
    this.messagePattern = messagePattern;
  }
  
  public MessagePattern messagePattern() { return messagePattern; }
  public List<Argument> arguments() { return messagePattern.arguments(); }

  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitMethod(this);
  }
}
