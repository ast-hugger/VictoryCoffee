package org.newspeaklanguage.compiler.codegen;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

/**
 * We often need to add a static field to the class being generated,
 * and initialize the field in a certain way. This is necessary for
 * things such as literals, blocks, or runtime bookkeeping. The
 * {@link ClassGenerator} keeps track of all of those using
 * subtypes of this type.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public interface StaticFieldDefiner {
  
  public void generateField(ClassWriter classWriter);
  public void generateClinitFragment(MethodVisitor methodWriter);

}
