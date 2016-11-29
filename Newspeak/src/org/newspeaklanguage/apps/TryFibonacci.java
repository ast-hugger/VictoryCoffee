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

package org.newspeaklanguage.apps;

import java.lang.reflect.Method;
import java.util.List;

import org.newspeaklanguage.compiler.Compiler;
import org.newspeaklanguage.runtime.ClassDefinition;
import org.newspeaklanguage.runtime.NewspeakClassLoader;
import org.newspeaklanguage.runtime.NsObject;
import org.newspeaklanguage.runtime.ObjectFactory;

public class TryFibonacci {

  private static final String source =
"class App = (         \n"
+ ")                   \n"
+ "('testing'          \n"
+ "main = (            \n"
+ "  ^(fib: 26) printString  \n"
+ ")  "
+ "fib: n = ("
+ "^n < 2 ifTrue: [1] ifFalse: [(fib: n - 1) + (fib: n - 2)]) \n"
+ ")";
  
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    say("Compiling...");
    List<Compiler.Result> results = Compiler.compile(source);
    say("Loading...");
    NewspeakClassLoader classLoader = new NewspeakClassLoader(TryFibonacci.class.getClassLoader());
    results.forEach(each 
        -> classLoader.addBytecode(each.implementationClassName(), each.bytecode()));
    classLoader.dumpClassFiles();
    Class<? extends NsObject> topClass;
    try {
      topClass = (Class<? extends NsObject>) classLoader.loadClass(results.get(0).implementationClassName());
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("failure loading the compiled Newspeak class");
    }
    say("Setting up...");
    ClassDefinition classDef = ClassDefinition.create("App", topClass);
    ObjectFactory factory = ObjectFactory.create(classDef, null);
    Object module = factory.makeInstance();
    System.out.print("Warming up");
    for (int i = 0; i < 3; i++) {
      invoke(module, "$main");
      System.out.print(".");
    }
    System.out.print("\n");
    say("Running...");
    long start = System.nanoTime();
    Object x = invoke(module, "$main");
    long stop = System.nanoTime();
    say("Result: " + x);
    say("in " + ((stop - start) / 1_000_000) + "ms");
    say("Done.");
  }

  private static void say(String message) {
    System.out.println(message);
  }
  
  private static Object invoke(Object object, String methodName) {
    try {
      Method method = object.getClass().getMethod(methodName);
      return method.invoke(object);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
}
