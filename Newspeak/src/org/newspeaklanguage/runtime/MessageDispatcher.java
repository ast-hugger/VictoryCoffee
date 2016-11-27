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
    // The call site type has an added parameter for the receiver, and each parameter is doubles
    // to make it an Object/int pair.
    int arity = callSiteType.parameterCount() / 2 - 1;
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
  public static Object dispatch(MessageSendSite callSite, int intReceiver, Object receiver) throws Throwable {
    if (receiver == null) throw new RuntimeError("null receiver not supported yet");
    Class<?> methodContainer = receiver == NsObject.UNDEFINED ? Builtins.IntMethods.class : methodContainerOf(receiver);
    MethodHandle method;
    try {
      // TODO is isAsignableFrom() fast enough or should we remember the findings in methodContainerOf?
      if (StandardObject.class.isAssignableFrom(methodContainer)) {
        method = callSite.lookup()
            .findVirtual(methodContainer, callSite.methodName(), methodType(callSite.type().parameterCount()));
        method = method.asType(method.type().changeParameterType(0, Object.class));
        callSite.addInlineCache(receiver.getClass(), method);
        return method.invoke(receiver);
      } else {
        method = callSite.lookup()
            .findStatic(methodContainer, callSite.methodName(), staticMethodType(callSite.type().parameterCount()))
            .asType(callSite.type());
        callSite.addInlineCacheStatic(receiver.getClass(), method);
        return method.invoke(intReceiver, receiver);
      }
    } catch (NoSuchMethodException e) {
      throw new MessageNotUnderstood(receiver, callSite.methodName(), new Object[0]);
    }
    // FIXME receiver.getClass() will not work for null receivers
    // getClass() also works in the primitive case. In that case receiver.getClass() is NsObject.Undefined.class,
    // which works fine as the primitive specialization marker.
  }
  
  /**
   * Single argument message dispatch (binary and 1-arg keyword messages).
   */
  public static Object dispatch(MessageSendSite callSite, int intReceiver, Object receiver, int intArg1, Object arg1)
      throws Throwable
  {
    if (receiver == null) throw new RuntimeError("null receiver not supported yet");
    Class<?> methodContainer = receiver == NsObject.UNDEFINED ? Builtins.IntMethods.class : methodContainerOf(receiver);
    MethodHandle method;
    try {
      if (StandardObject.class.isAssignableFrom(methodContainer)) {
        method = callSite.lookup()
            .findVirtual(methodContainer, callSite.methodName(), methodType(callSite.type().parameterCount()));
        method = method.asType(method.type().changeParameterType(0, Object.class));
        callSite.addInlineCache(receiver.getClass(), method);
        return method.invoke(receiver, intArg1, arg1);
      } else {
        method = callSite.lookup()
            .findStatic(methodContainer, callSite.methodName(), staticMethodType(callSite.type().parameterCount()))
            .asType(callSite.type());
        callSite.addInlineCacheStatic(receiver.getClass(), method);
        return method.invoke(intReceiver, receiver, intArg1, arg1);
      }
    } catch (NoSuchMethodException e) {
      throw new MessageNotUnderstood(receiver, callSite.methodName(), new Object[]{arg1});
    }
  }
  
  /**
   * Two -argument message dispatch.
   */
  public static Object dispatch(MessageSendSite callSite, int intReceiver, Object receiver, int intArg1, Object arg1, int intArg2, Object arg2)
      throws Throwable
  {
    if (receiver == null) throw new RuntimeError("null receiver not supported yet");
    Class<?> methodContainer = receiver == NsObject.UNDEFINED ? Builtins.IntMethods.class : methodContainerOf(receiver);
    MethodHandle method;
    try {
      if (StandardObject.class.isAssignableFrom(methodContainer)) {
        method = callSite.lookup()
            .findVirtual(methodContainer, callSite.methodName(), methodType(callSite.type().parameterCount()));
        method = method.asType(method.type().changeParameterType(0, Object.class));
        callSite.addInlineCache(receiver.getClass(), method);
        return method.invoke(receiver, intArg1, arg1, intArg2, arg2);
      } else {
        method = callSite.lookup()
            .findStatic(methodContainer, callSite.methodName(), staticMethodType(callSite.type().parameterCount()))
            .asType(callSite.type());
        callSite.addInlineCache(receiver.getClass(), method);
        return method.invoke(intReceiver, receiver, intArg1, arg1, intArg2, arg2);
      }
    } catch (NoSuchMethodException e) {
      throw new MessageNotUnderstood(receiver, callSite.methodName(), new Object[]{arg1, arg2});
    }
  }
  
  /**
   * Three-argument message dispatch.
   */
  public static Object dispatch(MessageSendSite callSite, int intReceiver, Object receiver, int intArg1, Object arg1, int intArg2, Object arg2,
                                int intArg3, Object arg3)
      throws Throwable
  {
    if (receiver == null) throw new RuntimeError("null receiver not supported yet");
    Class<?> methodContainer = methodContainerOf(receiver);
    MethodHandle method;
    try {
      if (StandardObject.class.isAssignableFrom(methodContainer)) {
        method = callSite.lookup()
            .findVirtual(methodContainer, callSite.methodName(), methodType(callSite.type().parameterCount()));
        method = method.asType(method.type().changeParameterType(0, Object.class));
        callSite.addInlineCache(receiver.getClass(), method);
        return method.invoke(receiver, intArg1, arg1, intArg2, arg2, intArg3, arg3);
      } else {
        method = callSite.lookup()
            .findStatic(methodContainer, callSite.methodName(), staticMethodType(callSite.type().parameterCount()))
            .asType(callSite.type());
        callSite.addInlineCache(receiver.getClass(), method);
        return method.invoke(intReceiver, intArg1, arg1, intArg2, arg2, intArg3, arg3);
      }
    } catch (NoSuchMethodException e) {
      throw new MessageNotUnderstood(receiver, callSite.methodName(), new Object[]{arg1, arg2, arg3});
    }
  }

  /**
   * Return a MethodType for a fixed-arity #dispatch method that can handle the specified arity.
   * The type treats the receiver as the leading argument, so the receiver gets its own
   * full-blown Object/int pair in the signature.
   */
  private static MethodType dispatchType(int arity) {
    MethodType type = MethodType.methodType(Object.class, MessageSendSite.class, int.class, Object.class);
    for (int i = 0; i < arity; i++) {
      type = type.appendParameterTypes(int.class, Object.class);
    }
    return type;
  }

  /**
   * Return a MethodType for an implementation method (a Java method compiled from Newspeak method)
   * able to handle the specified parameter count of the call site. The call site has the receiver
   * Object/int pair as part of the signature, but the type we produce should only include the int.
   */
  private static MethodType methodType(int callSiteParameterCount) {
    MethodType type = MethodType.methodType(Object.class); // the result and the unused receiver int
    for (int i = 0; i < callSiteParameterCount / 2 - 1; i++) {
      type = type.appendParameterTypes(int.class, Object.class);
    }
    return type;
  }

  private static MethodType staticMethodType(int callSiteParameterCount) {
    MethodType type = MethodType.methodType(Object.class);
    for (int i = 0; i < callSiteParameterCount / 2; i++) {
      type = type.appendParameterTypes(int.class, Object.class);
    }
    return type;
  }

  /**
   * Determine and return the class in which methods of the specified object
   * should be looked up.
   */
  private static Class<?> methodContainerOf(Object object) {
    if (object instanceof StandardObject) return object.getClass();
    if (object == null) return Builtins.UndefinedObjectMethods.class;
    if (object instanceof String) return Builtins.StringMethods.class;
    if (object instanceof Boolean) return Builtins.BooleanMethods.class;
    if (object instanceof Number) return Builtins.NumberMethods.class;
    throw new IllegalArgumentException("unrecognized artifact used as a Newspeak object: " + object);
  }
}
