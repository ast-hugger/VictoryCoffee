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

import org.newspeaklanguage.compiler.semantics.ClassScope;

public class Outer extends AstNode {

  private final String name;
  
  // Set by the analyzer; the scope of the lexically visible class referenced by this node
  private ClassScope targetClassScope; 
  
  Outer(String name) {
    this.name = name;
  }
  
  public String name() { return name; }
  public ClassScope targetClassScope() { return targetClassScope; }
  public void setTargetClassScope(ClassScope scope) { this.targetClassScope = scope; }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitOuter(this);
  }

}
