package org.newspeaklanguage.compiler.ast;

import java.util.LinkedList;
import java.util.List;

import org.newspeaklanguage.compiler.semantics.AnalyzerStage1;

public class Method extends CodeUnit implements NameDefinition {
  
  private final MessagePattern messagePattern;
  /**
   * A list of all blocks, including blocks inside blocks, contained in this
   * method. Populated by {@link AnalyzerStage1}.
   */
  private final List<Block> containedBlocks = new LinkedList<Block>();

  Method(MessagePattern messagePattern, List<SlotDefinition> temps, List<AstNode> body) {
    super(temps, body);
    this.messagePattern = messagePattern;
  }
  
  public MessagePattern messagePattern() { return messagePattern; }
  public String selector() { return messagePattern.selector(); }
  @Override
  public List<Argument> arguments() { return messagePattern.arguments(); }
  public List<Block> containedBlocks() { return containedBlocks; }
  
  @Override
  public String name() {
    return selector();
  }
  
  /**
   * A method defines a scope entry, so it has to say if the name it defines is
   * a mutable binding or not.
   */
  @Override
  public boolean isMutable() {
    return false;
  }

  public void addBlock(Block block) {
    containedBlocks.add(block);
  }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitMethod(this);
  }
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName()
        + "(" + selector() + ")";
  }
}
