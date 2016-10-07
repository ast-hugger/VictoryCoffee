package org.newspeaklanguage.compiler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.newspeaklanguage.runtime.Builtins;

public class BasicTests {
  
  @Test
  public void testEmptyMethodReturnsSelf() {
    Runner.Result result = Runner.run(
        "class Test = () ('testing'"
        + "test = ()"
        + ")");
    assertEquals("Test", result.resultClassName());
  }

  @Test
  public void testReceiverImplicitlyReturned() {
    Runner.Result result = Runner.run(
        "class Test = () ('testing'"
        + "test = (nil)"
        + ")");
    assertEquals("Test", result.resultClassName());
  }

  @Test
  public void testReturnNil() {
    Runner.Result result = Runner.run(
        "class Test = () ('testing'"
        + "test = (^nil)"
        + ")");
    assertEquals(Builtins.NIL, result.testResult);
  }

  @Test
  public void testReturnSelf() {
    Runner.Result result = Runner.run(
        "class Test = () ('testing'"
        + "test = (^self)"
        + ")");
    assertEquals("Test", result.resultClassName());
  }

  @Test
  public void testReturnTrue() {
    Runner.Result result = Runner.run(
       "class Test = () ('testing'"
        + "test = (^true)"
        + ")");
    assertEquals(Builtins.TRUE, result.testResult);
  }

  @Test
  public void testReturnFalse() {
    Runner.Result result = Runner.run(
        "class Test = () ('testing'"
        + "test = (^false)"
        + ")");
    assertEquals(Builtins.FALSE, result.testResult);
  }
  
  @Test
  public void testReturnLiteralString() {
    Runner.Result result = Runner.run(
        "class Test = () ('testing'"
        + "test = (^'foobar')"
        + ")");
    assertTrue(result.testResult instanceof Builtins.StringObject);
    assertEquals("foobar", ((Builtins.StringObject) result.testResult).value());
  }

  @Test
  public void testBinaryMessageSend() {
    Runner.Result result = Runner.run(
        "class Test = () ('testing'"
        + "test = (^self foo + self bar)"
        + "foo = (^'foo1')"
        + "bar = (^'bar2')"
        + ")");
    assertTrue(result.testResult instanceof Builtins.StringObject);
    assertEquals("foo1bar2", ((Builtins.StringObject) result.testResult).value());
  }

  @Test
  public void testKeywordMessageSend() {
    Runner.Result result = Runner.run(
        "class Test = () ('testing'"
        + "test = (^self concat: 'foo' and: 'bar')"
        + "concat: a and: b = (^ a + b)"
        + ")");
    assertTrue(result.testResult instanceof Builtins.StringObject);
    assertEquals("foobar", ((Builtins.StringObject) result.testResult).value());
  }

  @Test
  public void testImplicitSelfSendUnary() {
    Runner.Result result = Runner.run(
        "class Test = () ('testing'"
        + "test = (^foobar)"
        + "foobar = (^'Hello')"
        + ")");
    assertTrue(result.testResult instanceof Builtins.StringObject);
    assertEquals("Hello", ((Builtins.StringObject) result.testResult).value());
  }

  @Test
  public void testImplicitSelfSendKeyword() {
    Runner.Result result = Runner.run(
        "class Test = () ('testing'"
            + "test = (^concat: 'foo' and: 'bar')"
            + "concat: a and: b = (^ a + b)"
        + ")");
    assertTrue(result.testResult instanceof Builtins.StringObject);
    assertEquals("foobar", ((Builtins.StringObject) result.testResult).value());
  }

  @Test
  public void testSequence() {
    Runner.Result result = Runner.run(
        "class Test = () ('testing'"
            + "test = ('foo'."
            + "        'bar'."
            + "        ^'baz')"
        + ")");
    assertTrue(result.testResult instanceof Builtins.StringObject);
    assertEquals("baz", ((Builtins.StringObject) result.testResult).value());
  }


}
