package org.newspeaklanguage.runtime;

import org.newspeaklanguage.compiler.Descriptor;

public final class Builtins {

  public static final String INTERNAL_CLASS_NAME = Descriptor.internalClassName(Builtins.class);

  public static Object string(String contents) {
    return contents;
  }

  public abstract static class BuiltinObject {

    private BuiltinObject() { // no instances, this and the subclasses are just repositories of static methods
    }

    public static Object $class(Object self) {
      return null;
    }

    public static Object $printString(Object self) {
      String className = self.getClass().getSimpleName();
      String article = isVowel(className.charAt(0)) ? "an " : "a ";
      return Builtins.string(article + className);
    }

    private static boolean isVowel(char c) {
      return "AEIOUaeiou".indexOf(c) != -1;
    }
  }

  public static final class UndefinedObject extends BuiltinObject {

    public static Object $printString(Object self) {
      return "<nil>";
    }
  }

  public abstract static class BooleanObject extends BuiltinObject {
  }

  public static final class TrueObject extends BooleanObject {

    public static Object $printString(Object self) {
      return "<true>";
    }
  }

  public static final class FalseObject extends BooleanObject {

    public static Object $printString(Object self) {
      return "<false>";
    }
  }

  public static final class IntegerObject extends BuiltinObject {

  }

  public static final class StringObject extends BuiltinObject {

    public static Object $$plus(Object self, Object another) {
      // TODO for now just assuming another is also a string
      return ((String) self) + ((String) another);
    }

    public static Object $printString(Object self) {
      return "'" + ((String) self) + "'";
    }
  }

}
