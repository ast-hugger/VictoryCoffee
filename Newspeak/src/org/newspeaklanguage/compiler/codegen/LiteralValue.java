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

import org.newspeaklanguage.compiler.Descriptor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

abstract class LiteralValue implements StaticFieldDefiner {

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
      visitor.visitLdcInsn(value);
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
  
  public void generateField(ClassWriter classVisitor) {
    FieldVisitor fieldVisitor = classVisitor.visitField(
        Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL,
        fieldName,
        Descriptor.OBJECT_TYPE_DESCRIPTOR,
        null, null);
    fieldVisitor.visitEnd();
  }
  
  @Override
  public void generateClinitFragment(MethodVisitor visitor) {
    generateValueCreation(visitor);
    visitor.visitFieldInsn(
        Opcodes.PUTSTATIC,
        className, 
        fieldName,
        Descriptor.OBJECT_TYPE_DESCRIPTOR);
  }
  
  protected abstract void generateValueCreation(MethodVisitor methodVisitor);
  
  public void generateLoad(MethodVisitor visitor) {
    visitor.visitFieldInsn(
        Opcodes.GETSTATIC,
        className,
        fieldName,
        Descriptor.OBJECT_TYPE_DESCRIPTOR);
  }
}
