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

import org.junit.Test;

// import org.junit.Test;

import junit.framework.TestCase;

public class StructureTests extends TestCase {
/*
  public static class Outer extends NsObject {
    Outer(ObjectFactory klass) {
      super(klass);
    }
  }
  
  public static class Inner extends NsObject {
    Inner(ObjectFactory klass) {
      super(klass);
    }
  }
  
  public static class InnerInner extends NsObject {
    InnerInner(ObjectFactory klass) {
      super(klass);
    }
  }
  
  public ObjectFactory classClass = new ObjectFactory();
  
  public ClassDefinition outerDefinition = new ClassDefinition(Outer.class);
  public ClassDefinition innerDefinition = new ClassDefinition(Inner.class);
  public ClassDefinition innerInnerDefinition = new ClassDefinition(InnerInner.class);
  
  public ObjectFactory outerClass;
  public NsObject outerInstance;
  
  public ObjectFactory innerClass;
  public NsObject innerInstance;
  
  public ObjectFactory innerInnerClass;
  public NsObject innerInnerInstance;
  
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
  @Test
  public void testNothing() {
    assertEquals(4, 2 + 2);
  }
}
