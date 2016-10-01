package org.newspeaklanguage.compiler.ast;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.newspeaklanguage.compiler.parser.NewspeakBaseVisitor;
import org.newspeaklanguage.compiler.parser.NewspeakParser;

/**
 * The bridge between our ANTLR grammar and the AST defined in this package.
 * 
 * @author vassili
 *
 */
public class AstBuilder extends NewspeakBaseVisitor<AstNode> {

  @Override
  public AstNode visitSourceUnit(NewspeakParser.SourceUnitContext ctx) {
    // A source unit consists of a classDecl and an EOF;
    // need to handle this or we'd get the result of visiting the EOF.
    return visit(ctx.classDecl());
  }
  
  @Override
  public AstNode visitClassDecl(NewspeakParser.ClassDeclContext ctx) {
    
    // This class name and initializer
    String name = ctx.IDENTIFIER(0).getText();
    NewspeakParser.MessagePatternContext initCtx = ctx.messagePattern(0);
    MessagePattern initMessage = initCtx == null ? null : (MessagePattern) visit(initCtx);
    
    // Superclass name and initializer
    TerminalNode superclassNameCtx = ctx.IDENTIFIER(1); 
    String superclassName = superclassNameCtx == null ? null : superclassNameCtx.getText();
    NewspeakParser.MessagePatternContext superCtx = ctx.messagePattern(1);
    MessagePattern superclassInitMessage = superCtx == null ? null : (MessagePattern) visit(superCtx);
    
    List<Slot> slots = ctx.classBody().classHeader().slotDecl().stream()
        .map(each -> (Slot) visit(each))
        .collect(Collectors.toList());
// The following is better but too verbose
//        .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    
    List<ClassDecl> nestedClasses = ctx.classBody().instanceSideDecl().classDecl().stream()
        .map(each -> (ClassDecl) visit(each))
        .collect(Collectors.toList());
    
    List<Category> instCategories = ctx.classBody().instanceSideDecl().category().stream()
        .map(each -> (Category) visit(each))
        .collect(Collectors.toList());
    
    NewspeakParser.ClassSideDeclContext classSide = ctx.classBody().classSideDecl();
    List<Category> classCategories = classSide == null
        ? null
        : classSide.category().stream()
            .map(each -> (Category) visit(each))
            .collect(Collectors.toList());
    
    return new ClassDecl(name, initMessage,
        superclassName, superclassInitMessage, 
        slots, nestedClasses, instCategories, classCategories);
  }
 
  @Override
  public AstNode visitSlotDecl(NewspeakParser.SlotDeclContext ctx) {
    String name = ctx.IDENTIFIER().getText();
    boolean immutable = ctx.immutableSlotInitializer() != null;
    return new Slot(name, null, !immutable);
  }
  
  @Override
  public AstNode visitCategory(NewspeakParser.CategoryContext ctx) {
    String name = ctx.STRING().getText();
    // TODO get the actual methods
    return new Category(name, null);
  }
  
}
