package org.newspeaklanguage.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Stored in a static field of an implementation class; caches a direct pointer
 * to the closure body method. This is not the actual closure object produced by
 * evaluating a {@code [...]} expression in Newspeak.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public final class ClosureLiteral {
  
  public static final String INTERNAL_CLASS_NAME = Descriptors.internalClassName(ClosureLiteral.class);
  public static final String TYPE_DESCRIPTOR = Descriptors.valueTypeDescriptor(ClosureLiteral.class);
  public static final String CONSTRUCTOR_DESCRIPTOR = 
      Descriptors.methodTypeDescriptor(void.class, Class.class, String.class);
  
  private final Class<? extends StandardObject> implementationClass;
  private final String methodName;
  private final MethodHandle methodHandle;
  
  public ClosureLiteral(Class<? extends StandardObject> implementationClass, String methodName) {
    this.implementationClass = implementationClass;
    this.methodName = methodName;
    try {
      this.methodHandle = MethodHandles.lookup().findVirtual(
          implementationClass,
          methodName,
          // TODO bogus type; the closure should supply it
          MethodType.methodType(Object.class));
    } catch (NoSuchMethodException | IllegalAccessException e) {
      throw new RuntimeError("closure initialization error", e);
    }
  }
  
  public Class<? extends StandardObject> implementationClass() { return implementationClass; }
  public String methodName() { return methodName; }
  public MethodHandle methodHandle() { return methodHandle; }
  
  public Object $value() {
    try {
      return (Object) methodHandle.invoke();
    } catch(Throwable e) {
      throw new RuntimeError("closure invocation error", e);
    }
  }
  
}