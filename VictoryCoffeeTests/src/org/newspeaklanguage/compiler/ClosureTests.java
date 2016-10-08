package org.newspeaklanguage.compiler;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.newspeaklanguage.testsupport.Example;

public class ClosureTests {
  
  @Test
  public void testBasicClosure0Args() {
    Example test = Example.testBody("^['hello'] value");
    assertTrue(test.isResult("hello"));
  }

}
