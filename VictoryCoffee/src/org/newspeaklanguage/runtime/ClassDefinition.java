package org.newspeaklanguage.runtime;

/**
 * Represents a class in the source code sense. This is not an actual object
 * factory used to make instances. For that, see {@link Class}.
 * 
 * @author vassili
 *
 */
public class ClassDefinition {

  /**
   * The subclass of {@link Object} instances (in the Java sense) of which
   * represent instances of one of the Classes produced from this
   * ClassDefinition.
   */
  private final java.lang.Class<? extends Object> implementation;
  
  ClassDefinition(java.lang.Class<? extends Object> implementation) {
    this.implementation = implementation;
  }
  
  public java.lang.Class<? extends Object> implementation() {
    return implementation;
  }
  
}
