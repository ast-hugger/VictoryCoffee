package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.ast.AstNodeVisitorSkeleton;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.ast.MessageSendNoReceiver;
import org.newspeaklanguage.compiler.ast.Method;

/**
 * A visitor for the AST which populates name references
 * ({@link MessageSendNoReceiver} nodes) with their meanings. The AST must have
 * been previously visited by a {@link Stage1Analyzer}.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class Stage2Analyzer extends AstNodeVisitorSkeleton {
  
  /*
   * Static main access point
   */
  
  public static void analyze(ClassDecl classDecl) {
    new Stage2Analyzer().visit(classDecl);
  }
  
  /*
   * Instance side
   */

  private Scope currentScope;
  
  private Stage2Analyzer() {
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
    ScopeEntry def = currentScope.lookup(messageSend.selector());
    messageSend.setLexicalDefinition(def);
    
    NameMeaning meaning;
    if (def == null) {
      meaning = NameMeaning.selfSend();
    } else {
      if (def.isLocalVariable()) {
        meaning = NameMeaning.localVarReference(def);
      } else if (def.definitionScope() != nearestClassScope()) {
        meaning = NameMeaning.sendToEnclosingObject(def);
      } else {
        meaning = NameMeaning.selfSend();
      }
    }
    messageSend.setMeaning(meaning);
  }

  private Scope nearestClassScope() {
    Scope here = currentScope;
    while (here != null && !here.isClassScope()) {
      here = here.parent();
    }
    return here;
  }
  
}
