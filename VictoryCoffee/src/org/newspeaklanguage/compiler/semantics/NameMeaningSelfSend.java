package org.newspeaklanguage.compiler.semantics;

public class NameMeaningSelfSend extends NameMeaning {

  @Override
  public void accept(NameMeaningVisitor visitor) {
    visitor.visitSelfSend(this);
  }

}
