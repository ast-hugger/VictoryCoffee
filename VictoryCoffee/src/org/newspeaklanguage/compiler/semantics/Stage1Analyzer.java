package org.newspeaklanguage.compiler.semantics;

import org.newspeaklanguage.compiler.ast.Argument;
import org.newspeaklanguage.compiler.ast.AstNode;
import org.newspeaklanguage.compiler.ast.AstNodeVisitorSkeleton;
import org.newspeaklanguage.compiler.ast.Block;
import org.newspeaklanguage.compiler.ast.ClassDecl;
import org.newspeaklanguage.compiler.ast.Method;
import org.newspeaklanguage.compiler.ast.SlotDefinition;

/**
 * An AST visitor which walks the tree and creates and populates the associated
 * scopes. Nodes that establish scopes such as classes and methods get annotated
 * with the scopes they establish.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
 *
 */
public class Stage1Analyzer extends AstNodeVisitorSkeleton {

  public static Scope analyze(ClassDecl classDecl) {
    return new Stage1Analyzer().build(classDecl);
  }

  private Scope currentScope;

  private Stage1Analyzer() {
  }

  public Scope build(ClassDecl classDecl) {
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
      pushClassScope(classDecl);
    }
    classDecl.setScope((ClassScope) currentScope);
    classDecl.setImplementationClassName(computeImplementationName(classDecl));
    try {
      super.visitClassDecl(classDecl);
    } finally {
      popScope();
    }
  }

  @Override
  public void visitMethod(Method method) {
    defineName(method.messagePattern().selector(), method);
    pushBlockScope();
    method.setScope((MethodScope) currentScope);
    try {
      super.visitMethod(method);
    } finally {
      popScope();
    }
  }

  @Override
  public void visitBlock(Block block) {
    pushBlockScope();
    block.setScope((MethodScope) currentScope);
    try {
      super.visitBlock(block);
    } finally {
      popScope();
    }
  }

  @Override
  public void visitArgument(Argument argument) {
    assert currentScope.isMethodScope();
    defineName(argument.name(), argument);
  }

  @Override
  public void visitSlotDefinition(SlotDefinition slot) {
    defineName(slot.name(), slot);
  }
  
  protected void pushClassScope(ClassDecl classNode) {
    currentScope = new ClassScope(classNode, currentScope);
  }
  
  protected void pushBlockScope() {
    currentScope = new MethodScope(currentScope);
  }
  
  protected void defineName(String name, AstNode definingNode) {
    currentScope.define(name).setDefiningNode(definingNode);
  }

  protected void popScope() {
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
