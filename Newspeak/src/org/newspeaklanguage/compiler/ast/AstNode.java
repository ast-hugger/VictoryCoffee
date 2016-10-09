package org.newspeaklanguage.compiler.ast;

public abstract class AstNode {
  
  public abstract void accept(AstNodeVisitor visitor);

}
