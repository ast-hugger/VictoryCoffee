package org.newspeaklanguage.compiler;

import java.lang.reflect.Method;
import java.util.List;

import org.newspeaklanguage.runtime.ClassDefinition;
import org.newspeaklanguage.runtime.NewspeakClassLoader;
import org.newspeaklanguage.runtime.Object;
import org.newspeaklanguage.runtime.ObjectFactory;

public class Runner {

  public static class Result {
    public final Class<? extends Object> topClass;
    public final Object testResult;
    
    private Result(Class<? extends Object> topClass, Object testResult) {
      this.topClass = topClass;
      this.testResult = testResult;
    }
    
    public String resultClassName() {
      return testResult.getClass().getName();
    }
  }
  
  public static Result run(String source) {
    Runner runner = new Runner();
    runner.compile(source);
    Object testResult = runner.runTest();
    return new Result(runner.topImplementationClass, testResult);
  }
  
  /*
   * Instance side
   */
  
  private List<Compiler.Result> results;
  private Class<? extends Object> topImplementationClass;
  
  private Runner() {}
  
  @SuppressWarnings("unchecked")
  public void compile(String classSource) {
    results = Compiler.compile(classSource);
    NewspeakClassLoader classLoader = new NewspeakClassLoader();
    results.forEach(each 
        -> classLoader.addBytecode(each.implementationClassName(), each.bytecode()));
    classLoader.dumpClassFiles();

    try {
      topImplementationClass = (Class<? extends Object>) classLoader.loadClass(results.get(0).implementationClassName());
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("failure loading the compiled Newspeak class");
    }
  }
  
  public String topClassName() {
    return results.get(0).ast().name();
  }
  
  public Object runTest() {
    ClassDefinition classDef = ClassDefinition.create(topClassName(), topImplementationClass);
    ObjectFactory factory = ObjectFactory.create(classDef, null);
    Object module = factory.makeInstance();
    return invoke(module, NamingPolicy.methodNameForSelector("test"));
  }
  
  public static Object invoke(Object object, String methodName) {
    try {
      Method method = object.getClass().getMethod(methodName);
      return (Object) method.invoke(object);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

}
