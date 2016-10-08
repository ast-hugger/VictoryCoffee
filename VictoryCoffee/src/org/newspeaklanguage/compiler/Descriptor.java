package org.newspeaklanguage.compiler;

import java.lang.invoke.MethodType;

/**
 * A utility class for generating JVM descriptor strings.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public final class Descriptor {
  
  public static String internalClassName(Class<?> klass) {
    return klass.getName().replace('.', '/');
  }
  
  public static String ofType(Class<?> klass) {
    return "L" + internalClassName(klass) + ";";
  }
  
  public static String ofMethod(Class<?> resultType, Class<?>... argTypes) {
    return MethodType.methodType(resultType, argTypes).toMethodDescriptorString();
  }

}
