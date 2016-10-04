package org.newspeaklanguage.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Collections;

/**
 * This is a class in the sense of an object factory in a context of a
 * containing object, not in the source code sense. In other words, this is the
 * object one gets when given an instance {@code c} of class (in the source code
 * sense) {@code C} with a nested class {@code N}, one asks for {@code c N}.
 * 
 * <p>The class in the source code sense is represented by {@link ClassDefinition}. A ClassDefinition is not a Newspeak object.
 * 
 * @author vassili
 *
 */
public class Class extends Object {

  private final ClassDefinition classDefinition;
  
  /**
   * A direct pointer to the constructor of the implementation class. 
   */
  private final MethodHandle constructor;
  
  // Public so call sites for outer sends can read this directly as a field
  public final Object[] enclosingObjects;

  /**
   * Produces a bogus instance which is an instance of itself. 
   * Provided for bootstrapping and testing to create the Class class.
   */
  Class() {
    super(null);
    this.classDefinition = null;
    this.constructor = null;
    this.enclosingObjects = null;
  }
  
  /**
   * The proper way to create a Newspeak class metaobject.
   * 
   * @param nsClass
   *          A class is a Newspeak object, so it's an instance of a Newspeak
   *          class. This is the class.
   * @param classDefinition
   *          The ClassDefinition describing the class.
   * @param container
   *          The enclosing object of this class. It is an instance of the class
   *          in which this class is nested.
   * @throws NoSuchMethodException
   * @throws IllegalAccessException
   */
  Class(Class nsClass, ClassDefinition classDefinition, Object container)
      throws NoSuchMethodException, IllegalAccessException
  {
    super(nsClass);
    this.classDefinition = classDefinition;
    constructor = MethodHandles.lookup().findConstructor(
        classDefinition.implementation(),
        MethodType.methodType(void.class, Class.class));
    
    // Capture the enclosing objects
    if (container == null) {
      this.enclosingObjects = new Object[0];
      return;
    }
    Object[] higherObjects = container.nsClass().enclosingObjects;
    int higherLength = higherObjects.length;
    this.enclosingObjects = new Object[higherLength + 1];
    for (int i = 0; i < higherLength; i++) {
      this.enclosingObjects[i] = higherObjects[i];
    }
    this.enclosingObjects[higherLength] = container;
  }
  
  public ClassDefinition classDefinition() {
    return classDefinition;
  }
  
  public Object makeInstance() {
    try {
      return (Object) constructor.invoke(this);
    } catch (Throwable e) {
      e.printStackTrace();
      throw new IllegalStateException("object creation failed");
    }
  }
}
