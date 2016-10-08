package org.newspeaklanguage.compiler.ast;

import java.util.List;

import org.newspeaklanguage.compiler.semantics.CodeScope;

public abstract class CodeUnit extends AstNode {
  
  private final List<SlotDefinition> temps;
  private final List<AstNode> body;
  
  private CodeScope scope;
  
  CodeUnit(List<SlotDefinition> temps, List<AstNode> body) {
    this.temps = temps;
    this.body = body;
  }
  
  public List<SlotDefinition> temps() { return temps; }
  public List<AstNode> body() { return body; }
  
  public CodeScope scope() { return scope; }
  public void setScope(CodeScope scope) { this.scope = scope; }

}
