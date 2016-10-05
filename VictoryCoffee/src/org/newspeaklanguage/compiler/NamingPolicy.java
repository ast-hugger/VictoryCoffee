package org.newspeaklanguage.compiler;

import java.util.Arrays;

public final class NamingPolicy {
  
  public static String getterSelectorForSlot(String slotName) {
    return slotName;
  }
  
  public static String setterSelectorForSlot(String slotName) {
    return slotName + ":";
  }

  public static String fieldNameForSlot(String slotName) {
    return "_" + slotName;
  }
  
  public static String methodNameForSelector(String selector) {
    StringBuilder name = new StringBuilder("_");
    boolean first = true;
    for (String keyword : keywordsInSelector(selector)) {
      if (!first) {
        name.append("$");
      }
      name.append(keyword);
      first = false;
    }
    return name.toString();
  }
  
  private static String[] keywordsInSelector(String selector) {
    String[] result = selector.split(":");
    if (result[result.length - 1].isEmpty()) {
      return Arrays.copyOf(result, result.length - 1);
    }
    return result;
  }
}
