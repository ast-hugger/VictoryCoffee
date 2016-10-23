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

package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.ast.AstNode;

/**
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
public class VariableAssignment extends VariableReference {

  private final AstNode expression;
  private final boolean isPassThrough;

  VariableAssignment(AstNode expression, boolean isPassThrough, CodeScopeEntry definition, CodeScope sourceScope) {
    super(definition, sourceScope);
    this.expression = expression;
    this.isPassThrough = isPassThrough;
  }

  public AstNode expression() {
    return expression;
  }

  public boolean isPassThrough() {
    return isPassThrough;
  }

  @Override
  public void accept(RewrittenNodeVisitor visitor) {
    visitor.visitVariableAssignment(this);
  }
}
