package org.newspeaklanguage.compiler.semantics;

import java.util.Optional;

public class SendToEnclosingObject extends NameMeaning {

  private final ScopeEntry definition;

  SendToEnclosingObject(ScopeEntry def) {
    this.definition = def;
  }

  public ScopeEntry definition() {
    return definition;
  }

  @Override
  public Optional<ScopeEntry> lexicalDefinition() {
    return Optional.of(definition);
  }
  
  @Override
  public boolean isSendToEnclosingObject() {
    return true;
  }
}