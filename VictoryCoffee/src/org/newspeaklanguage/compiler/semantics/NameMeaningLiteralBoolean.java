package org.newspeaklanguage.compiler.semantics;

public class NameMeaningLiteralBoolean extends NameMeaning {

  private final boolean value;
  
  NameMeaningLiteralBoolean(boolean value) {
    this.value = value;
  }
  
  public boolean value() { return value; }
  
  @Override
  public void accept(NameMeaningVisitor visitor) {
    visitor.visitLiteralBoolean(this);
  }

}
