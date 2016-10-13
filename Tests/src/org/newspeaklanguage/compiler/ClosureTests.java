package org.newspeaklanguage.compiler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.newspeaklanguage.runtime.Builtins;
import org.newspeaklanguage.testsupport.Example;

public class ClosureTests {

  @Test
  public void testBasicClosure0Args() {
    Example test = Example.testBody("^['hello'] value");
    assertTrue(test.isResult("hello"));
  }

  @Test
  public void testEmptyClosureReturnsNil() {
    Example test = Example.testBody("^[] value");
    assertEquals(test.result(), Builtins.NIL);
  }
  
  @Test
  public void testClosureReturnsLastResult() {
    Example test = Example.testBody("^['hello'. 'goodbye'] value");
    assertTrue(test.isResult("goodbye"));
  }
  
  @Test
  public void testClosure1Arg() {
    Example test = Example.testBody("^[:x | x] value: 'Hello'");
    assertTrue(test.isResult("Hello"));
  }
  
  @Test
  public void testClosure2Args() {
    Example test = Example.testBody("^[:a :b | a + b] value: 'Hello' value: 'there'");
    assertTrue(test.isResult("Hellothere"));
  }
  
  @Test
  public void testClosureTemps() {
    Example test = Example.testBody(
        "^[:a :b | "
        + "| c d | "
        + "c: b. "
        + "d: a. "
        + "c + d] value: 'Hello' value: 'there'");
    assertTrue(test.isResult("thereHello"));
  }

  @Test
  public void testCopiedArg() {
    Example test = Example.testBody(
        "^[:a | [a] value] value: 'Hello'");
    assertTrue(test.isResult("Hello"));
  }

  @Test
  public void testCopiedArgs2Levels() {
    Example test = Example.testBody(
        "^[:a | [:b | [a + b] value] value: 'there'] value: 'Hello'");
    assertTrue(test.isResult("Hellothere"));
  }

  @Test
  public void testManyCopiedArgs() { // enough to trigger the varargs closure form
    Example test = Example.testBody(
        "^[:a :b | | t1 t2 | " +
            "[:c :d | " +
            "[t1: a + b." +
            " t2: c + d." +
            "t1 + t2] value] value: 'it' value: 'works'] value: 'Hello' value: 'there'");
    assertTrue(test.isResult("Hellothereitworks"));
  }

  @Test
  public void testMutableOuterBinding() {
    Example test = Example.testBody(
        "^[:a | | b | " +
        "   [b: a + a] value." +
            "b] value: 'Hello'");
    assertTrue(test.isResult("HelloHello"));
  }


}
