package org.newspeaklanguage.runtime;

import org.newspeaklanguage.compiler.Descriptor;

/**
 * A superclass of all objects manipulated by compiled Newspeak code.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public abstract class Object {
  
  public static final String INTERNAL_CLASS_NAME = Descriptor.internalClassName(Object.class);
  public static final String TYPE_DESCRIPTOR = "L" + INTERNAL_CLASS_NAME + ";";
  public static final String CONSTRUCTOR_DESCRIPTOR = "(" + ObjectFactory.TYPE_DESCRIPTOR + ")V";
  
  public abstract ObjectFactory nsClass();
  
  public Object $class() {
    return nsClass();
  }
  
  public Object $printString() {
    String name = this.nsClass().name();
    String article = isVowel(name.charAt(0)) ? "an" : "a";
    return Builtins.string("<" + article + " " + name + ">");
  }
  
  private boolean isVowel(char c) {
    return "AEIOUaeiou".indexOf(c) != -1;
  }
}
