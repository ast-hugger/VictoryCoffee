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

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;


public final class MessageSendSite extends MutableCallSite {

  public static MessageSendSite create(
      Lookup lookupAtCallSite,
      Lookup lookupAtImplementor,
      String methodName,
      MethodType callSiteType,
      String fallbackMethodName,
      MethodType fallbackMethodType)
        throws NoSuchMethodException, IllegalAccessException
  {
   MessageSendSite thisSite = new MessageSendSite(methodName, lookupAtCallSite, callSiteType);
   MethodHandle fallback = lookupAtImplementor
       .findStatic(lookupAtImplementor.lookupClass(), fallbackMethodName, fallbackMethodType)
       .bindTo(thisSite)
       .asType(callSiteType);
   thisSite.setTarget(fallback);
   return thisSite;
  }
  
  private static final MethodType CHECK_CLASS_TYPE =
      MethodType.methodType(boolean.class, Class.class, Object.class);
  
  public static boolean checkClass(final Class<?> expectedClass, Object object) {
    return object.getClass() == expectedClass;
  }
  
  /*
   * Instance side
   */
  
  private final String methodName;
  private final Lookup lookup;

  private int depth = 0;
  
  private MessageSendSite(String name, Lookup lookup, MethodType type) {
    super(type);
    this.methodName = name;
    this.lookup = lookup;
  }
  
  public String methodName() { return methodName; }
  public Lookup lookup() { return lookup; }
  
  public void addInlineCache(Class<?> expectedClass, MethodHandle specialization)
      throws NoSuchMethodException, IllegalAccessException
  {
    Lookup here = MethodHandles.lookup();
    MethodHandle typeCheck = here
        .findStatic(here.lookupClass(), "checkClass", CHECK_CLASS_TYPE)
        .bindTo(expectedClass);
    typeCheck = typeCheck.asType(
        typeCheck.type().changeParameterType(0, this.type().parameterType(0)));
    MethodHandle cacheNode =
        MethodHandles.guardWithTest(typeCheck, specialization, getTarget());
    setTarget(cacheNode);
    depth++;
//    System.out.println(expectedClass + ", " + depth + ", " + methodName);
  }
}
