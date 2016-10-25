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

import org.newspeaklanguage.compiler.semantics.RewrittenNodeVisitor;

public abstract class AstNode {
  
  public abstract void accept(AstNodeVisitor visitor);

  /**
   * Visit nodes of the tree, choosing to visit rewritten nodes instead of the originals
   * when available.
   */
  public void accept(RewrittenNodeVisitor visitor) {
    accept((AstNodeVisitor) visitor);
  }

}
