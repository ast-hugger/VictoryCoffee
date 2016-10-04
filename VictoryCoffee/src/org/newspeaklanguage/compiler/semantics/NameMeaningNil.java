package org.newspeaklanguage.compiler.semantics;

public class NameMeaningNil extends NameMeaning {

  @Override
  public void accept(NameMeaningVisitor visitor) {
    visitor.visitNil(this);
  }

}
