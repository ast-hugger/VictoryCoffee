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
import org.newspeaklanguage.runtime.ReturnPrimitiveValue;
import org.newspeaklanguage.runtime.StandardObject;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

// TODO blocks are not fully implemented

// TODO super sends are not implemented

/**
 * The generic method code generator. Generates a Java method to represent either
 * an individual Newspeak method, or a block contained in one. Two subclasses
 * of this class handle the specifics of each of those tasks.
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
    if (-1 <= value && value <= 5) {
      methodWriter.visitInsn(Opcodes.ICONST_0 + value);
    } else if (-128 <= value && value <= 127) {
      methodWriter.visitIntInsn(Opcodes.BIPUSH, value);
    } else {
      methodWriter.visitIntInsn(Opcodes.SIPUSH, value);
    }
  }

  public static void generateLoadUndefined(MethodVisitor methodWriter) {
    methodWriter.visitFieldInsn(
        Opcodes.GETSTATIC,
        Descriptor.internalClassName(NsObject.class),
        "UNDEFINED",
        Descriptor.OBJECT_TYPE_DESCRIPTOR);
  }

  public static void generateCreateReturnPrimitiveValue(MethodVisitor methodWriter) {
    methodWriter.visitMethodInsn(
        Opcodes.INVOKESTATIC,
        ReturnPrimitiveValue.INTERNAL_CLASS_NAME,
        "create",
        ReturnPrimitiveValue.FACTORY_DESCRIPTOR,
        false
    );
  }

  /*
   * Instance side
   */
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
    methodWriter.visitMaxs(0, 0); // args ignored; the writer is set up to compute these
    methodWriter.visitEnd();
  }

  @Override
  public void visitMethod(Method method) {
    unexpectedVisit(method);
  }

  public void visit(AstNode node) {
    node.accept(this);
  }

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
      methodWriter.visitTypeInsn(Opcodes.ANEWARRAY, Descriptor.OBJECT_INTERNAL_CLASS_NAME);
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
    generateLoadUndefined(methodWriter);
    generateLoadInt(methodWriter, literalNumber.value().intValue());
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
    generateLoadInt(methodWriter, 0);
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
   * Generate code for access to a lexically visible variable. If the variable
   * is defined in this scope, it's available as a method frame slot.
   * It it's defined in an outer scope, it's still available as a method frame
   * slot as a copied down argument. If the original binding is mutable, the
   * copied down argument is a Box.
   * <p>
   * There are three cases of access:
   * a getter, a regular setter (the result is the receiver) and
   * a "setter setter" (a double-colon setter, the result is the set value).
   * Each of those has the subcases of a regular and a boxed representation.
   */
  private void generateLexicalVarReference(MessageSendNoReceiver messageNode) {
    LexicalVarReference meaning = (LexicalVarReference) messageNode.meaning();
    LocalVariable local = meaning.localVariable();
    int index = local.index();
    // TODO we can do some peephole optimization here to avoid the effort
    // to keep the result on the stack if the send is a statement and the
    // result is going to be discarded anyway.
    if (NamingPolicy.isSetterSelector(messageNode.selector())) {
      // A setter, either a regular or a setter setter
      assert messageNode.arity() == 1;
      if (messageNode.isSetterSend()) {
        // A setter setter (foo::)
        visit(messageNode.arguments().get(0));
        methodWriter.visitInsn(Opcodes.DUP2); // the result is an Object/int pair
        if (local.isBoxed()) {
          // foo:: boxed
          methodWriter.visitVarInsn(Opcodes.ALOAD, index); // stack: Object, int, Box
          methodWriter.visitTypeInsn(Opcodes.CHECKCAST, Box.INTERNAL_CLASS_NAME);
          methodWriter.visitInsn(Opcodes.DUP_X2); // stack: Box, Object, int, Box
          methodWriter.visitInsn(Opcodes.SWAP); // stack: Box, Object, Box, int
          methodWriter.visitFieldInsn(Opcodes.PUTFIELD, Box.INTERNAL_CLASS_NAME, "intValue", Descriptor.INT_TYPE_DESCRIPTOR);
          methodWriter.visitFieldInsn(Opcodes.PUTFIELD, Box.INTERNAL_CLASS_NAME, "value", Descriptor.OBJECT_TYPE_DESCRIPTOR);
        } else {
          // foo: unboxed
          methodWriter.visitVarInsn(Opcodes.ISTORE, index + 1); // store the int
          methodWriter.visitVarInsn(Opcodes.ASTORE, index); // store the object part
        }
        // the original value pair is now on top
      } else {
        // foo:
        if (local.isBoxed()) {
          // foo: boxed
          visit(messageNode.arguments().get(0)); // stack: Object, Int
          methodWriter.visitVarInsn(Opcodes.ALOAD, index);
          methodWriter.visitTypeInsn(Opcodes.CHECKCAST, Box.INTERNAL_CLASS_NAME); // stack: Object, Int, Box
          methodWriter.visitInsn(Opcodes.DUP_X2); // stack: Box, Object, Int, Box
          methodWriter.visitInsn(Opcodes.SWAP); // stack: Box, Object, Box, Int
          methodWriter.visitFieldInsn(Opcodes.PUTFIELD, Box.INTERNAL_CLASS_NAME, "intValue", Descriptor.INT_TYPE_DESCRIPTOR);
          methodWriter.visitFieldInsn(Opcodes.PUTFIELD, Box.INTERNAL_CLASS_NAME, "value", Descriptor.OBJECT_TYPE_DESCRIPTOR);
        } else {
          // foo: unboxed
          visit(messageNode.arguments().get(0));
          methodWriter.visitVarInsn(Opcodes.ISTORE, index + 1);
          methodWriter.visitVarInsn(Opcodes.ASTORE, index);
        }
        methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
        methodWriter.visitInsn(Opcodes.ICONST_0);
      }
    } else {
      // A getter
      assert messageNode.arity() == 0;
      if (local.isBoxed()) {
        methodWriter.visitVarInsn(Opcodes.ALOAD, index);
        methodWriter.visitTypeInsn(Opcodes.CHECKCAST, Box.INTERNAL_CLASS_NAME);
        methodWriter.visitInsn(Opcodes.DUP); // stack: Box, Box
        methodWriter.visitFieldInsn(Opcodes.GETFIELD, Box.INTERNAL_CLASS_NAME, "value", Descriptor.OBJECT_TYPE_DESCRIPTOR);
        methodWriter.visitInsn(Opcodes.SWAP); // stack: Object, Box
        methodWriter.visitFieldInsn(Opcodes.GETFIELD, Box.INTERNAL_CLASS_NAME, "intValue", Descriptor.INT_TYPE_DESCRIPTOR);
      } else {
        methodWriter.visitVarInsn(Opcodes.ALOAD, index);
        methodWriter.visitVarInsn(Opcodes.ILOAD, index + 1);
      }
    }
  }

  private void generateSendToEnclosingObject(MessageSendNoReceiver messageNode) {
    SendToEnclosingObject meaning = (SendToEnclosingObject) messageNode.meaning();
    int scopeLevel = meaning.definition().scope().level();
    boolean isSetterSend = messageNode.isSetterSend();

    if (isSetterSend) {
      // A setter send should leave the message argument on the stack
      assert messageNode.arity() == 1;
      visit(messageNode.arguments().get(0)); // stack: Object, int
      methodWriter.visitInsn(Opcodes.DUP2); // stack: Object, int, Object, int
      generateLoadOfEnclosingObject(scopeLevel); // stack: Object, int, Object, int, receiver
      methodWriter.visitInsn(Opcodes.DUP_X2); // stack: Object, int, receiver, Object, int, receiver
      methodWriter.visitInsn(Opcodes.POP); // stack: Object, int, receiver, Object, int
      // In the following line we use a random int we have on the stack as the int value of the receiver pair
      // because it's there and we don't care about the value, we just need an int there to conform to the
      // standard method signature. The receiver is always a real object, so the
      // int part of the receiver value pair is never used.
      methodWriter.visitInsn(Opcodes.DUP_X1); // stack: Object, int, receiver, int, Object, int
      // We now have a copy of the argument pair above the receiver and the argument pair;
      // should also pop the send result once we are done.
    } else {
      generateLoadOfEnclosingObject(scopeLevel);
      messageNode.arguments().forEach(this::visit);
    }
    // do the send
    Label start = new Label();
    Label objectResult = new Label();
    Label handler = new Label();
    methodWriter.visitTryCatchBlock(start, objectResult, handler, ReturnPrimitiveValue.INTERNAL_CLASS_NAME);
// start:
    methodWriter.visitLabel(start);
    methodWriter.visitInvokeDynamicInsn(
        NamingPolicy.methodNameForSelector(messageNode.selector()),
        callSiteTypeDescriptor(messageNode.arity()),
        MessageDispatcher.bootstrapHandle());
// objectResult:
    methodWriter.visitLabel(objectResult); // a real object is on the stack; add a bogus int
    if (!isSetterSend) {
      generateLoadInt(methodWriter, 0);
    } // if a setter send we don't care, the stack is popped anyway to reveal the answer pair
    Label end = new Label();
    methodWriter.visitJumpInsn(Opcodes.GOTO, end);
// handler:
    methodWriter.visitLabel(handler);
    // if not a setter send, produce a proper value pair from the int in the exception
    // if a setter send, we don't care. The stack will be popped anyway
    if (!isSetterSend) {
      methodWriter.visitFieldInsn(
          Opcodes.GETFIELD,
          ReturnPrimitiveValue.INTERNAL_CLASS_NAME,
          "value",
          Descriptor.INT_TYPE_DESCRIPTOR);
      generateLoadUndefined(methodWriter); // stack: int, Object
      methodWriter.visitInsn(Opcodes.SWAP);
    }
// end:
    methodWriter.visitLabel(end);
    if (isSetterSend) {
      // Pop the answer; the copy of the argument pair is now exposed as the result.
      methodWriter.visitInsn(Opcodes.POP);
    }
  }

  /**
   * Get the enclosing receiver on the stack with the equivalent of:
   * {@code this.nsClass.enclosingObjects[scopeLevel]}
   * {@code this} is an instance of a StandardClass subclass, so no
   * CHECKCAST is needed prior to getting its nsClass.
   * <p>
   *   Note: this only loads the enclosing object, not a full object/int value pair.
   */
  private void generateLoadOfEnclosingObject(int level) {
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
        StandardObject.ARRAY_TYPE_DESCRIPTOR);
    generateLoadInt(methodWriter, level);
    methodWriter.visitInsn(Opcodes.AALOAD);
  }

  private void generateSelfSend(MessageSendNoReceiver messageSend) {
    boolean isSetterSend = messageSend.isSetterSend();
    if (isSetterSend) {
      // A setter send should leave the message argument pair on the stack
      assert messageSend.arguments().size() == 1;
      visit(messageSend.arguments().get(0)); // stack: Object, int
      methodWriter.visitInsn(Opcodes.DUP2);
      methodWriter.visitVarInsn(Opcodes.ALOAD, 0); // stack: Object, int, Object, int, receiver
      methodWriter.visitInsn(Opcodes.DUP_X2); // stack: Object, int, receiver, Object, int, receiver
      methodWriter.visitInsn(Opcodes.POP); // stack: Object, int, receiver, Object, int
      // See the comment in #generateSendToEnclosingObject() about this duplication of the int
      // on the stack as the int value of the receiver.
      methodWriter.visitInsn(Opcodes.DUP_X1); // stack: Object, int, receiver, int, Object, int
      // Now we have the argument pair copy above the receiver and the argument pair;
      // should also pop the send result out of the way once we are done.
    } else {
      methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
      generateLoadInt(methodWriter, 0);
      messageSend.arguments().forEach(this::visit);
    }
    // do the send
    Label start = new Label();
    Label objectResult = new Label();
    Label handler = new Label();
    methodWriter.visitTryCatchBlock(start, objectResult, handler, ReturnPrimitiveValue.INTERNAL_CLASS_NAME);
// start:
    methodWriter.visitLabel(start);
    methodWriter.visitInvokeDynamicInsn(
        NamingPolicy.methodNameForSelector(messageSend.selector()),
        callSiteTypeDescriptor(messageSend.arguments().size()),
        MessageDispatcher.bootstrapHandle());
// objectResult:
    methodWriter.visitLabel(objectResult); // object is on the stack; add a bogus int
    if (!isSetterSend) {
      generateLoadInt(methodWriter, 0);
    }
    Label end = new Label();
    methodWriter.visitJumpInsn(Opcodes.GOTO, end);
// handler:
    methodWriter.visitLabel(handler);
    // if not a setter send, produce a proper value pair from the int in the exception
    // if a setter send, we don't care. The stack will be popped anyway
    if (!isSetterSend) {
      methodWriter.visitFieldInsn(
          Opcodes.GETFIELD,
          ReturnPrimitiveValue.INTERNAL_CLASS_NAME,
          "value",
          Descriptor.INT_TYPE_DESCRIPTOR);
      generateLoadUndefined(methodWriter); // stack: int, Object
      methodWriter.visitInsn(Opcodes.SWAP);
    }
// end:
    methodWriter.visitLabel(end);
    if (isSetterSend) {
      // Pop the answer; the copy of the argument pair is now exposed as the result.
      methodWriter.visitInsn(Opcodes.POP);
    }
  }

  /**
   * Generate a traditional Smalltalk-style message send to an explicit receiver.
   * As everywhere, the receiver and each argument are represented by two stack entries.
   * The answer is returned normally if an Object, or as an exception if an int.
   * We have to adapt the outcome back into an Object/int pair on the stack.
   */
  @Override
  public void visitMessageSendWithReceiver(MessageSendWithReceiver sendNode) {
    sendNode.receiver().accept(this);
    for (AstNode arg : sendNode.arguments()) {
      arg.accept(this);
    }
    Label start = new Label();
    Label objectResult = new Label();
    Label handler = new Label();
    methodWriter.visitTryCatchBlock(start, objectResult, handler, ReturnPrimitiveValue.INTERNAL_CLASS_NAME);
    methodWriter.visitLabel(start);
    methodWriter.visitInvokeDynamicInsn(
        NamingPolicy.methodNameForSelector(sendNode.selector()),
        callSiteTypeDescriptor(sendNode.arguments().size()),
        MessageDispatcher.bootstrapHandle());
// objectResult:
    methodWriter.visitLabel(objectResult); // object is on the stack; add a bogus int
    generateLoadInt(methodWriter, 0);
    Label end = new Label();
    methodWriter.visitJumpInsn(Opcodes.GOTO, end);
// handler:
    methodWriter.visitLabel(handler); // exception with the int result is on the stack
    methodWriter.visitFieldInsn(
        Opcodes.GETFIELD,
        ReturnPrimitiveValue.INTERNAL_CLASS_NAME,
        "value",
        Descriptor.INT_TYPE_DESCRIPTOR);
    generateLoadUndefined(methodWriter); // stack: int, Object
    methodWriter.visitInsn(Opcodes.SWAP);
// end:
    methodWriter.visitLabel(end);
  }

  @Override
  public void visitLiteralNil(LiteralNil literalNil) {
    methodWriter.visitInsn(Opcodes.ACONST_NULL);
    generateLoadInt(methodWriter, 0);
  }

  @Override
  public void visitLiteralBoolean(LiteralBoolean literalBoolean) {
    methodWriter.visitFieldInsn(
        Opcodes.GETSTATIC,
        Builtins.INTERNAL_CLASS_NAME,
        literalBoolean.value() ? "TRUE" : "FALSE",
        Descriptor.OBJECT_TYPE_DESCRIPTOR);
    generateLoadInt(methodWriter, 0);
  }

  @Override
  public void visitSelf(Self self) {
    methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
    generateLoadInt(methodWriter, 0);
  }

  @Override
  public void visitSuper(Super superNode) {
    unimplemented(superNode);
  }

  @Override
  public void visitOuter(Outer outer) {
    int scopeLevel = outer.targetClassScope().level();
    generateLoadOfEnclosingObject(scopeLevel);
    generateLoadInt(methodWriter, 0);
  }

  @Override
  public void visitReturn(Return returnNode) {
    returnNode.expression().accept(this); // stack: Object, int
    methodWriter.visitInsn(Opcodes.SWAP);
    methodWriter.visitInsn(Opcodes.DUP);
    generateLoadUndefined(methodWriter); // stack: int, Object, Object, Undefined
    Label objectPresent = new Label();
    methodWriter.visitJumpInsn(Opcodes.IF_ACMPNE, objectPresent); // stack: int, Object
    // Object undefined, int is the return value
    methodWriter.visitInsn(Opcodes.POP); // stack: int
    generateCreateReturnPrimitiveValue(methodWriter);
    methodWriter.visitInsn(Opcodes.ATHROW);
// objectPresent:
    methodWriter.visitLabel(objectPresent);
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

  /**
   * Return a descriptor string for a call site of a message send of the given arity.
   * The arity we understand as the number of method arguments in Newspeak sense, not
   * counting the receiver. The call site descriptor string should also include the
   * Object/int pair representing the receiver.
   */
  private String callSiteTypeDescriptor(int arity) {
    StringBuilder result = new StringBuilder();
    result.append("(");
    for (int i = 0; i < arity + 1; i++) {
      result
          .append(Descriptor.OBJECT_TYPE_DESCRIPTOR)
          .append(Descriptor.INT_TYPE_DESCRIPTOR);
    }
    result.append(")");
    result.append(Descriptor.OBJECT_TYPE_DESCRIPTOR);
    return result.toString();
  }

  @Override
  public void visitArgument(Argument argument) {
    unexpectedVisit(argument);
  }

  @Override
  public void visitSlotDefinition(SlotDefinition slotDefinition) {
    unexpectedVisit(slotDefinition);
  }

  private void unexpectedNode(AstNode node) {
    throw new IllegalArgumentException("Unexpected node in a method AST: " + node);
  }

  private void unexpectedVisit(AstNode node) {
    throw new IllegalArgumentException("Unexpected direct visit of node: " + node);
  }

  private void unimplemented(AstNode node) {
    throw new UnsupportedOperationException("Code generation is not yet implemented for " + node);
  }
}
