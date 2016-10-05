package org.newspeaklanguage.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * A definition of a Newspeak class.
 * 
 * <p>A Newspeak class is implemented by a triplet of entities. Given a class
 * source code, the compiler produces two of them: a {@link ClassDefinition}
 * instance holding onto a subclass of {@link Object}. The subclass is what's
 * instantiated to produce an instance of the Newspeak class. There is always
 * one ClassDefinition and one subclass of Object per Newspeak class. The third
 * entity comes into play when Newspeak code retrieves a Newspeak class from a
 * particular containing object. The metaobject received is a {@link ObjectFactory}. It
 * holds onto the corresponding ClassDefinition and the enclosing instance of
 * its parent Newspeak class. Thus, there can be multiple Class instances
 * representing a particular Newspeak class, if that class has been referenced
 * in the context of different enclosing objects.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class ClassDefinition {

  public static ClassDefinition create(java.lang.Class<? extends Object> implClass) {
    return new ClassDefinition(implClass);
  }
  
  /**
   * The subclass of Newspeak {@link Object} instances of which represent
   * instances of one of the Classes produced from this ClassDefinition.
   */
  private final java.lang.Class<? extends Object> implementation;
  
  private final MethodHandle constructor;
  
  ClassDefinition(java.lang.Class<? extends Object> implementation)
  {
    this.implementation = implementation;
    try {
      this.constructor = MethodHandles.lookup().findConstructor(
          implementation,
          MethodType.methodType(void.class, ObjectFactory.class));
    } catch (NoSuchMethodException | IllegalAccessException e) {
      throw new IllegalStateException("Failure initializing a class definition");
    }
  }
  
  public java.lang.Class<? extends Object> implementation() {
    return implementation;
  }
  
  public Object makeInstance(ObjectFactory nsClass) throws Throwable {
    return (Object) constructor.invoke(nsClass);
  }
  
}
