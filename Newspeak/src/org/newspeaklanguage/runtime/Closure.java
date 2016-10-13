package org.newspeaklanguage.runtime;

import org.newspeaklanguage.compiler.Descriptor;

import java.lang.invoke.MethodHandle;

/**
 * A Newspeak closure: the result of evaluating a block expression.
 */
public class Closure extends Builtins.BuiltinObject {

  public static final String INTERNAL_CLASS_NAME = Descriptor.internalClassName(Closure.class);
  public static final int MAX_POSITIONAL_ARGC = 4;

  /**
   * Return a descriptor string for a Closure constructor that will accept the specified
   * number of copied values (in addition to the copied self which is always present).
   */
  public static String constructorDescriptor(int copiedArgCount) {
    StringBuilder builder = new StringBuilder(100);
    builder
        .append("(")
        .append(Descriptor.ofType(MethodHandle.class))
        .append(Descriptor.ofType(StandardObject.class));
    if (copiedArgCount <= MAX_POSITIONAL_ARGC) {
      for (int i = 0; i < copiedArgCount; i++) {
        builder.append(Object.TYPE_DESCRIPTOR);
      }
    } else {
      builder.append("[").append(Object.TYPE_DESCRIPTOR);
    }
    builder.append(")V");
    return builder.toString();
  }

  /**
   * The handle to the implementation method of the block, retrieved from the BlockHandle
   * and bound to the copied receiver and copied values, if any.
   */
  protected final MethodHandle implementation;
  /**
   * The number of arguments of the implementation method after it'e been bound to all
   * the copied values. In other words, it's the arity of the original block.
   */
  protected final int arity;

  public Closure(MethodHandle implMethodHandle, StandardObject copiedSelf) {
    this(implMethodHandle.bindTo(copiedSelf));
  }

  public Closure(MethodHandle implMethodHandle, StandardObject copiedSelf, Object copied1) {
    this(implMethodHandle.bindTo(copiedSelf).bindTo(copied1));
  }

  public Closure(MethodHandle implMethodHandle, StandardObject copiedSelf, Object copied1, Object copied2) {
    this(implMethodHandle.bindTo(copiedSelf).bindTo(copied1).bindTo(copied2));
  }

  public Closure(MethodHandle implMethodHandle, StandardObject copiedSelf, Object copied1, Object copied2,
                 Object copied3) {
    this(implMethodHandle
        .bindTo(copiedSelf)
        .bindTo(copied1)
        .bindTo(copied2)
        .bindTo(copied3));
  }

  public Closure(MethodHandle implMethodHandle, StandardObject copiedSelf, Object copied1, Object copied2,
                 Object copied3, Object copied4) {
    this(implMethodHandle
        .bindTo(copiedSelf)
        .bindTo(copied1)
        .bindTo(copied2)
        .bindTo(copied3)
        .bindTo(copied4));
  }

  public Closure(MethodHandle implMethodHandle, StandardObject copiedSelf, Object... copiedValues) {
    MethodHandle impl = implMethodHandle.bindTo(copiedSelf);
    for (int i = 0; i < copiedValues.length; i++) {
      impl = impl.bindTo(copiedValues[i]);
    }
    this.implementation = impl;
    this.arity = impl.type().parameterCount();
  }

  private Closure(MethodHandle alreadyBoundHandle) {
    this.implementation = alreadyBoundHandle;
    this.arity = alreadyBoundHandle.type().parameterCount();
  }

  public Object $value() {
    if (arity != 0) {
      throw new RuntimeError("this closure expects " + arity + " arguments, but was called with 0");
    }
    try {
      return (Object) implementation.invokeExact();
    } catch (Throwable e) {
      throw new RuntimeError("closure invocation error", e);
    }
  }

  public Object $value$(Object arg) {
    if (arity != 1) {
      throw new RuntimeError("this closure expects " + arity + " arguments, but was called with 1");
    }
    try {
      return (Object) implementation.invokeExact(arg);
    } catch (Throwable e) {
      throw new RuntimeError("closure invocation error", e);
    }
  }

  public Object $value$value$(Object arg1, Object arg2) {
    if (arity != 2) {
      throw new RuntimeError("this closure expects " + arity + " arguments, but was called with 2");
    }
    try {
      return (Object) implementation.invokeExact(arg1, arg2);
    } catch (Throwable e) {
      throw new RuntimeError("closure invocation error", e);
    }
  }

  public Object $value$value$value$(Object arg1, Object arg2, Object arg3) {
    if (arity != 3) {
      throw new RuntimeError("this closure expects " + arity + " arguments, but was called with 3");
    }
    try {
      return (Object) implementation.invokeExact(arg1, arg2, arg3);
    } catch (Throwable e) {
      throw new RuntimeError("closure invocation error", e);
    }
  }

  public Object $value$value$value$value$(Object arg1, Object arg2, Object arg3, Object arg4) {
    if (arity != 4) {
      throw new RuntimeError("this closure expects " + arity + " arguments, but was called with 4");
    }
    try {
      return (Object) implementation.invokeExact(arg1, arg2, arg3, arg4);
    } catch (Throwable e) {
      throw new RuntimeError("closure invocation error", e);
    }
  }

}
