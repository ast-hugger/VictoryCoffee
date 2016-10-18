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

public class Category extends AstNode {
  
  private final String name;
  private final List<Method> methods;
  
  Category(String name, List<Method> methods) {
    this.name = name;
    this.methods = methods;
  }
  
  public String name() { return name; }
  public List<Method> methods() { return methods; }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitCategory(this);
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName()
        + "(" + name + ")"
        + methods.toString();
  }
}
