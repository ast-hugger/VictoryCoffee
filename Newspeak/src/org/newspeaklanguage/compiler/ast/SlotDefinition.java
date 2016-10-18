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

/**
 * A definition of a class slot or a method or block temporary variable.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class SlotDefinition extends AstNode implements NameDefinition {

  private final String name;
  private final boolean isMutable;
  private final AstNode initializer;

  SlotDefinition(String name, AstNode initializer, boolean isMutable) {
    this.name = name;
    this.initializer = initializer;
    this.isMutable = isMutable;
  }

  @Override
  public String name() {
    return name;
  }

  public AstNode initializer() {
    return initializer;
  }

  @Override
  public boolean isMutable() {
    return isMutable;
  }

  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitSlotDefinition(this);
  }
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName()
        + "(" + name + ")"
        + (isMutable ? "mutable" : "immutable");
  }

}
