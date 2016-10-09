package org.newspeaklanguage.compiler.ast;

import org.newspeaklanguage.compiler.semantics.ClassScope;

public class Outer extends AstNode {

  private final String name;
  
  // Set by the analyzer; the scope of the lexically visible class referenced by this node
  private ClassScope targetClassScope; 
  
  Outer(String name) {
    this.name = name;
  }
  
  public String name() { return name; }
  public ClassScope targetClassScope() { return targetClassScope; }
  public void setTargetClassScope(ClassScope scope) { this.targetClassScope = scope; }
  
  @Override
  public void accept(AstNodeVisitor visitor) {
    visitor.visitOuter(this);
  }

}
