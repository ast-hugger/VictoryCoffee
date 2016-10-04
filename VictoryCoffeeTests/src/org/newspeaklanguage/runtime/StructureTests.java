package org.newspeaklanguage.runtime;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import junit.framework.TestCase;

public class StructureTests extends TestCase {

  public static class Outer extends Object {
    Outer(Class klass) {
      super(klass);
    }
  }
  
  public static class Inner extends Object {
    Inner(Class klass) {
      super(klass);
    }
  }
  
  public static class InnerInner extends Object {
    InnerInner(Class klass) {
      super(klass);
    }
  }
  
  public Class classClass = new Class();
  
  public ClassDefinition outerDefinition = new ClassDefinition(Outer.class);
  public ClassDefinition innerDefinition = new ClassDefinition(Inner.class);
  public ClassDefinition innerInnerDefinition = new ClassDefinition(InnerInner.class);
  
  public Class outerClass;
  public Object outerInstance;
  
  public Class innerClass;
  public Object innerInstance;
  
  public Class innerInnerClass;
  public Object innerInnerInstance;
  
  @Override
  public void setUp() {
    try {
      outerClass = new Class(classClass, outerDefinition, null);
      outerInstance = outerClass.makeInstance();
      innerClass = new Class(classClass, innerDefinition, outerInstance);
      innerInstance = innerClass.makeInstance();
      innerInnerClass = new Class(classClass, innerInnerDefinition, innerInstance);
      innerInnerInstance = innerInnerClass.makeInstance();
    } catch (NoSuchMethodException | IllegalAccessException e) {
      fail("Failure creating classes");
    }
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
    
}
