package org.newspeaklanguage.runtime;

/**
 * A metaobject representing a Newspeak class in a specific enclosing object.
 * <p>
 * A Newspeak class is implemented by a triplet of entities. Given a class
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
 * <p>
 * This is the object one gets when given an instance {@code c} of class (in the
 * source code sense) {@code C} with a nested class {@code N}, one asks for
 * {@code c N}.
 * 
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class ObjectFactory extends Object {
  
  public static final String INTERNAL_CLASS_NAME =
      ObjectFactory.class.getName().replace('.', '/');
  public static final String TYPE_DESCRIPTOR = "L" + INTERNAL_CLASS_NAME + ";";

  public static ObjectFactory create(ClassDefinition classDef, Object container) {
    return new ObjectFactory(null, classDef, container);
  }
  
  /*
   * Instance side
   */
  
  private final ObjectFactory nsClass;
  private final ClassDefinition classDefinition;
  // Public so call sites for outer sends can read this directly as a field
  public final Object[] enclosingObjects;

  /**
   * Produces a bogus instance which is an instance of itself. 
   * Provided for bootstrapping and testing to create the Class class.
   */
  ObjectFactory() {
    this.nsClass = null;
    this.classDefinition = null;
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
  ObjectFactory(ObjectFactory nsClass, ClassDefinition classDefinition, Object container) {
    this.nsClass = nsClass;
    this.classDefinition = classDefinition;
    
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
  
  public ObjectFactory nsClass() {
    return nsClass;
  }
  
  public ClassDefinition classDefinition() {
    return classDefinition;
  }
  
  public Object makeInstance() {
    try {
      return classDefinition.makeInstance(this);
    } catch (Throwable e) {
      e.printStackTrace();
      throw new IllegalStateException("object creation failed");
    }
  }
}
