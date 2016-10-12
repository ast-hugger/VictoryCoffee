package org.newspeaklanguage.compiler.semantics;

public class SelfSend extends NameMeaning {

  @Override
  public boolean isSelfSend() {
    return true;
  }

}