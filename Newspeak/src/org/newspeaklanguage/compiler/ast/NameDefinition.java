package org.newspeaklanguage.compiler.ast;


public interface NameDefinition {

  public String name();
  public boolean isMutable();
  default public boolean isImmutable() {
    return !isMutable();
  }
  
}
