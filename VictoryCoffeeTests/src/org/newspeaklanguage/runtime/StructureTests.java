package org.newspeaklanguage.runtime;

import org.junit.Test;

import junit.framework.TestCase;

public class StructureTests extends TestCase {
/*
  public static class Outer extends Object {
    Outer(ObjectFactory klass) {
      super(klass);
    }
  }
  
  public static class Inner extends Object {
    Inner(ObjectFactory klass) {
      super(klass);
    }
  }
  
  public static class InnerInner extends Object {
    InnerInner(ObjectFactory klass) {
      super(klass);
    }
  }
  
  public ObjectFactory classClass = new ObjectFactory();
  
  public ClassDefinition outerDefinition = new ClassDefinition(Outer.class);
  public ClassDefinition innerDefinition = new ClassDefinition(Inner.class);
  public ClassDefinition innerInnerDefinition = new ClassDefinition(InnerInner.class);
  
  public ObjectFactory outerClass;
  public Object outerInstance;
  
  public ObjectFactory innerClass;
  public Object innerInstance;
  
  public ObjectFactory innerInnerClass;
  public Object innerInnerInstance;
  
  @Override
  public void setUp() {
    outerClass = new ObjectFactory(classClass, outerDefinition, null);
    outerInstance = outerClass.makeInstance();
    innerClass = new ObjectFactory(classClass, innerDefinition, outerInstance);
    innerInstance = innerClass.makeInstance();
    innerInnerClass = new ObjectFactory(classClass, innerInnerDefinition, innerInstance);
    innerInnerInstance = innerInnerClass.makeInstance();
  }
  
  @Test
  public void testBasicStructure() {
    assertEquals(classClass, outerClass.nsClass());
    assertEquals(classClass, innerClass.nsClass());
    assertEquals(outerClass, outerInstance.nsClass());
    assertEquals(innerClass, innerInstance.nsClass());
  }

  @Test
  public void testEnclosingObjects() {
    assertEquals(0, outerClass.enclosingObjects.length);
    assertEquals(1, innerClass.enclosingObjects.length);
    assertEquals(outerInstance, innerClass.enclosingObjects[0]);
    assertEquals(2, innerInnerClass.enclosingObjects.length);
    assertEquals(outerInstance, innerInnerClass.enclosingObjects[0]);
    assertEquals(innerInstance, innerInnerClass.enclosingObjects[1]);
  }
 */
}
