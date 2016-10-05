package org.newspeaklanguage.compiler.ast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.newspeaklanguage.compiler.parser.NewspeakBaseVisitor;
import org.newspeaklanguage.compiler.parser.NewspeakParser;

/**
 * The bridge between our ANTLR grammar and the AST defined in this package.
 * 
 * @author Vassili Bykov <newspeakbigot@gmail.com>
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
    
    List<SlotDefinition> slots = ctx.classBody().classHeader().slotDecl().stream()
        .map(each -> (SlotDefinition) visit(each))
        .collect(Collectors.toList());
// The following is better in theory, but too verbose
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
    return new SlotDefinition(name, null, !immutable);
  }
  
  @Override
  public AstNode visitCategory(NewspeakParser.CategoryContext ctx) {
    String name = ctx.STRING().getText();
    List<Method> methods = ctx.methodDecl().stream()
        .map(each -> (Method) visit(each))
        .collect(Collectors.toList());
    return new Category(name, methods);
  }
  
  @Override
  public AstNode visitMethodDecl(NewspeakParser.MethodDeclContext ctx) {
    MessagePattern pattern = (MessagePattern) visit(ctx.messagePattern());
    List<SlotDefinition> temps = ctx.slotDecl().stream()
        .map(each -> (SlotDefinition) visit(each))
        .collect(Collectors.toList());
    List<AstNode> statements = ctx.codeBody().statement().stream()
        .map(each -> visit(each))
        .collect(Collectors.toList());
    return new Method(pattern, temps, statements);
  }
  
  @Override
  public AstNode visitUnaryPattern(NewspeakParser.UnaryPatternContext ctx) {
    return new MessagePattern(ctx.IDENTIFIER().getText(), Collections.emptyList());
  }
  
  @Override
  public AstNode visitBinaryPattern(NewspeakParser.BinaryPatternContext ctx) {
    Argument arg = new Argument(ctx.IDENTIFIER().getText()); 
    return new MessagePattern(ctx.BINARY_SELECTOR().getText(), Arrays.asList(arg));
  }
  
  @Override
  public AstNode visitKeywordPattern(NewspeakParser.KeywordPatternContext ctx) {
    Optional<String> partialSelector = ctx.KEYWORD().stream()
        .map(each -> each.getText())
        .reduce((a, b) -> a + ":" + b);
    String selector = partialSelector.get() + ":";
    List<Argument> args = ctx.IDENTIFIER().stream()
        .map(each -> new Argument(each.getText()))
        .collect(Collectors.toList());
    return new MessagePattern(selector, args);
  }
  
  // TODO we don't do setter sends for now
  
  @Override
  public AstNode visitNilReceiver(NewspeakParser.NilReceiverContext ctx) {
    return new LiteralNil();
  }
  
  @Override
  public AstNode visitTrueReceiver(NewspeakParser.TrueReceiverContext ctx) {
    return new LiteralBoolean(true);
  }
  
  @Override
  public AstNode visitFalseReceiver(NewspeakParser.FalseReceiverContext ctx) {
    return new LiteralBoolean(false);
  }
  
  @Override
  public AstNode visitSelfReceiver(NewspeakParser.SelfReceiverContext ctx) {
    return new Self();
  }
  
  @Override
  public AstNode visitSuperReceiver(NewspeakParser.SuperReceiverContext ctx) {
    return new Super();
  }
  
  @Override
  public AstNode visitOuterReceiver(NewspeakParser.OuterReceiverContext ctx) {
    return new Outer(ctx.IDENTIFIER().getText());
  }
  
  @Override
  public AstNode visitReceiverlessSend(NewspeakParser.ReceiverlessSendContext ctx) {
    NewspeakParser.UnaryMessageContext unary = ctx.message().unaryMessage();
    if (unary != null) {
      String selector = unary.IDENTIFIER().getText();
      return new MessageSendNoReceiver(selector, Collections.emptyList(), false);
    }
    
    NewspeakParser.BinaryMessageContext binary = ctx.message().binaryMessage();
    if (binary != null) {
      String selector = binary.BINARY_SELECTOR().getText();
      AstNode arg = visit(binary.expression());
      return new MessageSendNoReceiver(selector, Arrays.asList(arg), false);
    }
    
    NewspeakParser.KeywordMessageContext message = ctx.message().keywordMessage();
    assert message != null;
    Optional<String> keyword = message.KEYWORD().stream()
        .map(each -> each.getText())
        .reduce(String::concat);
    List<AstNode> args = message.expression().stream()
        .map(this::visit)
        .collect(Collectors.toList());
    return new MessageSendNoReceiver(keyword.get(), args, false);
  }
  
  @Override
  public AstNode visitReceiverfulSend(NewspeakParser.ReceiverfulSendContext ctx) {
    AstNode receiver = visit(ctx.receiver());
    
    NewspeakParser.UnaryMessageContext unary = ctx.message().unaryMessage();
    if (unary != null) {
      String selector = unary.IDENTIFIER().getText();
      return new MessageSendWithReceiver(receiver, selector, Collections.emptyList());
    }
    
    NewspeakParser.BinaryMessageContext binary = ctx.message().binaryMessage();
    if (binary != null) {
      String selector = binary.BINARY_SELECTOR().getText();
      AstNode arg = visit(binary.expression());
      return new MessageSendWithReceiver(receiver, selector, Arrays.asList(arg));
    }
    
    NewspeakParser.KeywordMessageContext message = ctx.message().keywordMessage();
    assert message != null;
    Optional<String> keyword = message.KEYWORD().stream()
        .map(each -> each.getText())
        .reduce(String::concat);
    List<AstNode> args = message.expression().stream()
        .map(this::visit)
        .collect(Collectors.toList());
    return new MessageSendWithReceiver(receiver, keyword.get(), args);
  }
  
  @Override
  public AstNode visitLiteral(NewspeakParser.LiteralContext ctx) {
    if (ctx.INTEGER() != null) {
      int intValue = Integer.parseInt(ctx.INTEGER().getText());
      return new LiteralNumber(intValue);
    }
    throw new IllegalArgumentException("Unsupported literal");
  }
  
  @Override
  public AstNode visitReturnStatement(NewspeakParser.ReturnStatementContext ctx) {
    AstNode expr = visit(ctx.expression());
    return new Return(expr);
  }
  
}
