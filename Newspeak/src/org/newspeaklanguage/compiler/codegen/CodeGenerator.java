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

import java.lang.invoke.MethodHandle;
import java.rmi.Naming;

import org.newspeaklanguage.compiler.Descriptor;
import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.compiler.ast.Argument;
import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.AstNodeVisitor;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.Category;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.ast.CodeUnit;
import org.newspeaklanguage.compiler.ast.LiteralBoolean;
import org.newspeaklanguage.compiler.ast.LiteralNil;
import org.newspeaklanguage.compiler.ast.LiteralNumber;
import org.newspeaklanguage.compiler.ast.LiteralString;
import org.newspeaklanguage.compiler.ast.MessagePattern;
import org.newspeaklanguage.compiler.ast.MessageSendNoReceiver;
import org.newspeaklanguage.compiler.ast.MessageSendWithReceiver;
import org.newspeaklanguage.compiler.ast.Method;
import org.newspeaklanguage.compiler.ast.Outer;
import org.newspeaklanguage.compiler.ast.Return;
import org.newspeaklanguage.compiler.ast.Self;
import org.newspeaklanguage.compiler.ast.SlotDefinition;
import org.newspeaklanguage.compiler.ast.Super;
import org.newspeaklanguage.compiler.semantics.LexicalVarReference;
import org.newspeaklanguage.compiler.semantics.LocalVariable;
import org.newspeaklanguage.compiler.semantics.SendToEnclosingObject;
import org.newspeaklanguage.runtime.Box;
import org.newspeaklanguage.runtime.Builtins;
import org.newspeaklanguage.runtime.Closure;
import org.newspeaklanguage.runtime.MessageDispatcher;
import org.newspeaklanguage.runtime.NsObject;
import org.newspeaklanguage.runtime.ObjectFactory;
import org.newspeaklanguage.runtime.StandardObject;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

// TODO blocks are not fully implemented

// TODO super sends are not implemented

/**
 * The generic method code generator. We generate Java methods to represent both
 * the individual Newspeak methods, and blocks contained in them. Those two
 * tasks are handled by the two subclasses of this class.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
abstract class CodeGenerator implements AstNodeVisitor {

  /**
   * Using the supplied MethodVisitor, pick and generate an optimal
   * instruction to load the specified int value.
   */
  public static void generateLoadInt(MethodVisitor methodWriter, int value) {
    if (0 <= value && value <= 5) {
      methodWriter.visitInsn(SPECIAL_LOAD_INT_OPCODES[value]);
    } else if (-128 <= value && value <= 127) {
      methodWriter.visitIntInsn(Opcodes.BIPUSH, value);
    } else {
      methodWriter.visitIntInsn(Opcodes.SIPUSH, value);
    }
  }
  private static final int[] SPECIAL_LOAD_INT_OPCODES = new int[] {
      Opcodes.ICONST_0, Opcodes.ICONST_1, Opcodes.ICONST_2, Opcodes.ICONST_3, Opcodes.ICONST_4, Opcodes.ICONST_5 };


  protected final ClassGenerator classGenerator;
  protected final CodeUnit rootNode;
  protected final MethodVisitor methodWriter;

  CodeGenerator(ClassGenerator classGenerator, CodeUnit rootNode, MethodVisitor methodVisitor) {
    this.classGenerator = classGenerator;
    this.rootNode = rootNode;
    this.methodWriter = methodVisitor;
  }

  public ClassGenerator classGenerator() {
    return classGenerator;
  }

  public ClassWriter classWriter() {
    return classGenerator.classWriter();
  }

  public void generate() {
    methodWriter.visitCode();
    generateMethodPrologue();
    generateBody();
    methodWriter.visitMaxs(0, 0); // args ignored; writer is set to compute
                                  // these
    methodWriter.visitEnd();
  }

  @Override
  public void visitMethod(Method method) {
    throw new IllegalStateException("this node should not be visited directly");
  }

  public void visit(AstNode node) {
    node.accept(this);
  }

  /**
   * Subclasses will implement this as appropriate for their type. Methods and
   * blocks differ in their default return value.
   */
  protected abstract void generateBody();

  @Override
  public void visitBlock(Block block) {
    BlockDefiner definer = block.definer();
    int copiedValueCount = block.scope().asBlockScope().copiedVariableCount();
    // Generate a constructor call of Closure in either the positional argument or
    // varargs form, depending on the number of copied values.
    methodWriter.visitTypeInsn(Opcodes.NEW, Closure.INTERNAL_CLASS_NAME);
    methodWriter.visitInsn(Opcodes.DUP);
    // push implementation method handle
    methodWriter.visitFieldInsn(
        Opcodes.GETSTATIC,
        definer.internalClassName(),
        definer.fieldName(),
        Descriptor.ofType(MethodHandle.class));
    // push the current receiver
    methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
    // push all copied values
    if (copiedValueCount <= Closure.MAX_POSITIONAL_ARGC) {
      block.scope().asBlockScope().forEachCopiedVariable(each -> {
        LocalVariable here = rootNode.scope().localVariableNamed(each.name()).get(); // must be found or the analyzer is broken
        methodWriter.visitVarInsn(Opcodes.ALOAD, here.index());
      });
    } else {
      generateLoadInt(methodWriter, copiedValueCount);
      methodWriter.visitTypeInsn(Opcodes.ANEWARRAY, NsObject.INTERNAL_CLASS_NAME);
      int i = 0;
      for (LocalVariable copied : block.scope().asBlockScope().copiedVariables()) {
        methodWriter.visitInsn(Opcodes.DUP);
        generateLoadInt(methodWriter, i++);
        LocalVariable here = rootNode.scope().localVariableNamed(copied.name()).get(); // must be found or the analyzer is broken
        methodWriter.visitVarInsn(Opcodes.ALOAD, here.index());
        methodWriter.visitInsn(Opcodes.AASTORE);
      }
    }
    methodWriter.visitMethodInsn(
        Opcodes.INVOKESPECIAL,
        Closure.INTERNAL_CLASS_NAME,
        "<init>",
        Closure.constructorDescriptor(copiedValueCount),
        false);
  }


  @Override
  public void visitLiteralNumber(LiteralNumber literalNumber) {
    generateLoadInt(methodWriter, literalNumber.value().intValue());
    methodWriter.visitMethodInsn(
        Opcodes.INVOKESTATIC,
        Descriptor.internalClassName(Integer.class),
        "valueOf",
        Descriptor.ofMethod(Integer.class, int.class),
        false);
  }

  @Override
  public void visitLiteralString(LiteralString literalString) {
    String string = literalString.value();
    LiteralValue literal = classGenerator.lookupLiteral(string);
    if (literal == null) {
      literal = LiteralValue.forString(string);
      classGenerator.addLiteral(literal);
    }
    literal.generateLoad(methodWriter);
  }

  @Override
  public void visitMessagePattern(MessagePattern messagePattern) {
    messagePattern.arguments().forEach(this::visit);
  }

  /**
   * Generate code for a receiverless message send. Depending on the lexical
   * context previously examined by the analyzer, this send may be a variable
   * reference, a send to an enclosing object, or a self send.
   */
  @Override
  public void visitMessageSendNoReceiver(MessageSendNoReceiver messageNode) {
    if (messageNode.meaning().isLexicalVarReference()) {
      generateLexicalVarReference(messageNode);
    } else if (messageNode.meaning().isSendToEnclosingObject()) {
      generateSendToEnclosingObject(messageNode);
    } else if (messageNode.meaning().isSelfSend()) {
      generateSelfSend(messageNode);
    } else {
      throw new IllegalStateException("unrecognized here send meaning");
    }
  }

  /**
   * Generate code for access to a lexically visible variable available
   * as a local in the current method frame (which local may be
   * a closed over value copied from the outer scope). There are three
   * major cases: a getter, a regular setter (result is the receiver) and
   * a double-colon setter (result is the set value). Each of those
   * has the subcases of a regular and a boxed representation.
   */
  private void generateLexicalVarReference(MessageSendNoReceiver messageNode) {
    LexicalVarReference meaning = (LexicalVarReference) messageNode.meaning();
    LocalVariable local = meaning.localVariable(); 
    int index = local.index();
    // TODO we can do some peephole optimization here to avoid the effort
    // to keep the result on the stack if the send is a statement and the
    // result is going to be discarded anyway.
    if (NamingPolicy.isSetterSelector(messageNode.selector())) {
      assert messageNode.arity() == 1;
      if (messageNode.isSetterSend()) {
        visit(messageNode.arguments().get(0));
        methodWriter.visitInsn(Opcodes.DUP); // make a copy for the result
        if (local.isBoxed()) {
          // :: send, boxed
          methodWriter.visitVarInsn(Opcodes.ALOAD, index);
          methodWriter.visitTypeInsn(Opcodes.CHECKCAST, Box.INTERNAL_CLASS_NAME);
          methodWriter.visitInsn(Opcodes.SWAP);
          methodWriter.visitFieldInsn(Opcodes.PUTFIELD, Box.INTERNAL_CLASS_NAME, "value", NsObject.TYPE_DESCRIPTOR);
        } else {
          // :: send, unboxed
          methodWriter.visitVarInsn(Opcodes.ASTORE, index);
        }
        // a prior copy of expr is now on the stack
      } else {
        if (local.isBoxed()) {
          // : send, boxed
          methodWriter.visitVarInsn(Opcodes.ALOAD, index);
          methodWriter.visitTypeInsn(Opcodes.CHECKCAST, Box.INTERNAL_CLASS_NAME);
          visit(messageNode.arguments().get(0));
          methodWriter.visitFieldInsn(Opcodes.PUTFIELD, Box.INTERNAL_CLASS_NAME, "value", NsObject.TYPE_DESCRIPTOR);
        } else {
          // : send, unboxed
          visit(messageNode.arguments().get(0));
          methodWriter.visitVarInsn(Opcodes.ASTORE, index);
        }
        methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
      }
    } else {
      // A getter
      assert messageNode.arity() == 0;
      methodWriter.visitVarInsn(Opcodes.ALOAD, index);
      if (local.isBoxed()) {
        methodWriter.visitTypeInsn(Opcodes.CHECKCAST, Box.INTERNAL_CLASS_NAME);
        methodWriter.visitFieldInsn(Opcodes.GETFIELD, Box.INTERNAL_CLASS_NAME, "value", NsObject.TYPE_DESCRIPTOR);
      }
    }
  }

  private void generateSendToEnclosingObject(MessageSendNoReceiver messageNode) {
    SendToEnclosingObject meaning = (SendToEnclosingObject) messageNode.meaning();
    int scopeLevel = meaning.definition().scope().level();
    if (messageNode.isSetterSend()) {
      // A setter send should leave the message argument on the stack
      assert messageNode.arity() == 1;
      visit(messageNode.arguments().get(0));
      methodWriter.visitInsn(Opcodes.DUP);
      generateLoadOfEnclosingObject(scopeLevel);
      methodWriter.visitInsn(Opcodes.SWAP);
      // Now we have the argument copy above the receiver and the argument;
      // should also pop the send result once we are done.
    } else {
      generateLoadOfEnclosingObject(scopeLevel);
      messageNode.arguments().forEach(this::visit);
    }
    methodWriter.visitInvokeDynamicInsn(
        NamingPolicy.methodNameForSelector(messageNode.selector()),
        callSiteTypeDescriptor(messageNode.arity()),
        MessageDispatcher.bootstrapHandle());
    if (messageNode.isSetterSend()) {
      // Pop the answer; the copy of the argument is now exposed as the result.
      methodWriter.visitInsn(Opcodes.POP);
    }
  }

  private void generateLoadOfEnclosingObject(int level) {
    // get the enclosing receiver on the stack with the equivalent of:
    // this.nsClass.enclosingObjects[scopeLevel]
    // 'this' is a subclass of StandardClass, so no CHECKCAST is needed prior to
    // getting 'nsClass'.
    methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
    methodWriter.visitFieldInsn(
        Opcodes.GETFIELD,
        StandardObject.INTERNAL_CLASS_NAME,
        "nsClass",
        ObjectFactory.TYPE_DESCRIPTOR);
    methodWriter.visitFieldInsn(
        Opcodes.GETFIELD,
        ObjectFactory.INTERNAL_CLASS_NAME,
        "enclosingObjects",
        "[" + StandardObject.TYPE_DESCRIPTOR);
    generateLoadInt(methodWriter, level);
    methodWriter.visitInsn(Opcodes.AALOAD);
  }

  private void generateSelfSend(MessageSendNoReceiver messageSend) {
    if (messageSend.isSetterSend()) {
      // A setter send should leave the message argument on the stack
      assert messageSend.arguments().size() == 1;
      visit(messageSend.arguments().get(0));
      methodWriter.visitInsn(Opcodes.DUP);
      methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
      methodWriter.visitInsn(Opcodes.SWAP);
      // Now we have the argument copy above the receiver and the argument;
      // should also pop the send result once we are done.
    } else {
      methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
      messageSend.arguments().forEach(this::visit);
    }
    methodWriter.visitInvokeDynamicInsn(
        NamingPolicy.methodNameForSelector(messageSend.selector()),
        callSiteTypeDescriptor(messageSend.arguments().size()),
        MessageDispatcher.bootstrapHandle());
    if (messageSend.isSetterSend()) {
      // Pop the answer; the copy of the argument is now exposed as the result.
      methodWriter.visitInsn(Opcodes.POP);
    }
  }

  @Override
  public void visitMessageSendWithReceiver(MessageSendWithReceiver sendNode) {
    sendNode.receiver().accept(this);
    for (AstNode arg : sendNode.arguments()) {
      arg.accept(this);
    }
    methodWriter.visitInvokeDynamicInsn(
        NamingPolicy.methodNameForSelector(sendNode.selector()),
        callSiteTypeDescriptor(sendNode.arguments().size()),
        MessageDispatcher.bootstrapHandle());
  }

  @Override
  public void visitLiteralNil(LiteralNil literalNil) {
    methodWriter.visitInsn(Opcodes.ACONST_NULL);
  }

  @Override
  public void visitLiteralBoolean(LiteralBoolean literalBoolean) {
    methodWriter.visitFieldInsn(
        Opcodes.GETSTATIC,
        Builtins.INTERNAL_CLASS_NAME,
        literalBoolean.value() ? "TRUE" : "FALSE",
        NsObject.TYPE_DESCRIPTOR);
  }

  @Override
  public void visitSelf(Self self) {
    methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
  }

  @Override
  public void visitSuper(Super superNode) {
    unimplemented(superNode);
  }

  @Override
  public void visitOuter(Outer outer) {
    int scopeLevel = outer.targetClassScope().level();
    generateLoadOfEnclosingObject(scopeLevel);
  }

  @Override
  public void visitReturn(Return returnNode) {
    returnNode.expression().accept(this);
    methodWriter.visitInsn(Opcodes.ARETURN);
  }

  @Override
  public void visitClassDecl(ClassDecl classDecl) {
    unexpectedNode(classDecl);
  }

  @Override
  public void visitCategory(Category category) {
    unexpectedNode(category);
  }

  private String callSiteTypeDescriptor(int arity) {
    StringBuilder result = new StringBuilder();
    result.append("(");
    for (int i = 0; i < arity + 1; i++) {
      result.append(NsObject.TYPE_DESCRIPTOR);
    }
    result.append(")");
    result.append(NsObject.TYPE_DESCRIPTOR);
    return result.toString();
  }

  private void unexpectedNode(AstNode node) {
    throw new IllegalArgumentException("Unexpected node in a method AST: " + node);
  }

  private void unexpectedVisit(AstNode node) {
    throw new IllegalArgumentException(
        "This method AST node should not be visited directly: " + node);
  }

  private void unimplemented(AstNode node) {
    throw new UnsupportedOperationException("Code generation is not yet implemented for " + node);
  }

  @Override
  public void visitArgument(Argument argument) {
    unexpectedVisit(argument);
  }

  @Override
  public void visitSlotDefinition(SlotDefinition slotDefinition) {
    unexpectedVisit(slotDefinition);
  }

  /**
   * Put a NIL value into each temp. Put an array of size 1 containing the NIL value
   * into each temp which is supposed to be boxed.
   */
  protected void generateMethodPrologue() {
    rootNode.scope().forEachTemp(each -> {
      if (each.isBoxed()) {
        methodWriter.visitTypeInsn(Opcodes.NEW, Box.INTERNAL_CLASS_NAME);
        methodWriter.visitInsn(Opcodes.DUP);
        methodWriter.visitMethodInsn(
            Opcodes.INVOKESPECIAL,
            Box.INTERNAL_CLASS_NAME,
            "<init>",
            "()V",
            false);
        methodWriter.visitVarInsn(Opcodes.ASTORE, each.index());
      }
    });
  }
}
