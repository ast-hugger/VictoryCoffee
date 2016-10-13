package org.newspeaklanguage.runtime;

import org.newspeaklanguage.compiler.Descriptor;

/**
 * A value holder used by the closure implementation to hold the values of mutable local
 * variables that are passed down to the implementation methods of nested closures.
 * It's made a subclass of the Newspeak kind of Object so that we can mix and match
 * these and the actual objects without changing method signatures.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
public class Box extends Object {

  public static final String INTERNAL_CLASS_NAME = Descriptor.internalClassName(Box.class);

  // public so that generated code can get and set it directly
  public Object value = Builtins.NIL;

  @Override
  public ObjectFactory nsClass() {
    return null;
  }
}
