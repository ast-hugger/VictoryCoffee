package org.newspeaklanguage.infrastructure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.newspeaklanguage.runtime.Object;

public class NewspeakClassLoader extends ClassLoader {
  
  private final Map<String, byte[]> bytecodeByName = new HashMap<String, byte[]>();
  private final Map<String, Class<? extends Object>> classesByName = 
      new HashMap<String, Class<? extends Object>>();

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
      classesByName.put(name, (Class<? extends Object>) loadedClass);
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
