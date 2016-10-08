package org.newspeaklanguage.runtime;

import java.lang.invoke.MethodType;

public final class Descriptors {
  
  public static String internalClassName(Class<?> klass) {
    return klass.getName().replace('.', '/');
  }
  
  public static String valueTypeDescriptor(Class<?> klass) {
    return "L" + internalClassName(klass) + ";";
  }
  
  public static String methodTypeDescriptor(Class<?> resultType, Class<?>... argTypes) {
    return MethodType.methodType(resultType, argTypes).toMethodDescriptorString();
  }

}
