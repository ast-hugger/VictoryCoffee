package org.newspeaklanguage.compiler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NamingPolicyTests {
  
  @Test
  public void testFieldName() {
    assertEquals("$foo", NamingPolicy.fieldNameForSlot("foo"));
  }

  @Test
  public void testGetterSelectorForSlot() {
    assertEquals("foo", NamingPolicy.getterForSlot("foo"));
  }  
  
  @Test
  public void testSetterSelectorForSlot() {
    assertEquals("foo:", NamingPolicy.setterForSlot("foo"));
  }  
  
  @Test
  public void testConversionBetweenGetterAndSetterSelectors() {
    assertEquals("foo:", NamingPolicy.setterForGetter("foo"));
    assertEquals("foo", NamingPolicy.getterForSetter("foo:"));
  }
  
  @Test
  public void testGetterMethodName() {
    assertEquals("$foo", NamingPolicy.getterMethodNameForSlot("foo"));
  }
  
  @Test
  public void testSetterMethodName() {
    assertEquals("$foo$", NamingPolicy.setterMethodNameForSlot("foo"));
  }
  
  @Test
  public void testCommutativityOfSlotRelatedThings() {
    assertEquals(
        NamingPolicy.getterMethodNameForSlot("foo"),
        NamingPolicy.methodNameForSelector(NamingPolicy.getterForSlot("foo")));
    assertEquals(
        NamingPolicy.setterMethodNameForSlot("foo"),
        NamingPolicy.methodNameForSelector(NamingPolicy.setterForSlot("foo")));
  }
  
  @Test
  public void testSlotNameForSelector() {
    assertEquals("foo", NamingPolicy.slotNameForSelector("foo"));
    assertEquals("foo", NamingPolicy.slotNameForSelector("foo:"));
  }

  @Test
  public void testMethodNameForSelector() {
    assertEquals("$foo", NamingPolicy.methodNameForSelector("foo"));
    assertEquals("$foo$", NamingPolicy.methodNameForSelector("foo:"));
    assertEquals("$foo$bar$", NamingPolicy.methodNameForSelector("foo:bar:"));
    assertEquals("$foo$bar$baz$", NamingPolicy.methodNameForSelector("foo:bar:baz:"));
    // Binary selectors now
    assertEquals("$$plus", NamingPolicy.methodNameForSelector("+"));
    assertEquals("$$minus", NamingPolicy.methodNameForSelector("-"));
    assertEquals("$$star", NamingPolicy.methodNameForSelector("*"));
    assertEquals("$$slash", NamingPolicy.methodNameForSelector("/"));
    assertEquals("$$eq", NamingPolicy.methodNameForSelector("="));
    assertEquals("$$tilda", NamingPolicy.methodNameForSelector("~"));
    assertEquals("$$amp", NamingPolicy.methodNameForSelector("&"));
    // Multi-char binary selectors
    assertEquals("$$eq$eq", NamingPolicy.methodNameForSelector("=="));
    assertEquals("$$tilda$eq", NamingPolicy.methodNameForSelector("~="));
  }
}
