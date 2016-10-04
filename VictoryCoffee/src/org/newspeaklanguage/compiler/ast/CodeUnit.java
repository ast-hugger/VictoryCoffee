package org.newspeaklanguage.compiler.ast;

import java.util.List;

import org.newspeaklanguage.compiler.semantics.BlockScope;

public abstract class CodeUnit extends AstNode {
  
  private final List<SlotDefinition> temps;
  private final List<AstNode> body;
  
  private BlockScope scope;
  
  CodeUnit(List<SlotDefinition> temps, List<AstNode> body) {
    this.temps = temps;
    this.body = body;
  }
  
  public List<SlotDefinition> temps() { return temps; }
  public List<AstNode> body() { return body; }
  
  public BlockScope scope() { return scope; }
  public void setScope(BlockScope scope) { this.scope = scope; }

}
