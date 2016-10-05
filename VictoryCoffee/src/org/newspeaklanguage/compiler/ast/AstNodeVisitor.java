package org.newspeaklanguage.compiler.ast;

public interface AstNodeVisitor {
  
  public void visitArgument(Argument argument);

  public void visitBlock(Block block);

  public void visitCategory(Category category);

  public void visitClassDecl(ClassDecl classDecl);

  public void visitLiteralNumber(LiteralNumber literalNumber);

  public void visitLiteralString(LiteralString literalString);

  public void visitMessagePattern(MessagePattern messagePattern);

  public void visitMessageSendNoReceiver(MessageSendNoReceiver messageSendNoReceiver);

  public void visitMessageSendWithReceiver(MessageSendWithReceiver messageSendWithReceiver);

  public void visitMethod(Method method);

  public void visitSlotDefinition(SlotDefinition slotDefinition);

  public void visitLiteralNil(LiteralNil literalNil);

  public void visitLiteralBoolean(LiteralBoolean literalBoolean);

  public void visitSelf(Self self);

  public void visitSuper(Super super1);

  public void visitOuter(Outer outer);

  public void visitReturn(Return return1);

}
