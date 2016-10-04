package org.newspeaklanguage.runtime;

/**
 * Every Newspeak object is in the Java sense an instance of a subclass of this
 * class. The compiler generates the subclass when it compiles a Newspeak class.
 * 
 * @author vassili
 *
 */
public class Object {
  
  private final Class nsClass;
  
  /**
   * Any generated subclass must provide a constructor with this signature.
   */
  public Object(Class nsClass) {
    this.nsClass = nsClass;
  }
  
  /**
   * @return The Newspeak class of the object.
   */
  public Class nsClass() {
    return nsClass;
  }

}
