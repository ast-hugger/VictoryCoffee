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

package org.newspeaklanguage.compiler.codegen;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.newspeaklanguage.compiler.Descriptor;
import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.Category;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.ast.Method;
import org.newspeaklanguage.compiler.ast.SlotDefinition;
import org.newspeaklanguage.runtime.ClassDefinition;
import org.newspeaklanguage.runtime.ObjectFactory;
import org.newspeaklanguage.runtime.StandardObject;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassGenerator {

  // Both getter and setter methods, like all other methods compiled as methods of the implementation class,
  // have the first unused int argument, which is the int part of the value pair which represented the receiver
  // on the stack before the dynamic call.

  public static final String GETTER_DESCRIPTOR = "(I)" + Descriptor.OBJECT_TYPE_DESCRIPTOR;
  public static final String SETTER_DESCRIPTOR
      = "(I" + Descriptor.OBJECT_TYPE_DESCRIPTOR + Descriptor.INT_TYPE_DESCRIPTOR + ")" + Descriptor.OBJECT_TYPE_DESCRIPTOR;
  
  public static byte[] generate(ClassDecl classNode) {
    ClassGenerator generator = new ClassGenerator(classNode);
    return generator.generate();
  }
  
  /*
   * Instance side
   */
  
  private final ClassDecl classNode;
  private final String internalClassName;
  private final ClassWriter classWriter = new NsClassWriter(ClassWriter.COMPUTE_FRAMES);
  private final Map<java.lang.Object, LiteralValue> literals = new HashMap<java.lang.Object, LiteralValue>();
  private final List<StaticFieldDefiner> staticFieldDefiners = new LinkedList<StaticFieldDefiner>();
  
  private ClassGenerator(ClassDecl classNode) {
    this.classNode = classNode;
    this.internalClassName = toInternalFormat(classNode.implementationClassName());
  }
  
  public String internalClassName() {
    return internalClassName;
  }
  
  public ClassWriter classWriter() {
    return classWriter;
  }
  
  public byte[] generate() {
    start();
    setupClassDefField();
    processSlots();
    processNestedClasses();
    generateConstructor();
    processMethods();
    processLiterals();
    generateClassInitializer();
    return finish();
  }
  
  public LiteralValue lookupLiteral(java.lang.Object key) {
    return literals.get(key);
  }
  
  public void addLiteral(LiteralValue literal) {
    literal.setClassName(classNode.implementationClassName());
    if (literal.fieldName == null) {
      literal.setFieldName(allocateLiteralFieldName(literal));
    }
    literals.put(literal.key(), literal);
    staticFieldDefiners.add(literal);
  }
  
  private String allocateLiteralFieldName(LiteralValue literal) {
    return "$literal" + literals.size() + "$";
  }
  
  private void start() {
    // TODO bogus: assuming the superclass is always NsObject
    classWriter.visit(
        Opcodes.V1_8,
        Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER,
        internalClassName,
        null,
        StandardObject.INTERNAL_CLASS_NAME,
        null);
  }
  
  private void setupClassDefField() {
    ClassDefinitionDefiner classDef = new ClassDefinitionDefiner(classNode);
    classDef.generateField(classWriter);
    staticFieldDefiners.add(classDef);
  }
  
  private void processSlots() {
    for(SlotDefinition slot : classNode.slots()) {
      FieldVisitor fieldVisitor = classWriter.visitField(
          Opcodes.ACC_PUBLIC,
          NamingPolicy.fieldNameForSlot(slot.name()),
          Descriptor.OBJECT_TYPE_DESCRIPTOR,
          null, null);
      fieldVisitor.visitEnd();
      fieldVisitor = classWriter.visitField(
          Opcodes.ACC_PUBLIC,
          NamingPolicy.fieldNameForPrimitiveSlot(slot.name()),
          Descriptor.INT_TYPE_DESCRIPTOR,
          null, null);
      fieldVisitor.visitEnd();
      generateSlotGetter(slot);
      if (slot.isMutable()) {
        generateSlotSetter(slot);
      }
    }
  }

  /**
   * Generate a getter for the specified slot.
   */
  private void generateSlotGetter(SlotDefinition slot) {
    MethodVisitor methodWriter = classWriter.visitMethod(
        Opcodes.ACC_PUBLIC,
        NamingPolicy.getterMethodNameForSlot(slot.name()),
        GETTER_DESCRIPTOR,
        null, null);
    methodWriter.visitCode();
    methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
    methodWriter.visitFieldInsn(
        Opcodes.GETFIELD,
        toInternalFormat(classNode.implementationClassName()),
        NamingPolicy.fieldNameForSlot(slot.name()),
        Descriptor.OBJECT_TYPE_DESCRIPTOR);
    methodWriter.visitInsn(Opcodes.DUP);
    CodeGenerator.generateLoadUndefined(methodWriter); // stack: Object, Object, Undefined
    Label objectPresent = new Label();
    methodWriter.visitJumpInsn(Opcodes.IF_ACMPNE, objectPresent); // stack: Object
    // the real value is in the primitive int field
    methodWriter.visitInsn(Opcodes.POP); // remove so stacks match at the objectPresent join
    methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
    methodWriter.visitFieldInsn(
        Opcodes.GETFIELD,
        toInternalFormat(classNode.implementationClassName()),
        NamingPolicy.fieldNameForPrimitiveSlot(slot.name()),
        Descriptor.INT_TYPE_DESCRIPTOR);
    CodeGenerator.generateCreateReturnPrimitiveValue(methodWriter);
// objectPresent:
    methodWriter.visitLabel(objectPresent);
    methodWriter.visitInsn(Opcodes.ARETURN);
    methodWriter.visitMaxs(0, 0); // args ignored
    methodWriter.visitEnd();
  }

  /**
   * Generate a setter method for the specified slot. The method has the signature of:
   * <pre>
   *   public void <setterMethodName>(int unused, Object objectValue, int intValue)
   * </pre>
   * So objectValue is local 2 and intValue is local 3.
   */
  private void generateSlotSetter(SlotDefinition slot) {
    MethodVisitor methodVisitor = classWriter.visitMethod(
        Opcodes.ACC_PUBLIC,
        NamingPolicy.setterMethodNameForSlot(slot.name()),
        SETTER_DESCRIPTOR,
        null, null);
    methodVisitor.visitCode();
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
    methodVisitor.visitInsn(Opcodes.DUP);
    // store the Object part
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 2);
    methodVisitor.visitFieldInsn(
        Opcodes.PUTFIELD,
        toInternalFormat(classNode.implementationClassName()),
        NamingPolicy.fieldNameForSlot(slot.name()),
        Descriptor.OBJECT_TYPE_DESCRIPTOR);
    // store the int part
    methodVisitor.visitVarInsn(Opcodes.ILOAD, 3);
    methodVisitor.visitFieldInsn(
        Opcodes.PUTFIELD,
        toInternalFormat(classNode.implementationClassName()),
        NamingPolicy.fieldNameForPrimitiveSlot(slot.name()),
        Descriptor.INT_TYPE_DESCRIPTOR);
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
    methodVisitor.visitInsn(Opcodes.ARETURN);
    methodVisitor.visitMaxs(3, 3); // args correct but ignored
    methodVisitor.visitEnd();
  }

  /**
   * Generate accessors for nested classes.
   */
  private void processNestedClasses() {
    classNode.nestedClasses().forEach(this::generateNestedClassGetter);
  }

  private void generateNestedClassGetter(ClassDecl nestedClassDecl) {
    String selector = NamingPolicy.getterMethodNameForSlot(nestedClassDecl.name());
    MethodVisitor methodWriter = classWriter.visitMethod(
        Opcodes.ACC_PUBLIC,
        selector,
        GETTER_DESCRIPTOR,
        null, null);
    methodWriter.visitCode();

    methodWriter.visitTypeInsn(Opcodes.NEW, ObjectFactory.INTERNAL_CLASS_NAME);
    methodWriter.visitInsn(Opcodes.DUP);
    // The first argument: the object factory (of this factory). Just use null for now
    methodWriter.visitInsn(Opcodes.ACONST_NULL);
    // The second argument: the class definition.
    methodWriter.visitFieldInsn(
        Opcodes.GETSTATIC,
        nestedClassDecl.implementationClassName(),
        NamingPolicy.CLASS_DEF_FIELD_NAME,
        ClassDefinition.TYPE_DESCRIPTOR);
    // The third argument: the containing object of the class--the receiver.
    methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
    methodWriter.visitMethodInsn(
        Opcodes.INVOKESPECIAL,
        ObjectFactory.INTERNAL_CLASS_NAME,
        "<init>",
        ObjectFactory.CONSTRUCTOR_DESCRIPTOR,
        false);

    methodWriter.visitInsn(Opcodes.ARETURN);
    methodWriter.visitMaxs(0, 0); // args ignored
    methodWriter.visitEnd();
  }

  private void generateConstructor() {
    MethodVisitor methodVisitor = classWriter.visitMethod(
        Opcodes.ACC_PUBLIC,
        "<init>",
        StandardObject.CONSTRUCTOR_DESCRIPTOR,
        null, null);
    methodVisitor.visitCode();
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
    // TODO bogus: assuming the superclass is always NsObject
    methodVisitor.visitMethodInsn(
        Opcodes.INVOKESPECIAL,
        StandardObject.INTERNAL_CLASS_NAME,
        "<init>",
        StandardObject.CONSTRUCTOR_DESCRIPTOR,
        false);
    methodVisitor.visitInsn(Opcodes.RETURN);
    methodVisitor.visitMaxs(2, 2); // args ignored; ClassWriter computes them
    methodVisitor.visitEnd();
  }
  
  private void processMethods() {
    for (Category category : classNode.categories()) {
      for (Method method : category.methods()) {
        preprocessBlocksInMethod(method);
        MethodVisitor methodVisitor = classWriter.visitMethod(
            Opcodes.ACC_PUBLIC,
            NamingPolicy.methodNameForSelector(method.messagePattern().selector()),
            Descriptor.ofMethodImplMethod(method.messagePattern().arity()),
            null, null);
        MethodGenerator methodGenerator = new MethodGenerator(this, method, methodVisitor);
        methodGenerator.generate();
      }
    }
  }
  
  private void preprocessBlocksInMethod(Method method) {
    int i = 0;
    for (Block block : method.containedBlocks()) {
      String implMethodName = NamingPolicy.methodNameForClosure(method.selector(), i++);
      BlockDefiner definer = new BlockDefiner(block, method, internalClassName, implMethodName);
      block.setDefiner(definer);
      definer.generateField(classWriter);
      staticFieldDefiners.add(definer);
    }
    for (Block block : method.containedBlocks()) {
      generateBlockImplementation(block.definer());
    }
  }
  
  /**
   * Generate the method with the code representing the block body.
   */
  private void generateBlockImplementation(BlockDefiner definer) {
    MethodVisitor writer = classWriter.visitMethod(
        Opcodes.ACC_PUBLIC, 
        definer.methodName(), 
        definer.descriptor(), 
        null, null);
    CodeGenerator generator = new BlockGenerator(this, definer.blockNode(), writer);
    generator.generate();
  }
  
  private void processLiterals() {
    literals.values().forEach(each -> each.generateField(classWriter));
  }
  
  private void generateClassInitializer() {
    MethodVisitor methodWriter = classWriter.visitMethod(
        Opcodes.ACC_STATIC,
        "<clinit>",
        "()V",
        null, null);
    methodWriter.visitCode();
    staticFieldDefiners.forEach(each -> each.generateClinitFragment(methodWriter));
    methodWriter.visitInsn(Opcodes.RETURN);
    methodWriter.visitMaxs(0, 0); // args ignored
    methodWriter.visitEnd();
  }
  
  private byte[] finish() {
    classWriter.visitEnd();
    return classWriter.toByteArray();
  }
  
  private String methodTypeDescriptor(int arity) {
    StringBuilder result = new StringBuilder();
    result.append("(");
    for (int i = 0; i < arity; i++) {
      result.append(Descriptor.OBJECT_TYPE_DESCRIPTOR);
    }
    result.append(")");
    result.append(Descriptor.OBJECT_TYPE_DESCRIPTOR);
    return result.toString();
  }
  
  private String toInternalFormat(String className) {
    return className.replace('.', '/');
  }
} 
