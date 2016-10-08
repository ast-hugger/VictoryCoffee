package org.newspeaklanguage.compiler.ast;

import java.util.LinkedList;
import java.util.List;

public class Method extends CodeUnit {
  
  private final MessagePattern messagePattern;
  /**
   * A list of all blocks, including blocks inside blocks, contained in this
   * method. Populated by {@link Stage1Analyzer}.
   */
  private final List<Block> allBlocks = new LinkedList<Block>();

  Method(MessagePattern messagePattern, List<SlotDefinition> temps, List<AstNode> body) {
    super(temps, body);
    this.messagePattern = messagePattern;
  }
  
  public MessagePattern messagePattern() { return messagePattern; }
  public List<Argument> arguments() { return messagePattern.arguments(); }

  public void addBlock(Block block) {
    allBlocks.add(block);
  }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitMethod(this);
  }
}
