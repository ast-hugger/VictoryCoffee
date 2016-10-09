package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.ast.AstNodeVisitorSkeleton;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.ast.MessageSendNoReceiver;
import org.newspeaklanguage.compiler.ast.Method;
import org.newspeaklanguage.compiler.ast.Outer;

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

  private Scope<? extends ScopeEntry> currentScope;
  
  private Stage2Analyzer() {
  }
  
  @Override
  public void visitClassDecl(ClassDecl classDecl) {
    Scope<? extends ScopeEntry> original = currentScope;
    currentScope = classDecl.scope();
    try {
      super.visitClassDecl(classDecl);
    } finally {
      currentScope = original;
    }
  }
  
  @Override
  public void visitBlock(Block classDecl) {
    Scope<? extends ScopeEntry> original = currentScope;
    currentScope = classDecl.scope();
    try {
      super.visitBlock(classDecl);
    } finally {
      currentScope = original;
    }
  }
  
  @Override
  public void visitMethod(Method classDecl) {
    Scope<? extends ScopeEntry> original = currentScope;
    currentScope = classDecl.scope();
    try {
      super.visitMethod(classDecl);
    } finally {
      currentScope = original;
    }
  }
  
  @Override
  public void visitOuter(Outer outerNode) {
    ClassScope target = currentScope.lookupClass(outerNode.name());
    if (target == null) {
      throw new IllegalArgumentException("no such outer class");
    }
    outerNode.setTargetClassScope(target);
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
        meaning = NameMeaning.localVarReference(def, currentScope);
      } else if (def.scope() != nearestClassScope()) {
        meaning = NameMeaning.sendToEnclosingObject(def);
      } else {
        meaning = NameMeaning.selfSend();
      }
    }
    messageSend.setMeaning(meaning);
    super.visitMessageSendNoReceiver(messageSend);
  }

  private ClassScope nearestClassScope() {
    Scope<? extends ScopeEntry> here = currentScope;
    while (here != null && !here.isClassScope()) {
      here = here.parent();
    }
    return (ClassScope) here;
  }
  
}
