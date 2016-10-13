package org.newspeaklanguage.runtime;

import org.newspeaklanguage.compiler.Descriptor;

import java.lang.invoke.MethodHandle;

/**
 * A Newspeak closure: the result of evaluating a block expression.
 */
public class Closure extends Builtins.BuiltinObject {

  public static final String INTERNAL_CLASS_NAME = Descriptor.internalClassName(Closure.class);

  public static String constructorDescriptor(int copiedArgCount) {
    StringBuilder builder = new StringBuilder(100);
    builder
        .append("(")
        .append(Descriptor.ofType(MethodHandle.class))
        .append(Descriptor.ofType(StandardObject.class));
    for (int i = 0; i < copiedArgCount; i++) {
      builder.append(Object.TYPE_DESCRIPTOR);
    }
    builder.append(")V");
    return builder.toString();
  }

  /**
   * The handle to the implementation method of the block, retrieved from the BlockHandle
   * and bound to the copied receiver.
   */
  protected final MethodHandle implementation;
  /**
   * The number of arguments of the implementation method. This is currently the same
   * as the number of the original block arguments.
   */
  protected final int arity;

  public Closure(MethodHandle blockMethodHandle, StandardObject copiedSelf) {
    implementation = blockMethodHandle.bindTo(copiedSelf);
    arity = implementation.type().parameterCount();
  }

  public Closure(MethodHandle blockMethodHandle, StandardObject copiedSelf, Object copied1) {
    implementation = blockMethodHandle.bindTo(copiedSelf).bindTo(copied1);
    arity = implementation.type().parameterCount();
  }

  public Closure(MethodHandle blockMethodHandle, StandardObject copiedSelf, Object copied1, Object copied2) {
    implementation = blockMethodHandle.bindTo(copiedSelf).bindTo(copied1).bindTo(copied2);
    arity = implementation.type().parameterCount();
  }

  public Object $value() {
    if (arity != 0) {
      throw new RuntimeError("this closure expects " + arity + " arguments");
    }
    try {
      return (Object) implementation.invokeExact();
    } catch (Throwable e) {
      throw new RuntimeError("closure invocation error", e);
    }
  }

  public Object $value$(Object arg) {
    if (arity != 1) {
      throw new RuntimeError("this closure expects " + arity + " arguments");
    }
    try {
      return (Object) implementation.invokeExact(arg);
    } catch (Throwable e) {
      throw new RuntimeError("closure invocation error", e);
    }
  }

  public Object $value$value$(Object arg1, Object arg2) {
    if (arity != 2) {
      throw new RuntimeError("this closure expects " + arity + " arguments");
    }
    try {
      return (Object) implementation.invokeExact(arg1, arg2);
    } catch (Throwable e) {
      throw new RuntimeError("closure invocation error", e);
    }
  }

}
