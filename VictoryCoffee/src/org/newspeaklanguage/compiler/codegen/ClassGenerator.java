package org.newspeaklanguage.compiler.codegen;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.Category;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.ast.Method;
import org.newspeaklanguage.compiler.ast.SlotDefinition;
import org.newspeaklanguage.runtime.ClassDefinition;
import org.newspeaklanguage.runtime.Object;
import org.newspeaklanguage.runtime.ObjectFactory;
import org.newspeaklanguage.runtime.StandardObject;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassGenerator {
  
  public static final String GETTER_DESCRIPTOR = "()" + Object.TYPE_DESCRIPTOR;
  public static final String SETTER_DESCRIPTOR = "(" + Object.TYPE_DESCRIPTOR + ")" + Object.TYPE_DESCRIPTOR;
  
  public static byte[] generate(ClassDecl classNode) {
    ClassGenerator generator = new ClassGenerator(classNode);
    return generator.generate();
  }
  
  /*
   * Instance side
   */
  
  private final ClassDecl classNode;
  private final String internalClassName;
  private final ClassWriter classWriter = new ClassWriter(
      ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
  private final Map<java.lang.Object, LiteralValue> literals = 
      new HashMap<java.lang.Object, LiteralValue>();
  private final List<ClassInitializerSnippet> classInitializerSnippets = 
      new LinkedList<ClassInitializerSnippet>();
  
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
    generateClassDefField();
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
    classInitializerSnippets.add(literal);
  }
  
  private String allocateLiteralFieldName(LiteralValue literal) {
    return "$literal" + literals.size() + "$";
  }
  
  private void start() {
    // TODO bogus: assuming the superclass is always Object
    classWriter.visit(
        52,
        Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER,
        internalClassName,
        null,
        StandardObject.INTERNAL_CLASS_NAME,
        null);
  }
  
  private void generateClassDefField() {
    FieldVisitor fieldWriter = classWriter.visitField(
        Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
        NamingPolicy.CLASS_DEF_FIELD_NAME,
        ClassDefinition.TYPE_DESCRIPTOR,
        null, null);
    fieldWriter.visitEnd();
    classInitializerSnippets.add(new ClassDefFieldInitializerSnippet(classNode));
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
  
  private void generateSlotGetter(SlotDefinition slot) {
    MethodVisitor methodVisitor = classWriter.visitMethod(
        Opcodes.ACC_PUBLIC,
        NamingPolicy.getterMethodNameForSlot(slot.name()),
        GETTER_DESCRIPTOR,
        null, null);
    methodVisitor.visitCode();
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
    methodVisitor.visitFieldInsn(
        Opcodes.GETFIELD, 
        toInternalFormat(classNode.implementationClassName()), 
        NamingPolicy.fieldNameForSlot(slot.name()),
        Object.TYPE_DESCRIPTOR);
    methodVisitor.visitInsn(Opcodes.ARETURN);
    methodVisitor.visitMaxs(1, 1); // args correct but ignored
    methodVisitor.visitEnd();
  }
  
  private void generateSlotSetter(SlotDefinition slot) {
    MethodVisitor methodVisitor = classWriter.visitMethod(
        Opcodes.ACC_PUBLIC,
        NamingPolicy.setterMethodNameForSlot(slot.name()),
        SETTER_DESCRIPTOR,
        null, null);
    methodVisitor.visitCode();
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
    methodVisitor.visitFieldInsn(
        Opcodes.PUTFIELD, 
        toInternalFormat(classNode.implementationClassName()), 
        NamingPolicy.fieldNameForSlot(slot.name()),
        Object.TYPE_DESCRIPTOR);
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
    methodVisitor.visitInsn(Opcodes.ARETURN);
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
            methodTypeDescriptor(method.messagePattern().arity()),
            null, null);
        MethodGenerator methodGenerator = new MethodGenerator(this, method, methodVisitor);
        methodGenerator.generate();
      }
    }
  }
  
  private void preprocessBlocksInMethod(Method method) {
    int i = 0;
    for (Block block : method.allBlocks()) {
      String implMethodName = NamingPolicy.methodNameForClosure(method.selector(), i++);
      BlockDescriptor descriptor = new BlockDescriptor(
          block, method, internalClassName, implMethodName);
      block.setDescriptor(descriptor);
      descriptor.generateField(classWriter);
      generateBlockImplementation(descriptor);
      classInitializerSnippets.add(descriptor);
    }
  }
  
  private void generateBlockImplementation(BlockDescriptor descriptor) {
    MethodVisitor writer = classWriter.visitMethod(
        Opcodes.ACC_PUBLIC, 
        descriptor.methodName(), 
        // TODO the type descriptor should be computed based on the closure arity
        "()" + Object.TYPE_DESCRIPTOR, 
        null, null);
    CodeGenerator generator = new BlockGenerator(this, descriptor.blockNode(), writer);
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
    classInitializerSnippets.forEach(each -> each.generateClinitFragment(methodWriter));
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
      result.append(Object.TYPE_DESCRIPTOR);
    }
    result.append(")");
    result.append(Object.TYPE_DESCRIPTOR);
    return result.toString();
  }
  
  private String toInternalFormat(String className) {
    return className.replace('.', '/');
  }
} 
