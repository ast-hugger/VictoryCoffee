package org.newspeaklanguage.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;

import org.newspeaklanguage.compiler.Descriptor;

/**
 * An object stored in a static field of an implementation class to represent a block
 * in one of the methods of the class. On initialization resolves and stored a direct pointer
 * to the method the block was compiled to.
 * <p>
 * The actual Closure object produced when the block is evaluated receives and holds
 * onto this handle.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public final class BlockHandle {
  
  public static final String INTERNAL_CLASS_NAME = Descriptor.internalClassName(BlockHandle.class);
  public static final String TYPE_DESCRIPTOR = Descriptor.ofType(BlockHandle.class);
  public static final String CONSTRUCTOR_DESCRIPTOR = 
      Descriptor.ofMethod(void.class, Class.class, String.class, int.class);
  
  /*
   * Return a {@link MethodType} of a block implementation method of the
   * specified arity.
   */
  public static MethodType methodType(int arity) {
    Class<?>[] argTypes = new Class<?>[arity];
    Arrays.setAll(argTypes, i -> Object.class);
    return MethodType.methodType(Object.class, argTypes);
  }
  
  /*
   * Instance Side
   */
  
  /** The class this handle belongs to. */
  private final Class<? extends StandardObject> implementationClass;
  /** The name of the static method of the implementationClass the block has been compiled to. */
  private final String methodName;
  /** The number of block arguments. */
  private final int arity;
  /** A method handle pointing at the implementation method. */
  private final MethodHandle methodHandle;
  
  public BlockHandle(Class<? extends StandardObject> implementationClass, String methodName, int arity) {
    this.implementationClass = implementationClass;
    this.methodName = methodName;
    this.arity = arity;
    try {
      this.methodHandle = MethodHandles.lookup().findVirtual(
          implementationClass,
          methodName,
          methodType(arity));
    } catch (NoSuchMethodException | IllegalAccessException e) {
      throw new RuntimeError("closure initialization error", e);
    }
  }
  
  /**
   * @return The class the handle belongs to. This is the Java class generated
   * to represent the Newspeak class which contains the method which contains the
   * block. 
   */
  public Class<? extends StandardObject> implementationClass() {
    return implementationClass;
  }
  
  /**
   * @return The name of the static method of the implementation class the block
   * has been compiled to (the implementation method).
   */
  public String methodName() {
    return methodName;
  }

  /**
   * @return The method handle invoking which runs the implementation method.
   */
  public MethodHandle methodHandle() {
    return methodHandle;
  }

  /**
   * @return The block number of arguments.
   */
  public int arity() {
    return arity;
  }
  
}