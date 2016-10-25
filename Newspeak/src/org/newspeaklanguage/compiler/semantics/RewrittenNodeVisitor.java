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

import org.newspeaklanguage.compiler.ast.AstNodeVisitor;

/**
 * A visitor for processing a rewritten syntax tree. The accept() method
 * of AstNode specialized for this visitor will make it visit rewritten
 * nodes when they are available instead of the originals. The visitor
 * should be able to handle nodes inserted by the analyzer and not found
 * in the original AST produced by the parser.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
public interface RewrittenNodeVisitor extends AstNodeVisitor {

  void visitEnclosingObjectReference(EnclosingObjectReference enclosingObjectReference);
  void visitVariableReference(VariableReference localVariableReference);
  void visitVariableAssignment(VariableAssignment variableAssignment);
}
