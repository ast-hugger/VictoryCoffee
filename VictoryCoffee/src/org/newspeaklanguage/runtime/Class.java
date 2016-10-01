package org.newspeaklanguage.runtime;

/**
 * This is a class in the sense of an object factory in a context of a
 * containing object, not in the source code sense. In other words, this is the
 * object one gets when given an instance {@code c} of class (in the source code
 * sense) {@code C} with a nested class {@code N}, one asks for {@code c N}.
 * 
 * <p>The class in the source code sense is represented by {@link ClassDefinition}.
 * 
 * @author vassili
 *
 */
public class Class extends Object {

  private final ClassDefinition classDefinition;
  
  // Package access so call sites for outer sends can read this directly
  final Object[] enclosingObjects;

  Class(Class klass, ClassDefinition classDefinition, Object container) {
    super(klass);
    this.classDefinition = classDefinition;
    Object[] higherObjects = container.klass().enclosingObjects();
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
  
  public Object[] enclosingObjects() {
    return enclosingObjects;
  }
  
  public Object enclosingObject(int index) {
    return enclosingObjects[index];
  }
 
  public Object makeInstance() {
    // TODO this should become an instantiation of classDefinition.implementation()
    return new Object(this);
  }
}
