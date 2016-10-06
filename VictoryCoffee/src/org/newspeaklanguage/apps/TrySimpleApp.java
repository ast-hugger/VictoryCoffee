package org.newspeaklanguage.apps;

import java.lang.reflect.Method;

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
    Compiler.Result result = Compiler.compile(source);
    say("Loading...");
    NewspeakClassLoader classLoader = new NewspeakClassLoader(TrySimpleApp.class.getClassLoader());
    classLoader.addBytecode(result.className, result.bytecode);
    classLoader.dumpClassFiles();
    Class<? extends Object> mainClass;
    try {
      mainClass = (Class<? extends Object>) classLoader.loadClass(result.className);
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("failure loading the compiled Newspeak class");
    }
    say("Setting up...");
    ClassDefinition classDef = ClassDefinition.create(mainClass);
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
