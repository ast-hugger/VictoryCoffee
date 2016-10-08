package org.newspeaklanguage.runtime;

import java.lang.invoke.MethodHandle;

import org.newspeaklanguage.compiler.Descriptor;

public final class Builtins {
  
  public static final String INTERNAL_CLASS_NAME =
      Builtins.class.getName().replace('.', '/');
  
  public static StringObject string(String contents) {
    return new StringObject(contents);
  }
  
  public static ClosureLiteral closure(Class<? extends StandardObject> implementationClass, String methodName) {
    return new ClosureLiteral(implementationClass, methodName);
  }
  
  
  public abstract static class BuiltinObject extends Object {
    @Override
    public ObjectFactory nsClass() {
      return null;
    }
    @Override
    public Object $class() {
      return NIL;
    }
    
    public Object $printString() {
      String className = this.getClass().getSimpleName();
      String article = isVowel(className.charAt(0)) ? "an " : "a ";
      return Builtins.string(article + this.getClass().getSimpleName());
    }
    
    private boolean isVowel(char c) {
      return "AEIOUaeiou".indexOf(c) != -1;
    }
  }
  
  public static final class UndefinedObject extends BuiltinObject {
    @Override
    public String toString() {
      return "<nil>";
    }
  }
  
  public abstract static class BooleanObject extends BuiltinObject {
  }
  
  public static final class TrueObject extends BooleanObject {
    public Object $printString() {
      return Builtins.string("<true>");
    }
  }

  public static final class FalseObject extends BooleanObject {
    public Object $printString() {
      return Builtins.string("<false>");
    }
  }

  public static final class IntegerObject extends BuiltinObject {
    public static final String INTERNAL_CLASS_NAME =
        IntegerObject.class.getName().replace('.', '/');
    
    private final int value;
    public IntegerObject(int value) {
      this.value = value;
    }
    public int value() { return value; }
    public String toString() { return "<" + value + ">"; }
  }

  public static final class StringObject extends BuiltinObject {
 
    public static final String INTERNAL_CLASS_NAME =
        StringObject.class.getName().replace('.', '/');

    private final String value;
    public StringObject(String value) {
      this.value = value;
    }
    public String value() { return value; }
    public String toString() { return "<'" + value + "'>"; }
    
    public Object $$plus(Object another) {
      // TODO for now just assuming another is also a string
      return Builtins.string(value + ((StringObject) another).value());
    }
    
    public Object $printString() {
      return Builtins.string("'" + value + "'");
    }
  }
  
  public static final class Closure extends BuiltinObject {
    
    public static final String INTERNAL_CLASS_NAME = Descriptor.internalClassName(Closure.class);
    public static final String CONSTRUCTOR_DESCRIPTOR = 
        Descriptor.ofMethod(void.class, ClosureLiteral.class, StandardObject.class);
    
    private final MethodHandle bodyMethodHandle;
    private final StandardObject copiedSelf;
    
    public Closure(ClosureLiteral closureLiteral, StandardObject copiedSelf) {
      this.bodyMethodHandle = closureLiteral.methodHandle();
      this.copiedSelf = copiedSelf;
    }
    
    public Object $value() {
      try {
        return (Object) bodyMethodHandle.invoke(copiedSelf);
      } catch (Throwable e) {
        throw new RuntimeError("closure invocation error", e);
      }
    }
  }
  
//  public static final class ClassObject extends Object {
//    @Override
//    public ObjectFactory nsClass() {
//      return stringFactory;
//    }
//  }

  
  
//  public static final ClassDefinition undefinedObjectClassDef = 
//      new ClassDefinition(UndefinedObject.class); 
//  public static final ClassDefinition booleanClassDef =
//      new ClassDefinition(BooleanObject.class);
//  public static final ClassDefinition trueClassDef =
//      new ClassDefinition(TrueObject.class);
//  public static final ClassDefinition falseClassDef =
//      new ClassDefinition(FalseObject.class);
//  public static final ClassDefinition integerClassDef =
//      new ClassDefinition(IntegerObject.class);
//  public static final ClassDefinition stringClassDef =
//      new ClassDefinition(StringObject.class);
////  public static final ClassDefinition classClassDef =
////      new ClassDefinition(ClassObject.class);
//  public static final ClassDefinition closureClassDef =
//      new ClassDefinition(ClosureObject.class);
  
//  public static final ObjectFactory objectMetafactory = new ObjectFactory();
//  public static final ObjectFactory undefinedObjectFactory =
//      new ObjectFactory(objectMetafactory, undefinedObjectClassDef, null);s
//  public static final ObjectFactory trueFactory =
//      new ObjectFactory(objectMetafactory, trueClassDef, null);
//  public static final ObjectFactory falseFactory =
//      new ObjectFactory(objectMetafactory, falseClassDef, null);
//new ObjectFactory(objectMetafactory, falseCl
//public static final ObjectFactory integerFactory =
//new ObjectFactory(objectMetafactory, integerClassDef, null);
//public static final ObjectFactory stringFactory =
//new ObjectFactory(objectMetafactory, stringClassDef, null
//public static final ObjectFactory falseFactory =
//new ObjectFactory(objectMetafactory, falseClassDef, null);
//public static final ObjectFactory integerFactory =
//new ObjectFactory(objectMetafactory, integerClassDef, nulassDef, null);
//public static final ObjectFactory integerFactory =
//new ObjectFactory(objectMetafactory, integerClassDef, null);
//public static final ObjectFactory stringFactory =
//new ObjectFactory(objectMetafactory, stringClassDef, null
//  public static final ObjectFactory integerFactory =
//      new ObjectFactory(objectMetafactory, integerClassDef, null);
//  public static final ObjectFactory stringFactory =
//      new ObjectFactory(objectMetafactory, stringClassDef, null);
//  public static final ObjectFactory closureFactory =
//public static final ObjectFactory trueFactory =
//new ObjectFactory(objectMetafactory, trueClassDef, null);
//new ObjectFactory(objectMetafactory, falseClassDef, null);
//public static final ObjectFactory integerFactory =
//new ObjectFactory(objectMetafactory, integerClassDef, null);
//public static final ObjectFactory stringFactory =
//new ObjectFactory(objectMetafactory, stringClassDef, null
//public static final ObjectFactory falseFactory =
//new ObjectFactory(objectMetafactory, falseClassDef, null);
//public static final ObjectFactory integerFactory =
//new ObjectFactory(objectMetafactory, integerClassDef, null);
//public static final ObjectFactory stringFactory =
//new ObjectFactory(objectMetafactory, stringClassDef, null
//      new ObjectFactory(objectMetafactory, closureClassDef, null);

  public static final Object NIL = new UndefinedObject();
  public static final Object TRUE = new TrueObject();
  public static final Object FALSE = new FalseObject();
}