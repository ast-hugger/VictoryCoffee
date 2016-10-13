package org.newspeaklanguage.compiler.semantics;

public class LocalVariable {

  private final String name;
  private final boolean isTemp;
  private boolean isBoxed;
  private int index;

  LocalVariable(String name, boolean isTemp, boolean isBoxed) {
    this.name = name;
    this.isTemp = isTemp;
    this.isBoxed = isBoxed;
  }

  public String name() {
    return name;
  }

  public boolean isTemp() {
    return isTemp;
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
