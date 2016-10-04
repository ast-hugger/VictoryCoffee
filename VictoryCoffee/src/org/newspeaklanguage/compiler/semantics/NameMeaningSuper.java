package org.newspeaklanguage.compiler.semantics;

public class NameMeaningSuper extends NameMeaning {

  @Override
  public void accept(NameMeaningVisitor visitor) {
    visitor.visitSuper(this);
  }

}
