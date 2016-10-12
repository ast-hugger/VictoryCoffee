package org.newspeaklanguage.compiler.semantics;

public class LocalVariable {
  
  private final String name;
  private boolean isBoxed;
  private int index;
  
  LocalVariable(String name, boolean isBoxed) {
    this.name = name;
    this.isBoxed = isBoxed;
  }
  
  public String name() {
    return name;
  }

  public int index() {
    return index;
  }

  
  public boolean isBoxed() {
    return isBoxed;
  }
  
  public void setIsBoxed(boolean isBoxed) {
    this.isBoxed = isBoxed;
  }


  public void setIndex(int index) {
    this.index = index;
  }
  
}
