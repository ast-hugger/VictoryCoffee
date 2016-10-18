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

  public int index() {
    return index;
  }


  public boolean isBoxed() {
    return isBoxed;
  }

  public void setIsBoxed(boolean isBoxed) {
    this.isBoxed = isBoxed;
  }


  public void setIndex(int index) {
    this.index = index;
  }

}
