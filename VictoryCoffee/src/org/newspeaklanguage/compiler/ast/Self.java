package org.newspeaklanguage.compiler.ast;


public class Self extends AstNode {

  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitSelf(this);
  }
}
