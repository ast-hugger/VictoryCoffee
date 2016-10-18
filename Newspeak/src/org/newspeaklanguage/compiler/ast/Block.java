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

import java.util.List;

import org.newspeaklanguage.compiler.codegen.BlockDefiner;

public class Block extends CodeUnit {
  
  private final List<Argument> arguments;
  private BlockDefiner definer;
  
  Block(List<Argument> arguments, List<SlotDefinition> temps, List<AstNode> body) {
    super(temps, body);
    this.arguments = arguments;
  }
  
  @Override
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
