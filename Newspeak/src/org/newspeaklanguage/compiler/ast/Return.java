package org.newspeaklanguage.compiler.ast;


public class Return extends AstNode {
  
  private final AstNode expression;
  
  Return(AstNode expression) {
    this.expression = expression;
  }

  public AstNode expression() { return expression; }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitReturn(this);
  }

}
