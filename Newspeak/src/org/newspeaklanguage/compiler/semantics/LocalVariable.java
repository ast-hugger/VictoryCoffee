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

/**
 * Instances of LocalVariable are used by {@link CodeScope scopes} to keep track
 * of the locals the scope has (defines), as either an argument or a temp.
 * The difference between this and a ScopeScopeEntry is that the latter
 * represents the <em>definition</em> of an argument or a temp in the Newspeak
 * source sense. This one is a definition in the implementation sense.
 * Compared to the original Newspeak source, an implementation method
 * has additional arguments for copied values of variables from outer
 * scopes.
 */
public class LocalVariable {

  private final String name;
  private final boolean isTemp;
  private boolean isBoxed;
  private int index;

  LocalVariable(String name, boolean isTemp, boolean isBoxed) {
    this.name = name;
    this.isTemp = isTemp;
    this.isBoxed = isBoxed;
  }

  public String name() {
    return name;
  }

  public boolean isTemp() {
    return isTemp;
  }

  public boolean isBoxed() {
    return isBoxed;
  }

  public void setIsBoxed(boolean isBoxed) {
    this.isBoxed = isBoxed;
  }

  public int index() {
    return index;
  }

  public int primitiveIndex() {
    return index + 1;
  }

  public void setIndex(int index) {
    this.index = index;
  }

}
