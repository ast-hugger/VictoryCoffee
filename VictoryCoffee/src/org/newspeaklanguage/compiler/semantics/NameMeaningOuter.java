package org.newspeaklanguage.compiler.semantics;

public class NameMeaningOuter extends NameMeaning {

  @Override
  public void accept(NameMeaningVisitor visitor) {
    visitor.visitOuter(this);
  }

}
