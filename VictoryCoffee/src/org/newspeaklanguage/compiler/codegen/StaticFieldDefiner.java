package org.newspeaklanguage.compiler.codegen;

import org.objectweb.asm.ClassWriter;

public interface StaticFieldDefiner {
  
  public void generateField(ClassWriter classWriter);

}
