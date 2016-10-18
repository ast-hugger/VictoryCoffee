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

package org.newspeaklanguage.compiler;

/**
 * A utility class for the various field, method, and methodName-related
 * translation, mangling, and unmangling.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public final class NamingPolicy {

  public static final String CLASS_DEF_FIELD_NAME = "$classDefinition$";

  public static String getterForSlot(String slotName) {
    return slotName;
  }
  
  public static String setterForSlot(String slotName) {
    return slotName + ":";
  }
  
  public static boolean isSetterSelector(String selector) {
    return selector.charAt(selector.length() - 1) == ':';
  }
  
  public static String getterForSetter(String setterSelector) {
    return setterSelector.substring(0, setterSelector.length() - 1);
  }
  
  public static String setterForGetter(String getterSelector) {
    return getterSelector + ":";
  }
  
  public static String getterMethodNameForSlot(String slotName) {
    return "$" + slotName;
  }

  public static String setterMethodNameForSlot(String slotName) {
    return "$" + slotName + "$";
  }

  public static String fieldNameForSlot(String slotName) {
    return "$" + slotName;
  }

  /**
   * Given a Newspeak selector assumed to be a slot getter or setter, return the
   * name of the actual slot. The name is in the original unmangled form.
   */
  public static String slotForSelector(String selector) {
    int lastCharIndex = selector.length() - 1;
    char last = selector.charAt(lastCharIndex);
    return last == ':'
        ? selector.substring(0, lastCharIndex)
        : selector;
  }
  
  /**
   * Given a Newspeak selector, return a method name to be used for a method
   * implementing that selector.
   */
  public static String methodNameForSelector(String selector) {
    StringBuilder name = new StringBuilder(selector.length() + 5);
    name.append('$');
    if (Character.isLetter(selector.charAt(0))) {
      // A unary or keyword selector
      int part = 0;
      for (String keyword : selector.split(":")) {
        if (part++ > 0) {
          name.append('$');
        }
        name.append(keyword);
      }
      if (selector.charAt(selector.length() - 1) == ':') {
        name.append('$');
      }
    } else {
      // A binary selector
      for (char c : selector.toCharArray()) {
        name.append('$');
        name.append(binarySelectorCharToToken(c));
      }
    }
    return name.toString();
  }
  
  public static String methodNameForClosure(String hostMethodSelector, int closureIndex) {
    return methodNameForSelector(hostMethodSelector) + "$closure" + closureIndex;
  }

  private static String binarySelectorCharToToken(char character) {
    switch (character) {
      case '+':
        return "plus";
      case '-':
        return "minus";
      case '*':
        return "star";
      case '/':
        return "slash";
      case '<':
        return "lt";
      case '>':
        return "gt";
      case '=':
        return "eq";
      case '~':
        return "tilda";
      case '&':
        return "amp";
      default:
        throw new IllegalArgumentException("Unrecognized binary selector char: " + character);
    }
  }

}
