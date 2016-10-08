package org.newspeaklanguage.compiler;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.newspeaklanguage.testsupport.Example;

public class ClassSlotsTests {
  
  @Test
  public void testSlotReadAndWrite() {
    Example test = Example.fullClass(
        "class Test = (| foo |)"
        + "('testing'"
        + "test = ("
        + "  foo: 'hello'."
        + "  ^foo"
        + ")"
        + ")");
    assertTrue(test.isResult("hello"));
  }

  @Test
  public void testSlotSetterReturnValue() {
    Example test = Example.fullClass(
        "class Test = (| foo bar |)"
        + "('testing'"
        + "test = ("
        + "  bar: (foo: 'hello')."
        + "  ^bar"
        + ")"
        + ")");
    assertEquals(test.module(), test.result());
  }

  @Test
  public void testSetterSend() {
    Example test = Example.fullClass(
        "class Test = (| foo |)"
        + "('testing'"
        + "test = ("
        + "  foo:: 'hello'."
        + "  ^foo"
        + ")"
        + ")");
    assertTrue(test.isResult("hello"));
  }

  @Test
  public void testSetterSendReturnValue() {
    Example test = Example.fullClass(
        "class Test = (| foo bar |)"
        + "('testing'"
        + "test = ("
        + "  bar: (foo:: 'hello')."
        + "  ^bar"
        + ")"
        + ")");
    assertTrue(test.isResult("hello"));
  }

  @Test
  public void testInnerAndOuterAccess() {
    Example test = Example.fullClass(
        "class Test = ( | outerSlot | ) ("
        + "class Nested = ( | innerSlot | ) ("
        + "  'accessing'"
        + "  foo = ("
        + "     innerSlot:: 'inner'."
        + "     outerSlot:: 'outer'. "
        + "     ^innerSlot + outerSlot)"
        + "  )"
        + "'testing'"
        + "test = (^Nested new foo)"
        + ")");
    assertTrue(test.isResult("innerouter"));
  }

  @Test
  public void testInnerAndOuterShadowedAccess() {
    Example test = Example.fullClass(
        "class Test = ( | slot | ) ("
        + "class Nested = ( | slot | ) ("
        + "  'accessing'"
        + "  foo = ("
        + "     slot:: 'inner'."
        + "     outer Test slot: 'outer'. "
        + "     ^slot + outer Test slot)"
        + "  )"
        + "'testing'"
        + "test = (^Nested new foo)"
        + ")");
    assertTrue(test.isResult("innerouter"));
  }


}
