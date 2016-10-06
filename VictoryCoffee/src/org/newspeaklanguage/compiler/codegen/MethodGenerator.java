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
import org.newspeaklanguage.runtime.ObjectFactory;
import org.newspeaklanguage.runtime.StandardObject;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

// TODO I think we are leaving each method statement result on the stack,
// so look into popping it, except after a last expression of a block.

public class MethodGenerator implements AstNodeVisitor {
  
  private final ClassGenerator classGenerator;
  private final Method methodNode;
  private final MethodVisitor methodVisitor;
  
  MethodGenerator(ClassGenerator classGenerator, Method methodNode, MethodVisitor methodVisitor) {
    this.classGenerator = classGenerator;
    this.methodNode = methodNode;
    this.methodVisitor = methodVisitor;
  }
  
  public void generate() {
    methodVisitor.visitCode();
    methodNode.accept(this);
    methodVisitor.visitMaxs(0, 0); // args ignored; writer is set to compute these 
    methodVisitor.visitEnd();
  }

  public void visit(AstNode node) {
    node.accept(this);
  }
  
  @Override
  public void visitMethod(Method method) {
    List<AstNode> body = method.body();
    body.forEach(this::visit);
    if (body.isEmpty()) {
      // Empty method or no explicit return: return self
      methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
      methodVisitor.visitInsn(Opcodes.ARETURN);
    } else if (!(body.get(body.size() - 1) instanceof Return)) {
      // Non-empty method and last expression is not an explicit return: return self
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
    String string = literalString.value();
    LiteralValue literal = classGenerator.lookupLiteral(string);
    if (literal == null) {
      literal = LiteralValue.forString(string);
      classGenerator.addLiteral(literal);
    }
    literal.generateLoad(methodVisitor);
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
    NameMeaning.LocalVarReference meaning = (NameMeaning.LocalVarReference) messageSend.meaning();
    int index = ((MethodScopeEntry) meaning.definition()).index();
    methodVisitor.visitVarInsn(Opcodes.ALOAD, index);
  }

  private void generateSendToEnclosingObject(MessageSendNoReceiver messageSend) {
    NameMeaning.SendToEnclosingObject meaning = (NameMeaning.SendToEnclosingObject) messageSend.meaning();
    int scopeLevel = meaning.targetDefinition().definitionScope().level();
    // get the enclosing receiver on the stack with the equivalent of:
    // this.nsClass.enclosingObjects[scopeLevel]
    // 'this' is a subclass of StandardClass, so no CHECKCAST is needed prior to getting 'nsClass'.
    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
    methodVisitor.visitFieldInsn(
        Opcodes.GETFIELD, 
        StandardObject.INTERNAL_CLASS_NAME,
        "nsClass",
        ObjectFactory.TYPE_DESCRIPTOR);
    methodVisitor.visitFieldInsn(
        Opcodes.GETFIELD, 
        ObjectFactory.INTERNAL_CLASS_NAME, 
        "enclosingObjects", 
        "[" + StandardObject.TYPE_DESCRIPTOR);
    generateLoadInt(scopeLevel);
    methodVisitor.visitInsn(Opcodes.AALOAD);
    // now emit the usual message send stuff
    messageSend.arguments().forEach(this::visit);
    methodVisitor.visitInvokeDynamicInsn(
        messageSend.selector(),
        callSiteTypeDescriptor(messageSend.arity()),
        MessageDispatcher.bootstrapHandle());  
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

  private static final int[] specialOpcodes = new int[]
      {Opcodes.ICONST_0, Opcodes.ICONST_1, Opcodes.ICONST_2, Opcodes.ICONST_3, Opcodes.ICONST_4, Opcodes.ICONST_5};
 
  public void generateLoadInt(int value) {
    if (0 <= value && value <= 5) {
      methodVisitor.visitInsn(specialOpcodes[value]);
    } else if (-128 <= value && value <= 127) {
      methodVisitor.visitIntInsn(Opcodes.BIPUSH, value);
    } else {
      methodVisitor.visitIntInsn(Opcodes.SIPUSH, value);
    }
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
