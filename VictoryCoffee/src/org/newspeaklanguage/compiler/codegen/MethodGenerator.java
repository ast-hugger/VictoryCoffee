package org.newspeaklanguage.compiler.codegen;

import java.util.List;

import org.newspeaklanguage.compiler.ast.Argument;
import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.AstNodeVisitor;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.Category;
import org.newspeaklanguage.compiler.ast.ClassDecl;
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
import org.newspeaklanguage.compiler.semantics.MethodScopeEntry;
import org.newspeaklanguage.compiler.semantics.NameMeaning;
import org.newspeaklanguage.runtime.Builtins;
import org.newspeaklanguage.runtime.MessageDispatcher;
import org.newspeaklanguage.runtime.Object;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodGenerator implements AstNodeVisitor {
  
  private final Method methodNode;
  private final MethodVisitor methodVisitor;
  
  MethodGenerator(Method methodNode, MethodVisitor methodVisitor) {
    this.methodNode = methodNode;
    this.methodVisitor = methodVisitor;
  }
  
  public void generate() {
    methodVisitor.visitCode();
    methodNode.accept(this);
    methodVisitor.visitMaxs(0, 0); // args ignored; writer is set to compute these 
    methodVisitor.visitEnd();
  }

  @Override
  public void visitMethod(Method method) {
//    method.messagePattern().accept(this);
//    method.temps().stream().forEach(each -> each.accept(this));
    List<AstNode> body = method.body();
    body.stream().forEach(each -> each.accept(this));
    if (body.isEmpty()) {
      // Empty method or no explicit return: return self
      methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
      methodVisitor.visitInsn(Opcodes.ARETURN);
    } else if (!(body.get(body.size() - 1) instanceof Return)) {
      // Empty method or no explicit return: return self
      methodVisitor.visitInsn(Opcodes.POP);
      methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
      methodVisitor.visitInsn(Opcodes.ARETURN);
    }
  }

  @Override
  public void visitBlock(Block block) {
    unimplemented(block);
  }

  @Override
  public void visitLiteralNumber(LiteralNumber literalNumber) {
    unimplemented(literalNumber);
  }

  @Override
  public void visitLiteralString(LiteralString literalString) {
    // TODO the literal is implemented as instantiation on the spot.
    // It would be better to implement is as a static class field, returned
    // by the use site.
    methodVisitor.visitTypeInsn(Opcodes.NEW, Builtins.StringObject.INTERNAL_CLASS_NAME);
    methodVisitor.visitInsn(Opcodes.DUP);
    methodVisitor.visitLdcInsn(literalString.value());
    methodVisitor.visitMethodInsn(
        Opcodes.INVOKESPECIAL,
        Builtins.StringObject.INTERNAL_CLASS_NAME,
        "<init>",
        "(Ljava/lang/String;)V",
        false);
  }

  @Override
  public void visitMessagePattern(MessagePattern messagePattern) {
    messagePattern.arguments().stream().forEach(each -> each.accept(this));
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
    NameMeaning.LocalVarReference meaning = (NameMeaning.LocalVarReference) messageSend.meaning();
    int index = ((MethodScopeEntry) meaning.definition()).index();
    methodVisitor.visitVarInsn(Opcodes.ALOAD, index);
  }

  private void generateSendToEnclosingObject(MessageSendNoReceiver messageSend) {
    unimplemented(messageSend);
  }
  
  private void generateSelfSend(MessageSendNoReceiver messageSend) {
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
    for (AstNode arg : messageSend.arguments()) {
      arg.accept(this);
    }
    methodVisitor.visitInvokeDynamicInsn(
        messageSend.selector(),
        callSiteTypeDescriptor(messageSend.arguments().size()),
        MessageDispatcher.bootstrapHandle());
  }

  @Override
  public void visitMessageSendWithReceiver(MessageSendWithReceiver sendNode) {
    sendNode.receiver().accept(this);
    for (AstNode arg : sendNode.arguments()) {
      arg.accept(this);
    }
    methodVisitor.visitInvokeDynamicInsn(
        sendNode.selector(),
        callSiteTypeDescriptor(sendNode.arguments().size()),
        MessageDispatcher.bootstrapHandle());
  }

  @Override
  public void visitLiteralNil(LiteralNil literalNil) {
    methodVisitor.visitFieldInsn(
        Opcodes.GETSTATIC, 
        Builtins.INTERNAL_CLASS_NAME, 
        "NIL", 
        org.newspeaklanguage.runtime.Object.TYPE_DESCRIPTOR);
  }

  @Override
  public void visitLiteralBoolean(LiteralBoolean literalBoolean) {
    methodVisitor.visitFieldInsn(
        Opcodes.GETSTATIC, 
        Builtins.INTERNAL_CLASS_NAME, 
        literalBoolean.value() ? "TRUE" : "FALSE", 
        org.newspeaklanguage.runtime.Object.TYPE_DESCRIPTOR);
  }

  @Override
  public void visitSelf(Self self) {
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
  }

  @Override
  public void visitSuper(Super superNode) {
    unimplemented(superNode);
  }

  @Override
  public void visitOuter(Outer outer) {
    unimplemented(outer); 
  }
  
  @Override
  public void visitReturn(Return returnNode) {
    returnNode.expression().accept(this);
    methodVisitor.visitInsn(Opcodes.ARETURN);
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

  @Override
  public void visitArgument(Argument argument) {
    unexpectedVisit(argument);
  }

  @Override
  public void visitSlotDefinition(SlotDefinition slotDefinition) {
    unexpectedVisit(slotDefinition);
  }

}
