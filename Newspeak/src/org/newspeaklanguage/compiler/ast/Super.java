package org.newspeaklanguage.compiler.ast;


public class Super extends AstNode {

  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitSuper(this);
  }

}
