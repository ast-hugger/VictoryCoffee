package org.newspeaklanguage.compiler.ast;


public class Outer extends AstNode {

  private final String name;
  
  Outer(String name) {
    this.name = name;
  }
  
  public String name() {
    return name;
  }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitOuter(this);
  }

}
