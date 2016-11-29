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

import org.newspeaklanguage.compiler.Compiler;
import org.newspeaklanguage.runtime.ClassDefinition;
import org.newspeaklanguage.runtime.NewspeakClassLoader;
import org.newspeaklanguage.runtime.NsObject;
import org.newspeaklanguage.runtime.ObjectFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * This is used as the main app class when the whole thing is packaged up as a jar.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 */
public class Runner {
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {

    if (args.length != 1) {
      say("Need a name of a Newspeak file defining a class named App with a 0-argument method named main.");
      return;
    }
    String source;
    try {
      source = readFile(args[0]);
    } catch (IOException e) {
      say("Error reading the file: " + e);
      return;
    }

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

  private static String readFile(String path)
      throws IOException
  {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, Charset.defaultCharset());
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
