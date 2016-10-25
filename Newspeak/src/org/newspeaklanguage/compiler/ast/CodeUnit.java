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

import org.newspeaklanguage.compiler.semantics.CodeScope;

public abstract class CodeUnit extends AstNode {
  
  private final List<SlotDefinition> temps;
  private final List<AstNode> body;
  
  protected CodeScope scope;
  
  CodeUnit(List<SlotDefinition> temps, List<AstNode> body) {
    this.temps = temps;
    this.body = body;
  }
  
  
  public abstract List<Argument> arguments();
  public List<SlotDefinition> temps() { return temps; }
  public List<AstNode> body() { return body; }
  
  public CodeScope scope() { return scope; }
  public void setScope(CodeScope scope) { this.scope = scope; }

}
