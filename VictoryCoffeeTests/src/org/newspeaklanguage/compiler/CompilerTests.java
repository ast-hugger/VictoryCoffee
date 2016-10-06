package org.newspeaklanguage.compiler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;
import org.newspeaklanguage.runtime.Builtins;
import org.newspeaklanguage.runtime.ClassDefinition;
import org.newspeaklanguage.runtime.NewspeakClassLoader;
import org.newspeaklanguage.runtime.Object;
import org.newspeaklanguage.runtime.ObjectFactory;

public class CompilerTests {
  
  private Compiler.Result lastCompilerResult;

  @Test
  public void testEmptyMethodReturnsSelf() {
    Object result = compileAndRunTestMethod(
        "class Test = () ('testing'"
        + "test = ()"
        + ")");
    assertEquals(lastCompilerResult.implementationClassName(), result.getClass().getName());
  }

  @Test
  public void testReceiverImplicitlyReturned() {
    Object result = compileAndRunTestMethod(
        "class Test = () ('testing'"
        + "test = (nil)"
        + ")");
    assertEquals(lastCompilerResult.implementationClassName(), result.getClass().getName());
  }

  @Test
  public void testReturnNil() {
    Object result = compileAndRunTestMethod(
        "class Test = () ('testing'"
        + "test = (^nil)"
        + ")");
    assertEquals(Builtins.NIL, result);
  }

  @Test
  public void testReturnSelf() {
    Object result = compileAndRunTestMethod(
        "class Test = () ('testing'"
        + "test = (^self)"
        + ")");
    assertEquals(lastCompilerResult.implementationClassName(), result.getClass().getName());
  }

  @Test
  public void testReturnTrue() {
    Object result = compileAndRunTestMethod(
        "class Test = () ('testing'"
        + "test = (^true)"
        + ")");
    assertEquals(Builtins.TRUE, result);
  }

  @Test
  public void testReturnFalse() {
    Object result = compileAndRunTestMethod(
        "class Test = () ('testing'"
        + "test = (^false)"
        + ")");
    assertEquals(Builtins.FALSE, result);
  }
  
  @Test
  public void testReturnLiteralString() {
    Object result = compileAndRunTestMethod(
        "class Test = () ('testing'"
        + "test = (^'foobar')"
        + ")");
    assertTrue(result instanceof Builtins.StringObject);
    assertEquals("foobar", ((Builtins.StringObject) result).value());
  }

  @Test
  public void testBinaryMessageSend() {
    Object result = compileAndRunTestMethod(
        "class Test = () ('testing'"
        + "test = (^self foo + self bar)"
        + "foo = (^'foo1')"
        + "bar = (^'bar2')"
        + ")");
    assertTrue(result instanceof Builtins.StringObject);
    assertEquals("foo1bar2", ((Builtins.StringObject) result).value());
  }

  @Test
  public void testKeywordMessageSend() {
    Object result = compileAndRunTestMethod(
        "class Test = () ('testing'"
        + "test = (^self concat: 'foo' and: 'bar')"
        + "concat: a and: b = (^ a + b)"
        + ")");
    assertTrue(result instanceof Builtins.StringObject);
    assertEquals("foobar", ((Builtins.StringObject) result).value());
  }

  @Test
  public void testImplicitSelfSendUnary() {
    Object result = compileAndRunTestMethod(
        "class Test = () ('testing'"
        + "test = (^foobar)"
        + "foobar = (^'Hello')"
        + ")");
    assertTrue(result instanceof Builtins.StringObject);
    assertEquals("Hello", ((Builtins.StringObject) result).value());
  }

  @Test
  public void testImplicitSelfSendKeyword() {
    Object result = compileAndRunTestMethod(
        "class Test = () ('testing'"
            + "test = (^concat: 'foo' and: 'bar')"
            + "concat: a and: b = (^ a + b)"
        + ")");
    assertTrue(result instanceof Builtins.StringObject);
    assertEquals("foobar", ((Builtins.StringObject) result).value());
  }

  @Test
  public void testNestedClass() {
    Object result = compileAndRunTestMethod(
        "class Test = () ("
        + "class Nested = () ("
        + "  'accessing'"
        + "  foo = (^'foo')"
        + "  )"
        + "'testing'"
        + "test = (^Nested new foo)"
        + ")");
    assertTrue(result instanceof Builtins.StringObject);
    assertEquals("foo", ((Builtins.StringObject) result).value());
  }


  @SuppressWarnings("unchecked")
  private Object compileAndRunTestMethod(String classSource) {
    List<Compiler.Result> results = Compiler.compile(classSource);
    lastCompilerResult = results.get(0);
    NewspeakClassLoader classLoader = new NewspeakClassLoader();
    results.forEach(each 
        -> classLoader.addBytecode(each.implementationClassName(), each.bytecode()));
    classLoader.dumpClassFiles();

    Class<? extends Object> mainClass;
    try {
      mainClass = (Class<? extends Object>) classLoader.loadClass(results.get(0).implementationClassName());
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("failure loading the compiled Newspeak class");
    }

    ClassDefinition classDef = ClassDefinition.create(mainClass);
    ObjectFactory factory = ObjectFactory.create(classDef, null);
    Object module = factory.makeInstance();
    
    return invoke(module, NamingPolicy.methodNameForSelector("test"));
  }
  
  private static Object invoke(Object object, String methodName) {
    try {
      Method method = object.getClass().getMethod(methodName);
      return (Object) method.invoke(object);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

}
