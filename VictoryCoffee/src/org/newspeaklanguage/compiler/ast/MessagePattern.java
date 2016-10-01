package org.newspeaklanguage.compiler.ast;

import java.util.List;

public class MessagePattern extends AstNode {
  
  private final String selector;
  private final List<String> argumentNames;
  
  MessagePattern(String selector, List<String> argumentNames) {
    this.selector = selector;
    this.argumentNames = argumentNames;
  }

  public String selector() { return selector; }
  public List<String> argumentNames() { return argumentNames; }
  
  
}
