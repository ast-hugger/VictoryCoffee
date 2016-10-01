package org.newspeaklanguage.compiler.ast;

import java.util.List;

public abstract class CodeUnit extends AstNode {
  
  private final List<Slot> temps;
  private final List<AstNode> body;
  
  CodeUnit(List<Slot> temps, List<AstNode> body) {
    this.temps = temps;
    this.body = body;
  }
  
  public List<Slot> temps() { return temps; }
  public List<AstNode> body() { return body; }

}
