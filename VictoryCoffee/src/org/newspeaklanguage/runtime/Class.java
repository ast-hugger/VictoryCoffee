package org.newspeaklanguage.runtime;

public class Class {

  private Object[] enclosingObjects;

  public Object[] enclosingObjects() {
    return enclosingObjects;
  }
  
  public Object enclosingObject(int index) {
    return enclosingObjects[index];
  }
 
  public Object makeInstance() {
    // TODO this should become an instantiation of the specific Java class
    return new Object(this);
  }
}
