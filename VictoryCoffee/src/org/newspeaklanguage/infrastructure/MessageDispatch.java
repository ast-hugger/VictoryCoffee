package org.newspeaklanguage.infrastructure;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.runtime.Object;

public final class MessageDispatch {

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
  
  public static Object dispatch(MessageSendSite callSite, Object receiver) throws Throwable {
    Class<?> receiverClass = receiver.getClass();
    String methodName = NamingPolicy.methodNameForSelector(callSite.selector());
    MethodHandle method = callSite.lookup()
        .findVirtual(receiverClass, methodName, methodType(callSite.type().parameterCount() - 1))
        .asType(callSite.type());
    callSite.addInlineCache(receiverClass, method);
    return (Object) method.invoke(receiver);
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
