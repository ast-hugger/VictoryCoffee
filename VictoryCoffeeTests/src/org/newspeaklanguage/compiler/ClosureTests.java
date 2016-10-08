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

  
}
