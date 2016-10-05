package org.newspeaklanguage.compiler;

import java.util.Arrays;

public final class NamingPolicy {

  public static String getterSelectorForSlot(String slotName) {
    return "$" + slotName;
  }

  public static String setterSelectorForSlot(String slotName) {
    return "$" + slotName + "$";
  }

  public static String fieldNameForSlot(String slotName) {
    return "$" + slotName;
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
