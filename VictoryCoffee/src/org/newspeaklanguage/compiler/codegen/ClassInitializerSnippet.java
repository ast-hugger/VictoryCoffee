package org.newspeaklanguage.compiler.codegen;

import org.objectweb.asm.MethodVisitor;

interface ClassInitializerSnippet {
  
  public void generateInitializerCode(MethodVisitor methodWriter);

}
