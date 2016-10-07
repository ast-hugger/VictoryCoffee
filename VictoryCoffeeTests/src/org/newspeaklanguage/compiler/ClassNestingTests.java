package org.newspeaklanguage.compiler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.newspeaklanguage.runtime.Builtins;

public class ClassNestingTests {
  @Test
  public void testNestedClass() {
    Runner.Result result = Runner.run(
        "class Test = () ("
        + "class Nested = () ("
        + "  'accessing'"
        + "  foo = (^'foo')"
        + "  )"
        + "'testing'"
        + "test = (^Nested new foo)"
        + ")");
    assertTrue(result.testResult instanceof Builtins.StringObject);
    assertEquals("foo", ((Builtins.StringObject) result.testResult).value());
  }

  @Test
  public void testHereSends() {
    Runner.Result result = Runner.run(
        "class Test = () ("
        + "class Nested = () ("
        + "  'accessing'"
        + "  foo = (^here + there)"
        + "  here = (^'inner')"
        + "  )"
        + "'testing'"
        + "test = (^Nested new foo)"
        + "there = (^'outer')"
        + ")");
    assertTrue(result.testResult instanceof Builtins.StringObject);
    assertEquals("innerouter", ((Builtins.StringObject) result.testResult).value());
  }

  @Test
  public void testHereSends2() {
    Runner.Result result = Runner.run(
        "class Test = () ("
        + "class Nested = () ("
        + "  'accessing'"
        + "  foo = (^printString + self printString)"
        + "  )"
        + "'testing'"
        + "test = (^Nested new foo)"
        + "printString = (^'The Boss')"
        + ")");
    assertTrue(result.testResult instanceof Builtins.StringObject);
    assertEquals("The Boss<a Nested>", ((Builtins.StringObject) result.testResult).value());
  }

  @Test
  public void testOuterSend() {
    Runner.Result result = Runner.run(
        "class Test = () ("
        + "class Nested = () ("
        + "  'accessing'"
        + "  foo = (^outer Test printString)"
        + "  )"
        + "'testing'"
        + "test = (^Nested new foo)"
        + "printString = (^'The Boss')"
        + ")");
    assertTrue(result.testResult instanceof Builtins.StringObject);
    assertEquals("The Boss", ((Builtins.StringObject) result.testResult).value());
  }

}
