package org.newspeaklanguage.compiler.ast;

/**
 * A method or block argument.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class Argument extends AstNode implements NameDefinition {
  
  private final String name;
  
  Argument(String name) {
    this.name = name;
  }
  
  @Override
  public String name() { return name; }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitArgument(this);
  }

}
