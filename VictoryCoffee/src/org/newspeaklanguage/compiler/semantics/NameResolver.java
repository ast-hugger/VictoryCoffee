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
      return;
    }
    
    // See if this is a special receiver.
    // TODO
  }
  
}
