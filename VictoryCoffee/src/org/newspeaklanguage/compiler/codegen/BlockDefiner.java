package org.newspeaklanguage.compiler.codegen;

import java.util.Arrays;

import org.newspeaklanguage.compiler.Descriptor;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.Method;
import org.newspeaklanguage.runtime.BlockHandle;
import org.newspeaklanguage.runtime.Object;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * A record created by {@link ClassGenerator} to keep track of blocks in
 * a method. One is associated with every Block node and used by the
 * generator to produce the associated code.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class BlockDefiner implements StaticFieldDefiner {
  
  public static String descriptor(int arity) {
    Class<?>[] argTypes = new Class<?>[arity];
    Arrays.setAll(argTypes, i -> Object.class);
    return Descriptor.ofMethod(Object.class, argTypes);
  }
  
  /*
   * Instance side
   */

  /** The node of the block we are defining. */
  private final Block blockNode;
  /** The method containing the block. */
  private final Method hostMethod;
  private final String internalClassName;
  /** The name of the method we will generate with the code of this block. */
  private final String methodName;
  
  BlockDefiner(Block blockNode, Method hostMethod, String internalClassName, String methodName) {
    this.blockNode = blockNode;
    this.hostMethod = hostMethod;
    this.internalClassName = internalClassName;
    this.methodName = methodName;
  }
  
  public Block blockNode() { return blockNode; }
  public Method hostMethod() { return hostMethod; }
  public String internalClassName() { return internalClassName; }
  public String methodName() { return methodName; }
  public String fieldName() { return methodName; }
  
  @Override
  public void generateField(ClassWriter classWriter) {
    FieldVisitor fieldWriter = classWriter.visitField(
        Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
        fieldName(),
        BlockHandle.TYPE_DESCRIPTOR,
        null, null);
    fieldWriter.visitEnd();
  }

  @Override
  public void generateClinitFragment(MethodVisitor methodWriter) {
    methodWriter.visitTypeInsn(Opcodes.NEW, BlockHandle.INTERNAL_CLASS_NAME);
    methodWriter.visitInsn(Opcodes.DUP);
    // BlockHandle.<init>(Class implementationClass, String methodName, int arity)
    methodWriter.visitLdcInsn(Type.getType("L" + internalClassName() + ";"));
    methodWriter.visitLdcInsn(methodName());
    CodeGenerator.generateLoadInt(methodWriter, blockNode.arity());
    methodWriter.visitMethodInsn(
        Opcodes.INVOKESPECIAL, 
        BlockHandle.INTERNAL_CLASS_NAME, 
        "<init>",
        BlockHandle.CONSTRUCTOR_DESCRIPTOR, 
        false);
    methodWriter.visitFieldInsn(
        Opcodes.PUTSTATIC,
        internalClassName(), 
        fieldName(),
        BlockHandle.TYPE_DESCRIPTOR);
  }

  public String descriptor() {
    return descriptor(blockNode.arity());
  }
  
}
