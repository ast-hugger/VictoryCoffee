package org.newspeaklanguage.compiler.ast;

import java.util.List;

import org.newspeaklanguage.compiler.codegen.BlockDefiner;

public class Block extends CodeUnit {
  
  private final List<Argument> arguments;
  private BlockDefiner definer;
  
  Block(List<Argument> arguments, List<SlotDefinition> temps, List<AstNode> body) {
    super(temps, body);
    this.arguments = arguments;
  }
  
  public List<Argument> arguments() { return arguments; }
  public int arity() { return arguments.size(); }
  public BlockDefiner definer() { return definer; }
  
  public void setDefiner(BlockDefiner definer) {
    assert this.definer == null;
    this.definer = definer;
  }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitBlock(this);
  }
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName()
        + "(" + argumentsString() + ")";
  }
  
  private String argumentsString() {
    return arguments.stream()
        .map(each -> ":" + each.name())
        .reduce((a, b) -> a + " " + b)
        .orElse("");
  }

}
