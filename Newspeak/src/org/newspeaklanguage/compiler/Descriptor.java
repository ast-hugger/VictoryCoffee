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

import org.newspeaklanguage.runtime.NsObject;

import java.lang.invoke.MethodType;

/**
 * A utility class for generating JVM descriptor strings.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public final class Descriptor {

  public static final String OBJECT_INTERNAL_CLASS_NAME = internalClassName(Object.class);
  public static final String OBJECT_TYPE_DESCRIPTOR = ofType(Object.class);
  public static final String INT_TYPE_DESCRIPTOR = "I";

  public static String internalClassName(Class<?> klass) {
    return klass.getName().replace('.', '/');
  }
  
  public static String ofType(Class<?> klass) {
    return "L" + internalClassName(klass) + ";";
  }

  /**
   * Return a method descriptor of a Java method with the specified result and argument types.
   */
  public static String ofMethod(Class<?> resultType, Class<?>... argTypes) {
    return MethodType.methodType(resultType, argTypes).toMethodDescriptorString();
  }

  /**
   * Return a method descriptor string for a Java method implementing
   * a Newspeak method of the specified arity. The implementation has
   * twice the number of arguments; each Newspeak argument becomes an
   * Object/primitive int pair.
   */
  public static String ofMethodImplMethod(int arity) {
    StringBuilder result = new StringBuilder();
    result.append("(");
    for (int i = 0; i < arity; i++) {
      result
          .append(OBJECT_TYPE_DESCRIPTOR)
          .append("I");
    }
    result.append(")");
    // One or the other: return I normally and object via an exception or the other way around:
//    result.append(Descriptor.INT_TYPE_DESCRIPTOR);
    result.append(Descriptor.OBJECT_TYPE_DESCRIPTOR);
    return result.toString();
  }



}
