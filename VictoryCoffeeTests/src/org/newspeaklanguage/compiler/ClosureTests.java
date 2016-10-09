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
  
}
