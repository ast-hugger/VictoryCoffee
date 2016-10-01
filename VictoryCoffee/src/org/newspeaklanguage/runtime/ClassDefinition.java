package org.newspeaklanguage.runtime;

/**
 * Represents a class in the source code sense. This is not an actual object
 * factory used to make instances. For that, see {@link Class}.
 * 
 * @author vassili
 *
 */
public class ClassDefinition {

  private final java.lang.Class<?> implementation;
  
  ClassDefinition(java.lang.Class<?> implementation) {
    this.implementation = implementation;
  }
  
  public java.lang.Class<?> implementation() {
    return implementation;
  }
  
}
