package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.NamingPolicy;
import org.newspeaklanguage.compiler.ast.Argument;
import org.newspeaklanguage.compiler.ast.AstNodeVisitorSkeleton;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.ast.Method;
import org.newspeaklanguage.compiler.ast.NameDefinition;
import org.newspeaklanguage.compiler.ast.SlotDefinition;

/**
 * An AST visitor for the first stage of semantic analysis. AST nodes are
 * annotated with additional information to aid future code generation.
 * <p>
 * This stage of analysis involves the following:
 * <ul>
 * <li>Scope objects are created for all nodes that establish scopes. Those are
 * class, method, and block nodes. The scopes are stored in the defining nodes.
 * <li>The scopes are populated with the names defined in them. Names are
 * defined by class definitions, class slots, method selectors, method
 * arguments, method temporaries, block arguments, and block temporaries.
 * <li>Implementation names of classes are computed.
 * <li>Each method gets a list of all blocks that appear in the method.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class AnalyzerStage1 extends AstNodeVisitorSkeleton {

  public static Scope<? extends ScopeEntry> analyze(ClassDecl classDecl) {
    return new AnalyzerStage1().build(classDecl);
  }

  private Scope<? extends ScopeEntry> currentScope;
  private Method currentMethod;

  private AnalyzerStage1() {
  }

  public Scope<? extends ScopeEntry> build(ClassDecl classDecl) {
    currentScope = null;
    visit(classDecl);
    // Visiting a ClassDecl does not pop the last scope it created,
    // so currentScope holds the top scope once we are done.
    return currentScope;
  }

  @Override
  public void visitClassDecl(ClassDecl classDecl) {
    if (currentScope == null) {
      currentScope = new ClassScope(classDecl, null, 0);
    } else {
      // Nested class; its containing scope needs a getter
      assert currentScope.isClassScope();
      defineName(classDecl.name(), classDecl);
      classDecl.setEnclosingClass(((ClassScope) currentScope).classNode());
      enterScope(classDecl);
    }
    classDecl.setScope((ClassScope) currentScope);
    classDecl.setImplementationClassName(computeImplementationName(classDecl));
    try {
      super.visitClassDecl(classDecl);
    } finally {
      exitScope();
    }
  }

  @Override
  public void visitMethod(Method method) {
    // Define the method's selector in the current scope
    // (the one for the class containing the method).
    assert currentMethod == null;
    currentMethod = method;
    defineName(method.messagePattern().selector(), method);
    enterScope(method);
    method.setScope((CodeScope) currentScope);
    try {
      super.visitMethod(method);
    } finally {
      exitScope();
      currentMethod = null;
    }
  }

  @Override
  public void visitBlock(Block block) {
    enterScope(block);
    block.setScope((CodeScope) currentScope);
    currentMethod.addBlock(block);
    try {
      super.visitBlock(block);
    } finally {
      exitScope();
    }
  }

  @Override
  public void visitArgument(Argument argument) {
    assert currentScope.isMethodScope() || currentScope.isBlockScope();
    defineName(NamingPolicy.getterForSlot(argument.name()), argument);
  }

  @Override
  public void visitSlotDefinition(SlotDefinition slot) {
    defineName(NamingPolicy.getterForSlot(slot.name()), slot);
    if (slot.isMutable()) {
      defineName(NamingPolicy.setterForSlot(slot.name()), slot);
    }
  }
  
  protected void enterScope(ClassDecl classNode) {
    currentScope = new ClassScope(classNode, currentScope);
  }
  
  protected void enterScope(Method methodNode) {
    currentScope = new MethodScope(methodNode, currentScope);
  }
  
  protected void enterScope(Block blockNode) {
    currentScope = new BlockScope(blockNode, currentScope);
  }
  
  protected void defineName(String name, NameDefinition definingNode) {
    currentScope.define(name).setAstNode(definingNode);
  }

  protected void exitScope() {
    if (currentScope.parent() != null) {
      currentScope = currentScope.parent();
    }
  }
  
  private String computeImplementationName(ClassDecl classNode) {
    StringBuilder result = new StringBuilder();
    printClassNamesToBuilder(result, classNode, true);
    return result.toString();
  }
  
  private void printClassNamesToBuilder(StringBuilder builder, ClassDecl node, boolean innermost) {
    if (node.enclosingClass() != null) {
      printClassNamesToBuilder(builder, node.enclosingClass(), false);
    }
    builder.append(node.name());
    if (!innermost) {
      builder.append('$');
    }
  }

}
