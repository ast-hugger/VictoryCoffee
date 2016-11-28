/*
 * Copyright (c) 2016 Vassili Bykov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    public static Object $class(int unused, Object self) {
      return null;
    }

    public static Object $printString(int unused, Object self) {
      String className = self.getClass().getSimpleName();
      String article = isVowel(className.charAt(0)) ? "an " : "a ";
      return Builtins.string(article + className);
    }

    private static boolean isVowel(char c) {
      return "AEIOUaeiou".indexOf(c) != -1;
    }
  }

  public static final class UndefinedObjectMethods extends ObjectMethods {

    public static Object $printString(int unused, Object self) {
      return "<nil>";
    }
  }

  public abstract static class BooleanMethods extends ObjectMethods {

    public static Object $ifTrue$ifFalse$(int unused, Object self, int unused2, Object trueBlock, int unused3, Object falseBlock) {
      // FIXME this will barf if the blocks are not blocks, but not with an informative message
      return ((Boolean) self)
          ? ((Closure) trueBlock).$value()
          : ((Closure) falseBlock).$value();
    }

    public static Object $printString(int unused, Object self) {
      return ((Boolean) self)
          ? "<true>"
          : "<false>";
    }
  }

  public static final class IntMethods extends ObjectMethods {

    public static Object $$plus(int self, Object unused, int intArg, Object arg) {
      int result = self + (arg == NsObject.UNDEFINED ? intArg : ((Number) arg).intValue());
      return ReturnPrimitiveValue.create(result);
    }

    public static Object $$minus(int self, Object unused, int intArg, Object arg) {
      int result = self - (arg == NsObject.UNDEFINED ? intArg : ((Number) arg).intValue());
      return ReturnPrimitiveValue.create(result);
    }

    public static Object $$lt(int self, Object unused, int intArg, Object arg) {
      return (Boolean) (self < (arg == NsObject.UNDEFINED ? intArg : ((Number) arg).intValue()));
    }

    public static Object $$gt(int self, Object unused, int intArg, Object arg) {
      return (Boolean) (self > (arg == NsObject.UNDEFINED ? intArg : ((Number) arg).intValue()));
    }

    public static Object $$eq(int self, Object unused, int intArg, Object arg) {
      return (Boolean) (self == (arg == NsObject.UNDEFINED ? intArg : ((Number) arg).intValue()));
    }

    public static Object $printString(int self, Object unused) {
      return "<" + self + ">";
    }
  }

  public static final class NumberMethods extends ObjectMethods {

    public static Object $$plus(int unused, Object self, int intArg, Object arg) {
      return ((Number) self).intValue() + (arg == NsObject.UNDEFINED ? intArg : ((Number) arg).intValue());
    }

    public static Object $$minus(int unused, Object self, int intArg, Object arg) {
      return ((Number) self).intValue() - (arg == NsObject.UNDEFINED ? intArg : ((Number) arg).intValue());
    }

    public static Object $$lt(int unused, Object self, int intArg, Object arg) {
      return ((Number) self).intValue() < (arg == NsObject.UNDEFINED ? intArg : ((Number) arg).intValue())
          ? TRUE : FALSE;
    }

    public static Object $$gt(int unused, Object self, int intArg, Object arg) {
      return ((Number) self).intValue() > (arg == NsObject.UNDEFINED ? intArg : ((Number) arg).intValue())
          ? TRUE : FALSE;
    }

    public static Object $$eq(int unused, Object self, int intArg, Object arg) {
      return ((Number) self).intValue() == (arg == NsObject.UNDEFINED ? intArg : ((Number) arg).intValue())
          ? TRUE : FALSE;
    }

    public static Object $printString(int unused, Object self) {
      return "<" + ((Number) self).intValue() + ">";
    }

  }

  public static final class StringMethods extends ObjectMethods {

    public static Object $$plus(int unused, Object self, int intAnother, Object another) {
      // TODO for now just assuming another is also a string
      String right = another == NsObject.UNDEFINED ? Integer.toString(intAnother) : (String) another;
      return ((String) self) + right;
    }

    public static Object $printString(int unused, Object self) {
      return "'" + ((String) self) + "'";
    }
  }

}
