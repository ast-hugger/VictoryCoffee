package org.newspeaklanguage.testsupport;

import java.lang.reflect.Method;
import java.util.List;

import org.newspeaklanguage.compiler.Compiler;
import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.runtime.Builtins;
import org.newspeaklanguage.runtime.ClassDefinition;
import org.newspeaklanguage.runtime.NewspeakClassLoader;
import org.newspeaklanguage.runtime.Object;
import org.newspeaklanguage.runtime.ObjectFactory;

public class Example {

  
  public static Example fullClass(String source) {
    Example runner = new Example();
    runner.compile(source);
    runner.runTest();
    return runner;
  }
  
  public static Example testMethod(String methodSource) {
    String classSource = 
        "class Test = () ('testing'\n"
        + methodSource + "\n"
        + ")"; 
    return fullClass(classSource);
  }
  
  public static Example testBody(String sourceOfTestMethodBody) {
    return testMethod("test = (" + sourceOfTestMethodBody + ")");
  }
  
  /*
   * Instance side
   */
  
  protected List<Compiler.Result> compilationResults;
  protected Class<? extends Object> topImplementationClass;
  protected ClassDefinition topClassDef;
  protected ObjectFactory topFactory;
  protected Object module;
  protected Object testResult;
  
  // No direct instantiation. Use the static methods.
  private Example() {}
  
  /**
   * Return the implementation class (the class in the Java sense)
   * of the top Newspeak class of the test source unit.
   */
  public Class<? extends Object> topJavaClass() { 
    return topImplementationClass; 
    }
  
  /**
   * Return the name (in the Newspeak sense) of the top class of the test source
   * unit.
   */
  public String topClassName() { 
    return compilationResults.get(0).ast().name(); 
  }
  
  /**
   * Return the Newspeak instance of the top class of the test source unit.
   */
  public Object module() {
    return module;
  }
  
  /**
   * Return the object returned by the test method.
   */
  public Object result() { return testResult; }
  
  /**
   * Return the Newspeak name of the class of the object returned by the test
   * method.
   */
  public String resultClassName() {
    return testResult.nsClass().name();
  }
  
  /**
   * Return true if the result of the test is a Newspeak string with the
   * specified contents.
   */
  public boolean isResult(String string) {
    return testResult instanceof Builtins.StringObject
        && ((Builtins.StringObject) testResult).value().equals(string);
  }
  
  /*
   * No API below
   */

  @SuppressWarnings("unchecked")
  protected void compile(String classSource) {
    compilationResults = Compiler.compile(classSource);
    NewspeakClassLoader classLoader = new NewspeakClassLoader();
    compilationResults.forEach(each 
        -> classLoader.addBytecode(each.implementationClassName(), each.bytecode()));
    classLoader.dumpClassFiles();

    try {
      topImplementationClass = (Class<? extends Object>) classLoader.loadClass(compilationResults.get(0).implementationClassName());
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("failure loading the compiled Newspeak class");
    }
  }
  
  protected void runTest() {
    topClassDef = ClassDefinition.create(topClassName(), topImplementationClass);
    topFactory = ObjectFactory.create(topClassDef, null);
    module = topFactory.makeInstance();
    testResult = invoke(module, NamingPolicy.methodNameForSelector("test"));
  }
  
  protected static Object invoke(Object object, String methodName) {
    try {
      Method method = object.getClass().getMethod(methodName);
      return (Object) method.invoke(object);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

}
