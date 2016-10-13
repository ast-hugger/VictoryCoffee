package org.newspeaklanguage.runtime;

import org.newspeaklanguage.compiler.Descriptor;

/**
 * This used to be a superclass of all objects manipulated by compiled Newspeak code.
 * Later we changed to mixing and matching StandardObjects, Java boxed primitives,
 * and {@code null}. This no longer functions as a descriptor of every Newspeak value
 * on the stacks in Java method frames. But, we still use subclasses of this
 * to simulate the classes that things like {@code String} and {@code null}
 * are instances of in the Newspeak sense.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public abstract class NsObject {
  // The class is intentionally named NsObject to avoid confusion with the standard Object
  // which we use in type signatures.

  // These now reflect our actual usage of the Java Object as the catch-all type.
  public static final String INTERNAL_CLASS_NAME = Descriptor.internalClassName(java.lang.Object.class);
  public static final String TYPE_DESCRIPTOR = "L" + INTERNAL_CLASS_NAME + ";";

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
