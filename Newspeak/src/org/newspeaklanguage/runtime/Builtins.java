package org.newspeaklanguage.runtime;

import org.newspeaklanguage.compiler.Descriptor;

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

  public static final String INTERNAL_CLASS_NAME = Descriptor.internalClassName(Builtins.class);

  public static final Object TRUE = Boolean.TRUE;
  public static final Object FALSE = Boolean.FALSE;

  public static Object string(String contents) {
    return contents;
  }

  public abstract static class ObjectMethods {

    private ObjectMethods() { // no instances, this and the subclasses are just repositories of static methods
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

  public static final class UndefinedObjectMethods extends ObjectMethods {

    public static Object $printString(Object self) {
      return "<nil>";
    }
  }

  public abstract static class BooleanMethods extends ObjectMethods {

    public static Object $ifTrue$ifFalse$(Object self, Object trueBlock, Object falseBlock) {
      return ((Boolean) self)
          ? ((Closure) trueBlock).$value()
          : ((Closure) falseBlock).$value();
    }

    public static Object $printString(Object self) {
      return ((Boolean) self)
          ? "<true>"
          : "<false>";
    }
  }

  public static final class NumberMethods extends ObjectMethods {

    public static Object $$plus(Object self, Object arg) {
      return ((Number) self).intValue() + ((Number) arg).intValue();
    }

    public static Object $$minus(Object self, Object arg) {
      return ((Number) self).intValue() - ((Number) arg).intValue();
    }

    public static Object $$lt(Object self, Object arg) {
      return ((Number) self).intValue() < ((Number) arg).intValue() ? TRUE : FALSE;
    }

    public static Object $$gt(Object self, Object arg) {
      return ((Number) self).intValue() > ((Number) arg).intValue() ? TRUE : FALSE;
    }

    public static Object $$eq(Object self, Object arg) {
      return ((Number) self).intValue() == ((Number) arg).intValue() ? TRUE : FALSE;
    }

    public static Object $printString(Object self) {
      return "<" + ((Number) self).intValue() + ">";
    }

  }

  public static final class StringMethods extends ObjectMethods {

    public static Object $$plus(Object self, Object another) {
      // TODO for now just assuming another is also a string
      return ((String) self) + ((String) another);
    }

    public static Object $printString(Object self) {
      return "'" + ((String) self) + "'";
    }
  }

}
