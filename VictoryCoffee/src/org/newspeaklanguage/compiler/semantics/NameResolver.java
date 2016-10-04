package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.ast.AstNodeVisitorSkeleton;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.ast.MessageSendNoReceiver;
import org.newspeaklanguage.compiler.ast.Method;

public class NameResolver extends AstNodeVisitorSkeleton {
  
  public static void resolveNames(ClassDecl classDecl) {
    new NameResolver().visit(classDecl);
  }

  private Scope currentScope;
  
  private NameResolver() {
  }
  
  @Override
  public void visitClassDecl(ClassDecl classDecl) {
    Scope original = currentScope;
    currentScope = classDecl.scope();
    try {
      super.visitClassDecl(classDecl);
    } finally {
      currentScope = original;
    }
  }
  
  @Override
  public void visitBlock(Block classDecl) {
    Scope original = currentScope;
    currentScope = classDecl.scope();
    try {
      super.visitBlock(classDecl);
    } finally {
      currentScope = original;
    }
  }
  
  @Override
  public void visitMethod(Method classDecl) {
    Scope original = currentScope;
    currentScope = classDecl.scope();
    try {
      super.visitMethod(classDecl);
    } finally {
      currentScope = original;
    }
  }
  
  @Override
  public void visitMessageSendNoReceiver(MessageSendNoReceiver messageSend) {
    NameDefinition def = currentScope.lookup(messageSend.selector());
    if (def != null) {
      messageSend.setLexicalDefinition(def);
    }
    
    if (messageSend.isName()) {
      NameMeaning meaning = null;
      switch (messageSend.selector()) {
      case "nil":
        meaning = new NameMeaningNil();
        break;
      case "true":
        meaning = new NameMeaningLiteralBoolean(true);
        break;
      case "false":
        meaning = new NameMeaningLiteralBoolean(false);
        break;
      case "self":
        meaning = new NameMeaningSelf();
        break;
      case "super":
        meaning = new NameMeaningSuper();
        break;
      case "outer":
        meaning = new NameMeaningOuter();
        break;
      default:
        meaning = def == null
            ? new NameMeaningSelfSend()
            : new NameMeaningVariableReference(def);  
      }
      messageSend.setMeaning(meaning);
    }
  }
  
}
