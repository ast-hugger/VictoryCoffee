package org.newspeaklanguage.apps;

import java.lang.reflect.Method;
import java.util.List;

import org.newspeaklanguage.compiler.Compiler;
import org.newspeaklanguage.runtime.ClassDefinition;
import org.newspeaklanguage.runtime.NewspeakClassLoader;
import org.newspeaklanguage.runtime.Object;
import org.newspeaklanguage.runtime.ObjectFactory;

public class TrySimpleApp {

  private static final String source =
"class App = (         \n"
+ ")                   \n"
+ "('testing'          \n"
+ "main = (            \n"
+ "  ^nil printString  \n"
+ ")                   \n"
+ ")";
  
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    say("Compiling...");
    List<Compiler.Result> results = Compiler.compile(source);
    say("Loading...");
    NewspeakClassLoader classLoader = new NewspeakClassLoader(TrySimpleApp.class.getClassLoader());
    results.forEach(each 
        -> classLoader.addBytecode(each.implementationClassName(), each.bytecode()));
    classLoader.dumpClassFiles();
    Class<? extends Object> topClass;
    try {
      topClass = (Class<? extends Object>) classLoader.loadClass(results.get(0).implementationClassName());
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("failure loading the compiled Newspeak class");
    }
    say("Setting up...");
    ClassDefinition classDef = ClassDefinition.create("App", topClass);
    ObjectFactory factory = ObjectFactory.create(classDef, null);
    say("Running...");
    Object module = factory.makeInstance();
    Object x = invoke(module, "$main");
    say("Result: " + x);
    say("Done.");
  }

  private static void say(String message) {
    System.out.println(message);
  }
  
  private static Object invoke(Object object, String methodName) {
    try {
      Method method = object.getClass().getMethod(methodName);
      return (Object) method.invoke(object);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
}
