package org.newspeaklanguage.compiler.semantics;

public class SelfSend extends NameMeaning {

  SelfSend() {
  }

  @Override
  public boolean isSelfSend() {
    return true;
  }

}