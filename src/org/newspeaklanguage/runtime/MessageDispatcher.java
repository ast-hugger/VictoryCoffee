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

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

import org.newspeaklanguage.compiler.Descriptor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Opcodes;

public final class MessageDispatcher {

  public static Handle bootstrapHandle() {
    return new Handle(
        Opcodes.H_INVOKESTATIC,
        Descriptor.internalClassName(MessageDispatcher.class),
        "bootstrap",
        MethodType.methodType(CallSite.class, Lookup.class, String.class, MethodType.class)
            .toMethodDescriptorString(),
        false);
  }
  
  public static CallSite bootstrap(Lookup callSiteLookup, String name, MethodType callSiteType)
      throws NoSuchMethodException, IllegalAccessException
  {
    // The call site type includes the receiver as a parameter, but in method signatures
    // only the actual arguments count, so the arity we want is 1 less.
    int arity = callSiteType.parameterCount() - 1;
    return MessageSendSite.create(
        callSiteLookup,
        MethodHandles.lookup(),
        name,
        callSiteType,
        "dispatch",
        dispatchType(arity));
  }
  
  /**
   * Unary message dispatch.
   */
  public static Object dispatch(MessageSendSite callSite, Object receiver) throws Throwable {
    if (receiver == null) throw new RuntimeError("null receiver not supported yet");
    Class<?> methodContainer = methodContainerOf(receiver);
    MethodHandle method;
    try {
      // TODO is the following fast enough or should we remember the findings in methodContainerOf?
      if (StandardObject.class.isAssignableFrom(methodContainer)) {
        method = callSite.lookup()
            .findVirtual(methodContainer, callSite.methodName(), methodType(callSite.type().parameterCount() - 1))
            .asType(callSite.type());
      } else {
        method = callSite.lookup()
            .findStatic(methodContainer, callSite.methodName(), methodType(callSite.type().parameterCount()))
            .asType(callSite.type());
      }
    } catch (NoSuchMethodException e) {
      throw new MessageNotUnderstood(receiver, callSite.methodName(), new Object[0]);
    }
    // FIXME receiver.getClass() will not work for null receivers
    callSite.addInlineCache(receiver.getClass(), method);
    return method.invoke(receiver);
  }
  
  /**
   * Single argument message dispatch (binary and 1-arg keyword messages).
   */
  public static Object dispatch(MessageSendSite callSite, Object receiver, Object arg1)
      throws Throwable
  {
    if (receiver == null) throw new RuntimeError("null receiver not supported yet");
    Class<?> methodContainer = methodContainerOf(receiver);
    MethodHandle method;
    try {
      if (StandardObject.class.isAssignableFrom(methodContainer)) {
        method = callSite.lookup()
            .findVirtual(methodContainer, callSite.methodName(), methodType(callSite.type().parameterCount() - 1))
            .asType(callSite.type());
      } else {
        method = callSite.lookup()
            .findStatic(methodContainer, callSite.methodName(), methodType(callSite.type().parameterCount()))
            .asType(callSite.type());
      }
    } catch (NoSuchMethodException e) {
      throw new MessageNotUnderstood(receiver, callSite.methodName(), new Object[]{arg1});
    }
    callSite.addInlineCache(receiver.getClass(), method);
    return method.invoke(receiver, arg1);
  }
  
  /**
   * Two -argument message dispatch.
   */
  public static Object dispatch(MessageSendSite callSite, Object receiver, Object arg1, Object arg2)
      throws Throwable
  {
    if (receiver == null) throw new RuntimeError("null receiver not supported yet");
    Class<?> methodContainer = methodContainerOf(receiver);
    MethodHandle method;
    try {
      if (StandardObject.class.isAssignableFrom(methodContainer)) {
        method = callSite.lookup()
            .findVirtual(methodContainer, callSite.methodName(), methodType(callSite.type().parameterCount() - 1))
            .asType(callSite.type());
      } else {
        method = callSite.lookup()
            .findStatic(methodContainer, callSite.methodName(), methodType(callSite.type().parameterCount()))
            .asType(callSite.type());
      }
    } catch (NoSuchMethodException e) {
      throw new MessageNotUnderstood(receiver, callSite.methodName(), new Object[]{arg1, arg2});
    }
    callSite.addInlineCache(receiver.getClass(), method);
    return method.invoke(receiver, arg1, arg2);
  }
  
  /**
   * Three-argument message dispatch.
   */
  public static Object dispatch(MessageSendSite callSite, Object receiver, Object arg1, Object arg2,
                                  Object arg3)
      throws Throwable
  {
    if (receiver == null) throw new RuntimeError("null receiver not supported yet");
    Class<?> methodContainer = methodContainerOf(receiver);
    MethodHandle method;
    try {
      if (StandardObject.class.isAssignableFrom(methodContainer)) {
        method = callSite.lookup()
            .findVirtual(methodContainer, callSite.methodName(), methodType(callSite.type().parameterCount() - 1))
            .asType(callSite.type());
      } else {
        method = callSite.lookup()
            .findStatic(methodContainer, callSite.methodName(), methodType(callSite.type().parameterCount()))
            .asType(callSite.type());
      }
    } catch (NoSuchMethodException e) {
      throw new MessageNotUnderstood(receiver, callSite.methodName(), new Object[]{arg1, arg2, arg3});
    }
    callSite.addInlineCache(receiver.getClass(), method);
    return method.invoke(receiver, arg1, arg2, arg3);
  }
  
  private static MethodType dispatchType(int arity) {
    MethodType type = MethodType.methodType(Object.class, MessageSendSite.class, Object.class);
    for (int i = 0; i < arity; i++) {
      type = type.appendParameterTypes(Object.class);
    }
    return type;
  }
  
  private static MethodType methodType(int arity) {
    MethodType type = MethodType.methodType(Object.class);
    for (int i = 0; i < arity; i++) {
      type = type.appendParameterTypes(Object.class);
    }
    return type;
  }

  private static Class<?> methodContainerOf(Object object) {
    if (object instanceof StandardObject) return object.getClass();
    if (object == null) return Builtins.UndefinedObjectMethods.class;
    if (object instanceof String) return Builtins.StringMethods.class;
    if (object instanceof Boolean) return Builtins.BooleanMethods.class;
    if (object instanceof Number) return Builtins.NumberMethods.class;
    throw new IllegalArgumentException("unrecognized artifact used as a Newspeak object: " + object);
  }
}
