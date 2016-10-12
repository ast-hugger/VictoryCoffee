package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.ast.AstNodeVisitorSkeleton;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.ast.MessageSendNoReceiver;
import org.newspeaklanguage.compiler.ast.Method;
import org.newspeaklanguage.compiler.ast.NameDefinition;
import org.newspeaklanguage.compiler.ast.Outer;

/**
 * A visitor for the AST which populates name references
 * ({@link MessageSendNoReceiver} nodes) with their meanings. The AST must have
 * been previously visited by a {@link AnalyzerStage1}.
 *
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class AnalyzerStage2 extends AstNodeVisitorSkeleton {

  /*
   * Static main access point
   */

  public static void analyze(ClassDecl classDecl) {
    new AnalyzerStage2().visit(classDecl);
  }

  /*
   * Instance side
   */

  private Scope<? extends ScopeEntry> currentScope;

  private AnalyzerStage2() {
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
  public void visitBlock(Block blockNode) {
    Scope<? extends ScopeEntry> original = currentScope;
    currentScope = blockNode.scope();
    try {
      super.visitBlock(blockNode);
      // Now that all the locals are finalized, we know their indices
      blockNode.scope().assignLocalVariableIndices();
    } finally {
      currentScope = original;
    }
  }

  @Override
  public void visitMethod(Method methodNode) {
    Scope<? extends ScopeEntry> original = currentScope;
    currentScope = methodNode.scope();
    try {
      super.visitMethod(methodNode);
      // Now that all the locals are finalized, we know their indices
      methodNode.scope().assignLocalVariableIndices();
    } finally {
      currentScope = original;
    }
  }

  @Override
  public void visitOuter(Outer outerNode) {
    ClassScope target = currentScope.outerClassNamed(outerNode.name());
    if (target == null) {
      throw new IllegalArgumentException("no such outer class");
    }
    outerNode.setTargetClassScope(target);
  }

  @Override
  public void visitMessageSendNoReceiver(MessageSendNoReceiver messageSend) {
    ScopeEntry lexicalDef = currentScope.lookup(messageSend.selector());

    NameMeaning meaning;
    if (lexicalDef == null) {
      meaning = new SelfSend();
    } else {
      if (lexicalDef.isImplementedAsLocalVar()) {
        // if it's a local var, it's a CodeScopeEntry
        meaning = new LexicalVarReference((CodeScopeEntry) lexicalDef, currentScope);
        processLocalVarReference(messageSend, (LexicalVarReference) meaning);
      } else if (!lexicalDef.scope().equals(nearestClassScope())) {
        meaning = new SendToEnclosingObject(lexicalDef);
      } else {
        meaning = new SelfSend();
      }
    }
    messageSend.setMeaning(meaning);
    super.visitMessageSendNoReceiver(messageSend);
  }

  private void processLocalVarReference(
      MessageSendNoReceiver sendNode,
      LexicalVarReference reference)
  {
    if (reference.isClean()) {
      String varName = reference.definition().astNode().name();
      LocalVariable var = ((CodeScope) currentScope).ownVariableNamed(varName).get();
      reference.setLocalVariable(var);
    } else {
      processCopiedVariable(reference);
    }
  }

  /**
   * The argument is the meaning of a receiverless self send representing a
   * reference to a lexically visible variable. It should be known as a copied
   * variable to every scope from the current and up to (but not including) the
   * defining scope. The local variable added to the current scope to represent
   * it should be known to the meaning.
   */
  private void processCopiedVariable(LexicalVarReference reference) {
    CodeScope definingScope = (CodeScope) reference.definition().scope();
    CodeScope here = (CodeScope) currentScope;
    NameDefinition varNode = reference.definition().astNode();
    
    LocalVariable var = ((BlockScope) here).registerCopiedVariable(varNode);
    reference.setLocalVariable(var);
    
    here = (CodeScope) here.parent();
    while (!here.equals(definingScope)) {
      ((BlockScope) here).registerCopiedVariable(varNode);
      here = (CodeScope) here.parent();
    }

    if (varNode.isMutable()) {
      here.markVariableAsBoxed(varNode.name());
    }
  }

  private ClassScope nearestClassScope() {
    Scope<? extends ScopeEntry> here = currentScope;
    while (here != null && !here.isClassScope()) {
      here = here.parent();
    }
    return (ClassScope) here;
  }

  // private CodeScope nearestMethodScope() {
  // Scope<? extends ScopeEntry> here = currentScope;
  // while (here != null && !here.isMethodScope()) {
  // here = here.parent();
  // }
  // return (CodeScope) here;
  // }

}
