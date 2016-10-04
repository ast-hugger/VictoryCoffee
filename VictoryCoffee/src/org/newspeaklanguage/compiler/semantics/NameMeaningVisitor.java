package org.newspeaklanguage.compiler.semantics;

public interface NameMeaningVisitor {

  public void visitNil(NameMeaningNil nameMeaningNil);

  public void visitLiteralBoolean(NameMeaningLiteralBoolean nameMeaningLiteralBoolean);

  public void visitVariableReference(NameMeaningVariableReference nameMeaningVariableReference);

  public void visitSelf(NameMeaningSelf nameMeaningSelfSend);

  public void visitSuper(NameMeaningSuper nameMeaningSuper);

  public void visitOuter(NameMeaningOuter nameMeaningOuter);

  public void visitSelfSend(NameMeaningSelfSend nameMeaningSelfSend);

}
