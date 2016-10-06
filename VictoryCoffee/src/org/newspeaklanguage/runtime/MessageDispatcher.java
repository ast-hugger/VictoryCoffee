package org.newspeaklanguage.runtime;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

import org.newspeaklanguage.compiler.NamingPolicy;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Opcodes;

public final class MessageDispatcher {

  public static Handle bootstrapHandle() {
    return new Handle(
        Opcodes.H_INVOKESTATIC,
        MessageDispatcher.class.getName().replace('.', '/'),
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
    Class<?> receiverClass = receiver.getClass();
    String methodName = NamingPolicy.methodNameForSelector(callSite.selector());
    MethodHandle method;
    try {
      method = callSite.lookup()
        .findVirtual(receiverClass, methodName, methodType(callSite.type().parameterCount() - 1))
        .asType(callSite.type());
    } catch (NoSuchMethodException e) {
      throw new MessageNotUnderstood(receiver, callSite.selector(), new Object[0]);
    }
    callSite.addInlineCache(receiverClass, method);
    return (Object) method.invoke(receiver);
  }
  
  /**
   * Single argument message dispatch (binary and 1-arg keyword messages).
   */
  public static Object dispatch(MessageSendSite callSite, Object receiver, Object arg1)
      throws Throwable
  {
    Class<?> receiverClass = receiver.getClass();
    String methodName = NamingPolicy.methodNameForSelector(callSite.selector());
    MethodHandle method;
    try {
      method = callSite.lookup()
        .findVirtual(receiverClass, methodName, methodType(callSite.type().parameterCount() - 1))
        .asType(callSite.type());
    } catch (NoSuchMethodException e) {
      throw new MessageNotUnderstood(receiver, callSite.selector(), new Object[]{arg1});
    }
    callSite.addInlineCache(receiverClass, method);
    return (Object) method.invoke(receiver, arg1);
  }
  
  /**
   * Two -argument message dispatch.
   */
  public static Object dispatch(MessageSendSite callSite, Object receiver, Object arg1, Object arg2)
      throws Throwable
  {
    Class<?> receiverClass = receiver.getClass();
    String methodName = NamingPolicy.methodNameForSelector(callSite.selector());
    MethodHandle method;
    try {
      method = callSite.lookup()
        .findVirtual(receiverClass, methodName, methodType(callSite.type().parameterCount() - 1))
        .asType(callSite.type());
    } catch (NoSuchMethodException e) {
      throw new MessageNotUnderstood(receiver, callSite.selector(), new Object[]{arg1, arg2});
    }
    callSite.addInlineCache(receiverClass, method);
    return (Object) method.invoke(receiver, arg1, arg2);
  }
  
  /**
   * Three-argument message dispatch.
   */
  public static Object dispatch(MessageSendSite callSite, Object receiver, Object arg1, Object arg2,
      Object arg3) 
      throws Throwable
  {
    Class<?> receiverClass = receiver.getClass();
    String methodName = NamingPolicy.methodNameForSelector(callSite.selector());
    MethodHandle method;
    try {
      method = callSite.lookup()
        .findVirtual(receiverClass, methodName, methodType(callSite.type().parameterCount() - 1))
        .asType(callSite.type());
    } catch (NoSuchMethodException e) {
      throw new MessageNotUnderstood(receiver, callSite.selector(), new Object[]{arg1, arg2, arg3});
    }
    callSite.addInlineCache(receiverClass, method);
    return (Object) method.invoke(receiver, arg1, arg2, arg3);
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
}
