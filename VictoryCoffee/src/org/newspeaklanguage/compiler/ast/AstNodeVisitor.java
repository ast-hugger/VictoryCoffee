package org.newspeaklanguage.compiler.ast;

public interface AstNodeVisitor {
  
  public void visit(AstNode node);

  public void visitArgument(Argument argument);

  public void visitBlock(Block block);

  public void visitCategory(Category category);

  public void visitClassDecl(ClassDecl classDecl);

  public void visitLiteralNumber(LiteralNumber literalNumber);

  public void visitLiteralString(AstNodeVisitor visitor);

  public void visitMessagePattern(MessagePattern messagePattern);

  public void visitMessageSendNoReceiver(MessageSendNoReceiver messageSendNoReceiver);

  public void visitMessageSendWithReceiver(MessageSendWithReceiver messageSendWithReceiver);

  public void visitMethod(Method method);

  public void visitSlotDefinition(SlotDefinition slotDefinition);

}
