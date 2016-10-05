package org.newspeaklanguage.runtime;

public final class Builtins {
  
  public static final String INTERNAL_CLASS_NAME =
      Builtins.class.getName().replace('.', '/');
  
  public static final class UndefinedObject extends Object {
    public UndefinedObject(ObjectFactory nsClass) {
      super(nsClass);
    }
    @Override
    public String toString() {
      return "<nil>";
    }
    public Object _printString() {
      return stringFactory.makeInstance();
    }
  }
  
  public static class BooleanObject extends Object {
    public BooleanObject(ObjectFactory nsClass) {
      super(nsClass);
    }
  }
  
  public static final class TrueObject extends BooleanObject {
    public TrueObject(ObjectFactory nsClass) {
      super(nsClass);
    }
  }

  public static final class FalseObject extends BooleanObject {
    public FalseObject(ObjectFactory nsClass) {
      super(nsClass);
    }
  }

  public static final class IntegerObject extends Object {
    public IntegerObject(ObjectFactory nsClass) {
      super(nsClass);
    }
  }

  public static final class StringObject extends Object {
    public StringObject(ObjectFactory nsClass) {
      super(nsClass);
    }
  }
  
  public static final class ClassObject extends Object {
    public ClassObject(ObjectFactory nsClass) {
      super(nsClass);
    }
  }

  public static final class ClosureObject extends Object {
    public ClosureObject(ObjectFactory nsClass) {
      super(nsClass);
    }
  }

  public static final ClassDefinition undefinedObjectClassDef = 
      new ClassDefinition(UndefinedObject.class); 
  public static final ClassDefinition booleanClassDef =
      new ClassDefinition(BooleanObject.class);
  public static final ClassDefinition trueClassDef =
      new ClassDefinition(TrueObject.class);
  public static final ClassDefinition falseClassDef =
      new ClassDefinition(FalseObject.class);
  public static final ClassDefinition integerClassDef =
      new ClassDefinition(IntegerObject.class);
  public static final ClassDefinition stringClassDef =
      new ClassDefinition(StringObject.class);
  public static final ClassDefinition classClassDef =
      new ClassDefinition(ClassObject.class);
  public static final ClassDefinition closureClassDef =
      new ClassDefinition(ClosureObject.class);
  
  public static final ObjectFactory objectMetafactory = new ObjectFactory();
  public static final ObjectFactory undefinedObjectFactory =
      new ObjectFactory(objectMetafactory, undefinedObjectClassDef, null);
  public static final ObjectFactory trueFactory =
      new ObjectFactory(objectMetafactory, trueClassDef, null);
  public static final ObjectFactory falseFactory =
      new ObjectFactory(objectMetafactory, falseClassDef, null);
  public static final ObjectFactory integerFactory =
      new ObjectFactory(objectMetafactory, integerClassDef, null);
  public static final ObjectFactory stringFactory =
      new ObjectFactory(objectMetafactory, stringClassDef, null);
  public static final ObjectFactory closureFactory =
      new ObjectFactory(objectMetafactory, closureClassDef, null);

  public static final Object NIL = undefinedObjectFactory.makeInstance();
  public static final Object TRUE = trueFactory.makeInstance();
  public static final Object FALSE = falseFactory.makeInstance();
}
