package org.newspeaklanguage.compiler.codegen;

import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.compiler.ast.Category;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.ast.MessagePattern;
import org.newspeaklanguage.compiler.ast.Method;
import org.newspeaklanguage.compiler.ast.SlotDefinition;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.newspeaklanguage.runtime.Object;

public class ClassGenerator {
  
  public static final String GETTER_DESCRIPTOR = "()" + Object.TYPE_DESCRIPTOR;
  public static final String SETTER_DESCRIPTOR = "(" + Object.TYPE_DESCRIPTOR + ")V";

  public static byte[] generate(ClassDecl classNode) {
    ClassGenerator generator = new ClassGenerator(classNode);
    return generator.generate();
  }
  
  private final ClassDecl classNode;
  private final ClassWriter classWriter = new ClassWriter(
      ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
  
  private ClassGenerator(ClassDecl classNode) {
    this.classNode = classNode;
  }
  
  public byte[] generate() {
    start();
    processSlots();
    generateConstructor();
    processMethods();
    return finish();
  }
  
  private void start() {
    // TODO bogus: assuming the superclass is always Object
    classWriter.visit(52, Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER, className(), null,
        Object.INTERNAL_CLASS_NAME, null);
  }
  
  private void processSlots() {
    for(SlotDefinition slot : classNode.slots()) {
      FieldVisitor fieldVisitor = classWriter.visitField(
          Opcodes.ACC_PUBLIC,
          NamingPolicy.fieldNameForSlot(slot.name()),
          Object.TYPE_DESCRIPTOR,
          null, null);
      fieldVisitor.visitEnd();
      generateSlotGetter(slot);
      if (slot.isMutable()) {
        generateSlotSetter(slot);
      }
    }
  }
  
  private void generateSlotGetter(SlotDefinition slot) {
    MethodVisitor methodVisitor = classWriter.visitMethod(
        Opcodes.ACC_PUBLIC,
        NamingPolicy.getterSelectorForSlot(slot.name()),
        GETTER_DESCRIPTOR,
        null, null);
    methodVisitor.visitCode();
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
    methodVisitor.visitFieldInsn(
        Opcodes.GETFIELD, 
        className(), 
        NamingPolicy.fieldNameForSlot(slot.name()),
        Object.TYPE_DESCRIPTOR);
    methodVisitor.visitInsn(Opcodes.ARETURN);
    methodVisitor.visitMaxs(1, 1); // args correct but ignored
    methodVisitor.visitEnd();
  }
  
  private void generateSlotSetter(SlotDefinition slot) {
    MethodVisitor methodVisitor = classWriter.visitMethod(
        Opcodes.ACC_PUBLIC,
        NamingPolicy.setterSelectorForSlot(slot.name()),
        SETTER_DESCRIPTOR,
        null, null);
    methodVisitor.visitCode();
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
    methodVisitor.visitFieldInsn(
        Opcodes.PUTFIELD, 
        className(), 
        NamingPolicy.fieldNameForSlot(slot.name()),
        Object.TYPE_DESCRIPTOR);
    methodVisitor.visitInsn(Opcodes.RETURN);
    methodVisitor.visitMaxs(2, 2); // args correct but ignored
    methodVisitor.visitEnd();    
  }
  
  private void generateConstructor() {
    MethodVisitor methodVisitor = classWriter.visitMethod(
        Opcodes.ACC_PUBLIC,
        "<init>",
        Object.CONSTRUCTOR_DESCRIPTOR,
        null, null);
    methodVisitor.visitCode();
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
    // TODO bogus: assuming the superclass is always Object
    methodVisitor.visitMethodInsn(
        Opcodes.INVOKESPECIAL,
        Object.INTERNAL_CLASS_NAME,
        "<init>",
        Object.CONSTRUCTOR_DESCRIPTOR,
        false);
    methodVisitor.visitInsn(Opcodes.RETURN);
    methodVisitor.visitMaxs(2, 2); // args ignored; ClassWriter computes them
    methodVisitor.visitEnd();
  }
  
  private void processMethods() {
    for (Category category : classNode.categories()) {
      for (Method method : category.methods()) {
        MethodVisitor methodVisitor = classWriter.visitMethod(
            Opcodes.ACC_PUBLIC,
            NamingPolicy.methodNameForSelector(method.messagePattern().selector()),
            methodTypeDescriptor(method.messagePattern().arity()),
            null, null);
        MethodGenerator methodGenerator = new MethodGenerator(method, methodVisitor);
        methodGenerator.generate();
      }
    }
  }
  
  private byte[] finish() {
    classWriter.visitEnd();
    return classWriter.toByteArray();
  }
  
  private String className() {
    // TODO should do the right thing for nested classes here
    return classNode.name();
  }

  private String methodTypeDescriptor(int arity) {
    StringBuilder result = new StringBuilder();
    result.append("(");
    for (int i = 0; i < arity; i++) {
      result.append(Object.TYPE_DESCRIPTOR);
    }
    result.append(")");
    result.append(Object.TYPE_DESCRIPTOR);
    return result.toString();
  }
}
