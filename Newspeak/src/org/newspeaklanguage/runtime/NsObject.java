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

package org.newspeaklanguage.runtime;

import org.newspeaklanguage.compiler.Descriptor;

/**
 * This used to be a superclass of all objects manipulated by compiled Newspeak code.
 * Later we changed to mixing and matching StandardObjects, Java boxed primitives,
 * and {@code null}. This no longer functions as a descriptor of every Newspeak value
 * on the stacks in Java method frames. But, we still use subclasses of this
 * to simulate the classes that things like {@code String} and {@code null}
 * are instances of in the Newspeak sense.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public abstract class NsObject {
  // The class is intentionally named NsObject to avoid confusion with the standard Object
  // which we use in type signatures.

  // These now reflect our actual usage of the Java Object as the catch-all type.
  public static final String INTERNAL_CLASS_NAME = Descriptor.internalClassName(java.lang.Object.class);
  public static final String TYPE_DESCRIPTOR = "L" + INTERNAL_CLASS_NAME + ";";

  public abstract ObjectFactory nsClass();
  
  public Object $class() {
    return nsClass();
  }
  
  public Object $printString() {
    String name = this.nsClass().name();
    String article = isVowel(name.charAt(0)) ? "an" : "a";
    return Builtins.string("<" + article + " " + name + ">");
  }
  
  private boolean isVowel(char c) {
    return "AEIOUaeiou".indexOf(c) != -1;
  }
}
