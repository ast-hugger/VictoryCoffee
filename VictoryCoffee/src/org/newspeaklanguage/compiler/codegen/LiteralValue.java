package org.newspeaklanguage.compiler.codegen;

import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.runtime.Builtins;
import org.newspeaklanguage.runtime.ClassDefinition;
import org.newspeaklanguage.runtime.ClosureLiteral;
import org.newspeaklanguage.runtime.Object;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

abstract class LiteralValue implements ClassInitializerSnippet {

  /*
   * Public API
   */
  
  public static LiteralValue forString(String value) {
    return new LiteralString(value);
  }
  
  public static LiteralValue forClosureBody(String internalClassName, String implementationMethodName) {
    return new ClosureBody(internalClassName, implementationMethodName);
  }
  
  /*
   * Private subclasses for the specific kinds of literal value
   */
  
  private static class LiteralString extends LiteralValue {
    
    private final String value;
    private LiteralString(String value) {
      this.value = value;
    }
    
    @Override
    public java.lang.Object key() { return value; }
    
    @Override
    public String typeName() { return "String"; }
    
    @Override
    protected void generateValueCreation(MethodVisitor visitor) {
      visitor.visitTypeInsn(Opcodes.NEW, Builtins.StringObject.INTERNAL_CLASS_NAME);
      visitor.visitInsn(Opcodes.DUP);
      visitor.visitLdcInsn(value);
      visitor.visitMethodInsn(
          Opcodes.INVOKESPECIAL,
          Builtins.StringObject.INTERNAL_CLASS_NAME,
          "<init>",
          "(Ljava/lang/String;)V",
          false);
    }
  }
  
  private static class ClosureBody extends LiteralValue {
    
    private final String internalClassName;
    private final String implementationMethodName;
    
    private ClosureBody(String internalClassName, String implementationMethodName) {
      this.internalClassName = internalClassName;
      this.implementationMethodName = implementationMethodName;
    }

    @Override
    public java.lang.Object key() {
      return implementationMethodName;
    }

    @Override
    public String typeName() {
      return "Closure";
    }

    @Override
    public void generateFieldDefinition(ClassVisitor classVisitor) {
      FieldVisitor fieldVisitor = classVisitor.visitField(
          Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
          fieldName,
          ClosureLiteral.TYPE_DESCRIPTOR,
          null, null);
      fieldVisitor.visitEnd();
    }
    

    @Override
    public void generateLoad(MethodVisitor methodWriter) {
      // new Builtins.Closure
      methodWriter.visitTypeInsn(Opcodes.NEW, Builtins.Closure.INTERNAL_CLASS_NAME);
      methodWriter.visitInsn(Opcodes.DUP);
      // Builtins.Closure.<init>(ClosureLiteral closureLiteral, StandardObject self)
      methodWriter.visitFieldInsn(
          Opcodes.GETSTATIC,
          className,
          fieldName,
          ClosureLiteral.TYPE_DESCRIPTOR);
      methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
      methodWriter.visitMethodInsn(
          Opcodes.INVOKESPECIAL, 
          Builtins.Closure.INTERNAL_CLASS_NAME, 
          "<init>",
          Builtins.Closure.CONSTRUCTOR_DESCRIPTOR, 
          false);
    }
    
    // TODO all of this is special code; no need to have this as a LiteralValue
    
    @Override
    public void generateInitializerCode(MethodVisitor visitor) {
      generateValueCreation(visitor);
      visitor.visitFieldInsn(
          Opcodes.PUTSTATIC,
          className, 
          fieldName,
          ClosureLiteral.TYPE_DESCRIPTOR);
    }
    
   @Override
    protected void generateValueCreation(MethodVisitor methodWriter) {
      methodWriter.visitTypeInsn(Opcodes.NEW, ClosureLiteral.INTERNAL_CLASS_NAME);
      methodWriter.visitInsn(Opcodes.DUP);
      // ClosureObject.<init>(Class implementationClass, String methodName)
      methodWriter.visitLdcInsn(Type.getType("L" + internalClassName + ";"));
      methodWriter.visitLdcInsn(implementationMethodName);
      methodWriter.visitMethodInsn(
          Opcodes.INVOKESPECIAL, 
          ClosureLiteral.INTERNAL_CLASS_NAME, 
          "<init>",
          ClosureLiteral.CONSTRUCTOR_DESCRIPTOR, 
          false);
    }
    
  }
  
  /*
   * Instance side
   */
  
  /** The internal name of the class to hold the field with the literal. */
  protected String className;
  /** The name of the field with the literal. */
  protected String fieldName;
    
  // package access for ClassGenerator
  void setClassName(String name) {
    this.className = name;
  }

  // package access for ClassGenerator
  void setFieldName(String name) {
    this.fieldName = name;
  }
  
  @Override
  public boolean equals(java.lang.Object another) {
    return another != null
        && another.getClass() == getClass()
        && ((LiteralValue) another).key().equals(key());
  }
  
  public abstract java.lang.Object key();
  public abstract String typeName();
  
  public void generateFieldDefinition(ClassVisitor classVisitor) {
    FieldVisitor fieldVisitor = classVisitor.visitField(
        Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
        fieldName,
        Object.TYPE_DESCRIPTOR,
        null, null);
    fieldVisitor.visitEnd();
  }
  
  @Override
  public void generateInitializerCode(MethodVisitor visitor) {
    generateValueCreation(visitor);
    visitor.visitFieldInsn(
        Opcodes.PUTSTATIC,
        className, 
        fieldName,
        Object.TYPE_DESCRIPTOR);
  }
  
  protected abstract void generateValueCreation(MethodVisitor methodVisitor);
  
  public void generateLoad(MethodVisitor visitor) {
    visitor.visitFieldInsn(
        Opcodes.GETSTATIC,
        className,
        fieldName,
        Object.TYPE_DESCRIPTOR);
  }
}
