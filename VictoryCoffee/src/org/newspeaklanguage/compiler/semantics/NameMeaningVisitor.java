package org.newspeaklanguage.compiler.semantics;

public interface NameMeaningVisitor {

  public void visitVariableReference(NameMeaningVariableReference nameMeaningVariableReference);

  public void visitSelfSend(NameMeaningSelfSend nameMeaningSelfSend);

}
