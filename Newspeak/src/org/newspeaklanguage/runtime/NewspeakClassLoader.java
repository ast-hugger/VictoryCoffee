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

package org.newspeaklanguage.runtime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NewspeakClassLoader extends ClassLoader {
  
  private final Map<String, byte[]> bytecodeByName = new HashMap<String, byte[]>();
  private final Map<String, Class<? extends NsObject>> classesByName =
      new HashMap<String, Class<? extends NsObject>>();

  public NewspeakClassLoader() {
    // Still not sure if we need or need not to inherit from the main loader,
    // so provide both constructors for now.
  }
  
  public NewspeakClassLoader(ClassLoader parent) {
    super(parent);
  }
  
  public void addBytecode(String className, byte[] bytecode) {
    bytecodeByName.put(className, bytecode);
  }
  
  @Override
  @SuppressWarnings("unchecked")
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    Class<?> loadedClass = classesByName.get(name);
    if (loadedClass == null) {
      byte[] bytecode = bytecodeByName.get(name);
      if (bytecode == null) {
        throw new ClassNotFoundException(name);
      }
      loadedClass = defineClass(name, bytecode, 0, bytecode.length);
      classesByName.put(name, (Class<? extends NsObject>) loadedClass);
    }
    return loadedClass;
  }
  
  public void dumpClassFiles() {
    File dumpDirectory = new File("classdump");
    if (!dumpDirectory.exists()) {
      dumpDirectory.mkdirs();
    }
    for (String className : bytecodeByName.keySet()) {
      byte[] bytecode = bytecodeByName.get(className);
      File classFile = new File(dumpDirectory, className + ".class");
      try {
        FileOutputStream classStream = new FileOutputStream(classFile);
        classStream.write(bytecode);
        classStream.close();
      } catch (IOException e) {
        // so what
      }
    }
  }
}
