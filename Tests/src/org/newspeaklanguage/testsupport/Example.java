/*
 * Copyright (c) 2016 Vassili Bykov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.newspeaklanguage.testsupport;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.List;

import org.newspeaklanguage.compiler.Compiler;
import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.compiler.codegen.CpsMonkey;
import org.newspeaklanguage.runtime.Builtins;
import org.newspeaklanguage.runtime.ClassDefinition;
import org.newspeaklanguage.runtime.NewspeakClassLoader;
import org.newspeaklanguage.runtime.NsObject;
import org.newspeaklanguage.runtime.ObjectFactory;
import org.newspeaklanguage.runtime.StandardObject;

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
  protected Class<? extends StandardObject> topImplementationClass;
  protected ClassDefinition topClassDef;
  protected ObjectFactory topFactory;
  protected StandardObject module;
  protected Object testResult;
  
  // No direct instantiation. Use the static methods.
  private Example() {}
  
  /**
   * Return the implementation class (the class in the Java sense)
   * of the top Newspeak class of the test source unit.
   */
  public Class<? extends NsObject> topJavaClass() {
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
  public NsObject module() {
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
    return testResult.getClass().getName();
  }
  
  /**
   * Return true if the result of the test is a Newspeak string with the
   * specified contents.
   */
  public boolean isResult(String string) {
    return testResult instanceof String
        && ((String) testResult).equals(string);
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
      topImplementationClass = (Class<? extends StandardObject>) classLoader.loadClass(compilationResults.get(0).implementationClassName());
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


  
  protected static Object invoke(NsObject object, String methodName) {
    try {
      Method method = object.getClass().getMethod(methodName, MethodHandle.class);
      MethodHandle k = MethodHandles.lookup()
          .findStatic(Example.class, "acceptResult", CpsMonkey.CONTINUATION_TYPE);
      method.invoke(object, k);
      return lastResult;
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  private static Object lastResult;

  @SuppressWarnings("unused") // called via continuation plumbing
  private static void acceptResult(Object objectResult, int intResult) {
    lastResult = objectResult == NsObject.UNDEFINED ? intResult : objectResult;
  }



}
