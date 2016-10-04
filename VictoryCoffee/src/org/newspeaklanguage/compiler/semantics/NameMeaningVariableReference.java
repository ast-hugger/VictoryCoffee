package org.newspeaklanguage.compiler.semantics;

public class NameMeaningVariableReference extends NameMeaning {

  private final NameDefinition definition;
  
  NameMeaningVariableReference(NameDefinition definition) {
    this.definition = definition;
  }
  
  public NameDefinition definition() { return definition; }
  
  @Override
  public void accept(NameMeaningVisitor visitor) {
    visitor.visitVariableReference(this);
  }

}
