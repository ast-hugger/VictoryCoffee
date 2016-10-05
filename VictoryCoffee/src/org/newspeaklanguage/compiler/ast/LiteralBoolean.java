package org.newspeaklanguage.compiler.ast;


public class LiteralBoolean extends AstNode {

  private final boolean value;
  
  LiteralBoolean(boolean value) {
    this.value = value;
  }
  
  public boolean value() { return value; }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitLiteralBoolean(this);
  }

}
