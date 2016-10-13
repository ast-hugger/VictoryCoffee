package org.newspeaklanguage.runtime;

/**
 * This class is a holder of a set of static classes used to give apparent
 * Newspeak behavior to built-in Java artifacts such as wrappers, Strings,
 * and null.
 * <p>
 * When Newspeak message dispatch encounters one of those artifacts as the
 * receiver of a message, it looks for a static method in one of the
 * classes nested here as the implementor of the corresponding message.
 * In other words, these are Newspeak method dictionaries of those Java artifacts.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
public final class Builtins {

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

  public static final class BuiltinNil extends BuiltinObject {

    public static Object $printString(Object self) {
      return "<nil>";
    }
  }

  public abstract static class BuiltinBoolean extends BuiltinObject {
  }

  public static final class BuiltinTrue extends BuiltinBoolean {

    public static Object $printString(Object self) {
      return "<true>";
    }
  }

  public static final class BuiltinFalse extends BuiltinBoolean {

    public static Object $printString(Object self) {
      return "<false>";
    }
  }

  public static final class BuiltinNumber extends BuiltinObject {

  }

  public static final class BuiltinString extends BuiltinObject {

    public static Object $$plus(Object self, Object another) {
      // TODO for now just assuming another is also a string
      return ((String) self) + ((String) another);
    }

    public static Object $printString(Object self) {
      return "'" + ((String) self) + "'";
    }
  }

}
