package org.newspeaklanguage.compiler.ast;

/**
 * A method or block argument.
 * 
 * @author vassili
 *
 */
public class Argument extends AstNode {
  
  private final String name;
  
  Argument(String name) {
    this.name = name;
  }
  
  public String name() { return name; }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitArgument(this);
  }

}
