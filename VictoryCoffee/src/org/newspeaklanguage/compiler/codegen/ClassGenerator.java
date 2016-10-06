package org.newspeaklanguage.compiler.codegen;

import java.util.HashMap;
import java.util.Map;

import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.compiler.ast.Category;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.ast.Method;
import org.newspeaklanguage.compiler.ast.SlotDefinition;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.newspeaklanguage.runtime.Object;
import org.newspeaklanguage.runtime.StandardObject;

public class ClassGenerator {
  
  public static final String GETTER_DESCRIPTOR = "()" + Object.TYPE_DESCRIPTOR;
  public static final String SETTER_DESCRIPTOR = "(" + Object.TYPE_DESCRIPTOR + ")V";

  public static byte[] generate(ClassDecl classNode) {
    ClassGenerator generator = new ClassGenerator(classNode);
    return generator.generate();
  }
  
  /*
   * Instance side
   */
  
  private final ClassDecl classNode;
  private final ClassWriter classWriter = new ClassWriter(
      ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
  private final Map<java.lang.Object, LiteralValue> literals = 
      new HashMap<java.lang.Object, LiteralValue>(); 
  
  private ClassGenerator(ClassDecl classNode) {
    this.classNode = classNode;
  }
  
  public byte[] generate() {
    start();
    processSlots();
    processNestedClasses();
    generateConstructor();
    processMethods();
    processLiterals();
    return finish();
  }
  
  public LiteralValue lookupLiteral(java.lang.Object key) {
    return literals.get(key);
  }
  
  public void addLiteral(LiteralValue literal) {
    literal.setClassName(classNode.implementationClassName());;
    literal.setFieldName(allocateLiteralFieldName());
    literals.put(literal.key(), literal);
  }
  
  private String allocateLiteralFieldName() {
    return "Literal$" + literals.size();
  }
  
  private void start() {
    // TODO bogus: assuming the superclass is always Object
    classWriter.visit(
        52,
        Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER,
        toInternalFormat(classNode.implementationClassName()),
        null,
        StandardObject.INTERNAL_CLASS_NAME,
        null);
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
  
  private void generateNestedClassGetter(ClassDecl classNode) {
    String selector = NamingPolicy.getterSelectorForSlot(classNode.name());
    MethodVisitor methodVisitor = classWriter.visitMethod(
        Opcodes.ACC_PUBLIC,
        selector,
        GETTER_DESCRIPTOR,
        null, null);
    methodVisitor.visitCode();
    
    
    methodVisitor.visitMaxs(0, 0); // args ignored
    methodVisitor.visitEnd();
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
        NamingPolicy.setterSelectorForSlot(slot.name()),
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
  
  private void processLiterals() {
    if (!literals.isEmpty()) {
      literals.values().forEach(each -> each.generateFieldDefinition(classWriter));
      generateClassInitializer();
    }
  }
  
  private void generateClassInitializer() {
    MethodVisitor methodWriter = classWriter.visitMethod(
        Opcodes.ACC_STATIC,
        "<clinit>",
        "()V",
        null, null);
    methodWriter.visitCode();
    literals.values().forEach(each -> each.generateInitializer(methodWriter));
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
