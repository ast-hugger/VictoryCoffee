/*
 * Copyright (c) 2016 Vassili Bykov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.newspeaklanguage.compiler.ast;

import java.util.LinkedList;
import java.util.List;

import org.newspeaklanguage.compiler.semantics.AnalyzerStage1;
import org.newspeaklanguage.compiler.semantics.MethodScope;

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

  @Override
  public MethodScope scope() {
    return (MethodScope) scope;
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
