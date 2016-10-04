package org.newspeaklanguage.compiler.semantics;

public class NameMeaningSelf extends NameMeaning {

  @Override
  public void accept(NameMeaningVisitor visitor) {
    visitor.visitSelf(this);
  }

}
