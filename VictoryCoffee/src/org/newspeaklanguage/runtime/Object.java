package org.newspeaklanguage.runtime;

/**
 * A superclass of classes used to create Newspeak instances.
 * <p>
 * A Newspeak class is implemented by a triplet of entities. Given a class
 * source code, the compiler produces two of them: a {@link ClassDefinition}
 * instance holding onto a subclass of {@link Object}. The subclass is what's
 * instantiated to produce an instance of the Newspeak class. There is always
 * one ClassDefinition and one subclass of Object per Newspeak class. The third
 * entity comes into play when Newspeak code retrieves a Newspeak class from a
 * particular containing object. The metaobject received is a
 * {@link ObjectFactory}. It holds onto the corresponding ClassDefinition and
 * the enclosing instance of its parent Newspeak class. Thus, there can be
 * multiple Class instances representing a particular Newspeak class, if that
 * class has been referenced in the context of different enclosing objects.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public abstract class Object {
  
  public static final String INTERNAL_CLASS_NAME = Object.class.getName().replace('.', '/');
  public static final String TYPE_DESCRIPTOR = "L" + INTERNAL_CLASS_NAME + ";";
  public static final String CONSTRUCTOR_DESCRIPTOR = "(" + ObjectFactory.TYPE_DESCRIPTOR + ")V";
  
  private final ObjectFactory nsClass;
  
  /**
   * Any generated subclass must provide a constructor with this signature.
   * 
   * @see Object.CONSTRUCTOR_DESCRIPTOR
   */
  public Object(ObjectFactory nsClass) {
    this.nsClass = nsClass;
  }
  
  /**
   * @return The Newspeak class of the object.
   */
  public ObjectFactory nsClass() {
    return nsClass;
  }

}
