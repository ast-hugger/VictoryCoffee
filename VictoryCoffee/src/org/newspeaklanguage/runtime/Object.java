package org.newspeaklanguage.runtime;

public class Object {
  
  private final Class klass;
  
  Object(Class klass) {
    this.klass = klass;
  }
  
  public Class klass() {
    return klass;
  }

}
