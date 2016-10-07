package org.newspeaklanguage.compiler.codegen;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.newspeaklanguage.runtime.Builtins;
import org.newspeaklanguage.runtime.ClassDefinition;
import org.newspeaklanguage.runtime.Object;

public abstract class LiteralValue {

  /*
   * Public API
   */
  
  public static LiteralValue forString(String value) {
    return new LiteralString(value);
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
  
  public void generateInitializer(MethodVisitor visitor) {
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
