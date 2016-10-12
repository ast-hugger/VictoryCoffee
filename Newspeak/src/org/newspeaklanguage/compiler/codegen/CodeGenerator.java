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
import org.newspeaklanguage.compiler.semantics.CodeScopeEntry;
import org.newspeaklanguage.compiler.semantics.LexicalVarReference;
import org.newspeaklanguage.compiler.semantics.SendToEnclosingObject;
import org.newspeaklanguage.runtime.Builtins;
import org.newspeaklanguage.runtime.MessageDispatcher;
import org.newspeaklanguage.runtime.Object;
import org.newspeaklanguage.runtime.ObjectFactory;
import org.newspeaklanguage.runtime.StandardObject;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

// TODO blocks are not fully implemented

// TODO super sends are not implemented

/**
 * The generic method code generator. We generate Java methods to represent both
 * the actual methods in Newspeak, and blocks contained in them. Those two tasks
 * are handled by the two subclasses of this class.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
abstract class CodeGenerator implements AstNodeVisitor {
  
  public static void generateLoadInt(MethodVisitor methodWriter, int value) {
    if (0 <= value && value <= 5) {
      methodWriter.visitInsn(specialOpcodes[value]);
    } else if (-128 <= value && value <= 127) {
      methodWriter.visitIntInsn(Opcodes.BIPUSH, value);
    } else {
      methodWriter.visitIntInsn(Opcodes.SIPUSH, value);
    }
  }
  
  protected final ClassGenerator classGenerator;
  protected final CodeUnit rootNode;
  protected final MethodVisitor methodWriter;
  
  CodeGenerator(ClassGenerator classGenerator, CodeUnit rootNode, MethodVisitor methodVisitor) {
    this.classGenerator = classGenerator;
    this.rootNode = rootNode;
    this.methodWriter = methodVisitor;
  }
  
  public ClassGenerator classGenerator() { return classGenerator; }
  public ClassWriter classWriter() { return classGenerator.classWriter(); }
  
  public void generate() {
    methodWriter.visitCode();
    visitStatements();
    methodWriter.visitMaxs(0, 0); // args ignored; writer is set to compute these 
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
   * Subclasses will implement this as appropriate for their type. Methods
   * and blocks differ in their default return value.
   */
  protected abstract void visitStatements();
  
  @Override
  public void visitBlock(Block block) {
    BlockDefiner definer = block.definer();
    // new Builtins.Closure
    methodWriter.visitTypeInsn(Opcodes.NEW, Builtins.Closure.INTERNAL_CLASS_NAME);
    methodWriter.visitInsn(Opcodes.DUP);
    // Builtins.Closure.<init>(ClosureLiteral closureLiteral, StandardObject self)
    methodWriter.visitFieldInsn(
        Opcodes.GETSTATIC,
        definer.internalClassName(),
        definer.fieldName(),
        Descriptor.ofType(MethodHandle.class));
    methodWriter.visitVarInsn(Opcodes.ALOAD, 0);
    methodWriter.visitMethodInsn(
        Opcodes.INVOKESPECIAL, 
        Builtins.Closure.INTERNAL_CLASS_NAME, 
        "<init>",
        Builtins.Closure.CONSTRUCTOR_DESCRIPTOR, 
        false);
  }

  @Override
  public void visitLiteralNumber(LiteralNumber literalNumber) {
    unimplemented(literalNumber);
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

  @Override
  public void visitMessageSendNoReceiver(MessageSendNoReceiver messageSend) {
    if (messageSend.meaning().isLocalVarReference()) {
      generateLocalVarReference(messageSend);
    } else if (messageSend.meaning().isSendToEnclosingObject()) {
      generateSendToEnclosingObject(messageSend);
    } else if (messageSend.meaning().isSelfSend()) {
      generateSelfSend(messageSend);
    } else {
      throw new IllegalArgumentException("unrecognized here send meaning");
    }
  }

  private void generateLocalVarReference(MessageSendNoReceiver messageSend) {
    LexicalVarReference meaning = (LexicalVarReference) messageSend.meaning();
    int index = ((CodeScopeEntry) meaning.definition()).index();
    if (NamingPolicy.isSetterSelector(messageSend.selector())) {
      assert messageSend.arity() == 1;
      visit(messageSend.arguments().get(0));
      // TODO we can do some peephole optimization here to avoid the moves
      // to have the result on the stack if the send is a statement and the
      // result is going to be discarded anyway.
      if (messageSend.isSetterSend()) {
        methodWriter.visitInsn(Opcodes.DUP); // the copy will be the result
      }
      methodWriter.visitVarInsn(Opcodes.ASTORE, index);
      if (!messageSend.isSetterSend()) {
        methodWriter.visitVarInsn(Opcodes.ALOAD, 0); // the result is the receiver
      }
    } else {
      assert messageSend.arity() == 0;
      methodWriter.visitVarInsn(Opcodes.ALOAD, index);
    }
  }

  private void generateSendToEnclosingObject(MessageSendNoReceiver messageSend) {
    SendToEnclosingObject meaning = (SendToEnclosingObject) messageSend.meaning();
    int scopeLevel = meaning.definition().scope().level();
    if (messageSend.isSetterSend()) {
      // A setter send should leave the message argument on the stack
      assert messageSend.arity() == 1;
      visit(messageSend.arguments().get(0));
      methodWriter.visitInsn(Opcodes.DUP);
      generateLoadOfEnclosingObject(scopeLevel);
      methodWriter.visitInsn(Opcodes.SWAP);
      // Now we have the argument copy above the receiver and the argument;
      // should also pop the send result once we are done.
    } else {
      generateLoadOfEnclosingObject(scopeLevel);
      messageSend.arguments().forEach(this::visit);
    }
    methodWriter.visitInvokeDynamicInsn(
        messageSend.selector(),
        callSiteTypeDescriptor(messageSend.arity()),
        MessageDispatcher.bootstrapHandle());
    if (messageSend.isSetterSend()) {
      // Pop the answer; the copy of the argument is now exposed as the result. 
      methodWriter.visitInsn(Opcodes.POP);
    }
  }
  
  private void generateLoadOfEnclosingObject(int level) {
    // get the enclosing receiver on the stack with the equivalent of:
    // this.nsClass.enclosingObjects[scopeLevel]
    // 'this' is a subclass of StandardClass, so no CHECKCAST is needed prior to getting 'nsClass'.
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
        messageSend.selector(),
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
        sendNode.selector(),
        callSiteTypeDescriptor(sendNode.arguments().size()),
        MessageDispatcher.bootstrapHandle());
  }

  @Override
  public void visitLiteralNil(LiteralNil literalNil) {
    methodWriter.visitFieldInsn(
        Opcodes.GETSTATIC, 
        Builtins.INTERNAL_CLASS_NAME, 
        "NIL", 
        org.newspeaklanguage.runtime.Object.TYPE_DESCRIPTOR);
  }

  @Override
  public void visitLiteralBoolean(LiteralBoolean literalBoolean) {
    methodWriter.visitFieldInsn(
        Opcodes.GETSTATIC, 
        Builtins.INTERNAL_CLASS_NAME, 
        literalBoolean.value() ? "TRUE" : "FALSE", 
        org.newspeaklanguage.runtime.Object.TYPE_DESCRIPTOR);
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
      result.append(Object.TYPE_DESCRIPTOR);
    }
    result.append(")");
    result.append(Object.TYPE_DESCRIPTOR);
    return result.toString();
  }
  
  private void unexpectedNode(AstNode node) {
    throw new IllegalArgumentException("Unexpected node in a method AST: " + node);
  }

  private void unexpectedVisit(AstNode node) {
    throw new IllegalArgumentException("This method AST node should not be visited directly: " + node);
  }

  private void unimplemented(AstNode node) {
    throw new UnsupportedOperationException("Code generation is not yet implemented for " + node);
  }

  private static final int[] specialOpcodes = new int[]
      {Opcodes.ICONST_0, Opcodes.ICONST_1, Opcodes.ICONST_2, Opcodes.ICONST_3, Opcodes.ICONST_4, Opcodes.ICONST_5};
 
  @Override
  public void visitArgument(Argument argument) {
    unexpectedVisit(argument);
  }

  @Override
  public void visitSlotDefinition(SlotDefinition slotDefinition) {
    unexpectedVisit(slotDefinition);
  }

}
