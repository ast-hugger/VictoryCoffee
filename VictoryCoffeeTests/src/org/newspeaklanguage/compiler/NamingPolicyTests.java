package org.newspeaklanguage.compiler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NamingPolicyTests {
  
  @Test
  public void testFieldName() {
    assertEquals("$foo", NamingPolicy.fieldNameForSlot("foo"));
  }
  
  @Test
  public void testGetterSelector() {
    assertEquals("$foo", NamingPolicy.getterSelectorForSlot("foo"));
  }
  
  @Test
  public void testSetterSelector() {
    assertEquals("$foo$", NamingPolicy.setterSelectorForSlot("foo"));
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
