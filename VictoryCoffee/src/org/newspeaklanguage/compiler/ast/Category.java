package org.newspeaklanguage.compiler.ast;

import java.util.List;

public class Category extends AstNode {
  
  private final String name;
  private final List<Method> methods;
  
  Category(String name, List<Method> methods) {
    this.name = name;
    this.methods = methods;
  }
  
  public String name() { return name; }
  public List<Method> methods() { return methods; }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitCategory(this);
  }

}
