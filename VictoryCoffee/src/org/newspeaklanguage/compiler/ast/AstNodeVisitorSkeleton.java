package org.newspeaklanguage.compiler.ast;

import java.util.List;

/**
 * A basic implementation of the AstNodeVisitor interface, which visits each
 * node in the tree while doing nothing. A concrete visitor based on this only
 * needs to override the methods reacting to what it cares about.
 * 
 * @author vassili
 *
 */
public abstract class AstNodeVisitorSkeleton implements AstNodeVisitor {

  @Override
  public void visit(AstNode node) {
    if (node != null) {
      node.accept(this);
    }
  }
  
  protected void visit(List<? extends AstNode> list) {
    if (list != null ) {
     list.stream().forEach(this::visit);
    }
  }
  
  @Override
  public void visitArgument(Argument argument) {
  }

  @Override
  public void visitBlock(Block block) {
    visit(block.arguments());
    visit(block.temps());
    visit(block.body());
  }

  @Override
  public void visitCategory(Category category) {
    visit(category.methods());
  }

  @Override
  public void visitClassDecl(ClassDecl classDecl) {
    visit(classDecl.initMessage());
    visit(classDecl.superInitMessage());
    visit(classDecl.slots());
    visit(classDecl.nestedClasses());
    visit(classDecl.categories());
    visit(classDecl.classCategories());
  }

  @Override
  public void visitLiteralNumber(LiteralNumber literalNumber) {
  }

  @Override
  public void visitLiteralString(AstNodeVisitor visitor) {
  }

  @Override
  public void visitMessagePattern(MessagePattern messagePattern) {
    visit(messagePattern.arguments());
  }

  @Override
  public void visitMessageSendNoReceiver(MessageSendNoReceiver messageSendNoReceiver) {
    visit(messageSendNoReceiver.arguments());
  }

  @Override
  public void visitMessageSendWithReceiver(MessageSendWithReceiver messageSendWithReceiver) {
    visit(messageSendWithReceiver.receiver());
    visit(messageSendWithReceiver.arguments());
  }

  @Override
  public void visitMethod(Method method) {
    visit(method.messagePattern());
    visit(method.temps());
    visit(method.body());
  }

  @Override
  public void visitSlotDefinition(SlotDefinition slotDefinition) {
    visit(slotDefinition.initializer());
  }

}
