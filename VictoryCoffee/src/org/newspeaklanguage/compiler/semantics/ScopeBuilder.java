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
 * @author vassili
 *
 */
public class ScopeBuilder extends AstNodeVisitorSkeleton {

  public static Scope buildScopeForClass(ClassDecl classDecl) {
    return new ScopeBuilder().build(classDecl);
  }

  private Scope currentScope;

  private ScopeBuilder() {
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
      currentScope = new ClassScope(null, 0);
    } else {
      pushClassScope();
    }
    classDecl.setScope((ClassScope) currentScope);
    try {
      defineName(classDecl.name(), classDecl);
      super.visitClassDecl(classDecl);
    } finally {
      popScope();
    }
  }

  @Override
  public void visitMethod(Method method) {
    defineName(method.messagePattern().selector(), method);
    pushBlockScope();
    method.setScope((BlockScope) currentScope);
    try {
      super.visitMethod(method);
    } finally {
      popScope();
    }
  }

  @Override
  public void visitBlock(Block block) {
    pushBlockScope();
    block.setScope((BlockScope) currentScope);
    try {
      super.visitBlock(block);
    } finally {
      popScope();
    }
  }

  @Override
  public void visitArgument(Argument argument) {
    assert currentScope.isBlockScope();
    defineName(argument.name(), argument);
  }

  @Override
  public void visitSlotDefinition(SlotDefinition slot) {
    defineName(slot.name(), slot);
  }
  
  protected void pushClassScope() {
    currentScope = new ClassScope(currentScope);
  }
  
  protected void pushBlockScope() {
    currentScope = new BlockScope(currentScope);
  }
  
  protected void defineName(String name, AstNode definingNode) {
    currentScope.define(name).setDefiningNode(definingNode);
  }

  protected void popScope() {
    if (currentScope.parent() != null) {
      currentScope = currentScope.parent();
    }
  }
}
