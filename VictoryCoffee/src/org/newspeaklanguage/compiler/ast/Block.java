package org.newspeaklanguage.compiler.ast;

import java.util.List;

import org.newspeaklanguage.compiler.codegen.BlockDescriptor;

public class Block extends CodeUnit {
  
  private final List<Argument> arguments;
  private BlockDescriptor codegenDescriptor;
  
  Block(List<Argument> arguments, List<SlotDefinition> temps, List<AstNode> body) {
    super(temps, body);
    this.arguments = arguments;
  }
  
  public List<Argument> arguments() { return arguments; }
  public BlockDescriptor descriptor() { return codegenDescriptor; }
  
  public void setDescriptor(BlockDescriptor descriptor) {
    assert codegenDescriptor == null;
    this.codegenDescriptor = descriptor;
  }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitBlock(this);
  }

}
