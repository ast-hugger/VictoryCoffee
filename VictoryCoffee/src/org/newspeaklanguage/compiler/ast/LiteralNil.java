package org.newspeaklanguage.compiler.ast;

public class LiteralNil extends AstNode {

  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitLiteralNil(this);
  }

}
