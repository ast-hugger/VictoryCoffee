// Generated from Newspeak.g4 by ANTLR 4.5.3
package org.newspeaklanguage.compiler.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link NewspeakParser}.
 */
public interface NewspeakListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#sourceUnit}.
	 * @param ctx the parse tree
	 */
	void enterSourceUnit(NewspeakParser.SourceUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#sourceUnit}.
	 * @param ctx the parse tree
	 */
	void exitSourceUnit(NewspeakParser.SourceUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#classDecl}.
	 * @param ctx the parse tree
	 */
	void enterClassDecl(NewspeakParser.ClassDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#classDecl}.
	 * @param ctx the parse tree
	 */
	void exitClassDecl(NewspeakParser.ClassDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(NewspeakParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(NewspeakParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#classHeader}.
	 * @param ctx the parse tree
	 */
	void enterClassHeader(NewspeakParser.ClassHeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#classHeader}.
	 * @param ctx the parse tree
	 */
	void exitClassHeader(NewspeakParser.ClassHeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#instanceSideDecl}.
	 * @param ctx the parse tree
	 */
	void enterInstanceSideDecl(NewspeakParser.InstanceSideDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#instanceSideDecl}.
	 * @param ctx the parse tree
	 */
	void exitInstanceSideDecl(NewspeakParser.InstanceSideDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#classSideDecl}.
	 * @param ctx the parse tree
	 */
	void enterClassSideDecl(NewspeakParser.ClassSideDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#classSideDecl}.
	 * @param ctx the parse tree
	 */
	void exitClassSideDecl(NewspeakParser.ClassSideDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#category}.
	 * @param ctx the parse tree
	 */
	void enterCategory(NewspeakParser.CategoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#category}.
	 * @param ctx the parse tree
	 */
	void exitCategory(NewspeakParser.CategoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#slotDecl}.
	 * @param ctx the parse tree
	 */
	void enterSlotDecl(NewspeakParser.SlotDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#slotDecl}.
	 * @param ctx the parse tree
	 */
	void exitSlotDecl(NewspeakParser.SlotDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#mutableSlotInitializer}.
	 * @param ctx the parse tree
	 */
	void enterMutableSlotInitializer(NewspeakParser.MutableSlotInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#mutableSlotInitializer}.
	 * @param ctx the parse tree
	 */
	void exitMutableSlotInitializer(NewspeakParser.MutableSlotInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#immutableSlotInitializer}.
	 * @param ctx the parse tree
	 */
	void enterImmutableSlotInitializer(NewspeakParser.ImmutableSlotInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#immutableSlotInitializer}.
	 * @param ctx the parse tree
	 */
	void exitImmutableSlotInitializer(NewspeakParser.ImmutableSlotInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#methodDecl}.
	 * @param ctx the parse tree
	 */
	void enterMethodDecl(NewspeakParser.MethodDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#methodDecl}.
	 * @param ctx the parse tree
	 */
	void exitMethodDecl(NewspeakParser.MethodDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#accessModifier}.
	 * @param ctx the parse tree
	 */
	void enterAccessModifier(NewspeakParser.AccessModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#accessModifier}.
	 * @param ctx the parse tree
	 */
	void exitAccessModifier(NewspeakParser.AccessModifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryPattern}
	 * labeled alternative in {@link NewspeakParser#messagePattern}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPattern(NewspeakParser.UnaryPatternContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryPattern}
	 * labeled alternative in {@link NewspeakParser#messagePattern}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPattern(NewspeakParser.UnaryPatternContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryPattern}
	 * labeled alternative in {@link NewspeakParser#messagePattern}.
	 * @param ctx the parse tree
	 */
	void enterBinaryPattern(NewspeakParser.BinaryPatternContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryPattern}
	 * labeled alternative in {@link NewspeakParser#messagePattern}.
	 * @param ctx the parse tree
	 */
	void exitBinaryPattern(NewspeakParser.BinaryPatternContext ctx);
	/**
	 * Enter a parse tree produced by the {@code keywordPattern}
	 * labeled alternative in {@link NewspeakParser#messagePattern}.
	 * @param ctx the parse tree
	 */
	void enterKeywordPattern(NewspeakParser.KeywordPatternContext ctx);
	/**
	 * Exit a parse tree produced by the {@code keywordPattern}
	 * labeled alternative in {@link NewspeakParser#messagePattern}.
	 * @param ctx the parse tree
	 */
	void exitKeywordPattern(NewspeakParser.KeywordPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#codeBody}.
	 * @param ctx the parse tree
	 */
	void enterCodeBody(NewspeakParser.CodeBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#codeBody}.
	 * @param ctx the parse tree
	 */
	void exitCodeBody(NewspeakParser.CodeBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(NewspeakParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(NewspeakParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(NewspeakParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(NewspeakParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(NewspeakParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(NewspeakParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#messageSend}.
	 * @param ctx the parse tree
	 */
	void enterMessageSend(NewspeakParser.MessageSendContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#messageSend}.
	 * @param ctx the parse tree
	 */
	void exitMessageSend(NewspeakParser.MessageSendContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#receiverlessSend}.
	 * @param ctx the parse tree
	 */
	void enterReceiverlessSend(NewspeakParser.ReceiverlessSendContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#receiverlessSend}.
	 * @param ctx the parse tree
	 */
	void exitReceiverlessSend(NewspeakParser.ReceiverlessSendContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#receiverfulSend}.
	 * @param ctx the parse tree
	 */
	void enterReceiverfulSend(NewspeakParser.ReceiverfulSendContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#receiverfulSend}.
	 * @param ctx the parse tree
	 */
	void exitReceiverfulSend(NewspeakParser.ReceiverfulSendContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#receiver}.
	 * @param ctx the parse tree
	 */
	void enterReceiver(NewspeakParser.ReceiverContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#receiver}.
	 * @param ctx the parse tree
	 */
	void exitReceiver(NewspeakParser.ReceiverContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#message}.
	 * @param ctx the parse tree
	 */
	void enterMessage(NewspeakParser.MessageContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#message}.
	 * @param ctx the parse tree
	 */
	void exitMessage(NewspeakParser.MessageContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#unaryMessage}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMessage(NewspeakParser.UnaryMessageContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#unaryMessage}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMessage(NewspeakParser.UnaryMessageContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#binaryMessage}.
	 * @param ctx the parse tree
	 */
	void enterBinaryMessage(NewspeakParser.BinaryMessageContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#binaryMessage}.
	 * @param ctx the parse tree
	 */
	void exitBinaryMessage(NewspeakParser.BinaryMessageContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#keywordMessage}.
	 * @param ctx the parse tree
	 */
	void enterKeywordMessage(NewspeakParser.KeywordMessageContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#keywordMessage}.
	 * @param ctx the parse tree
	 */
	void exitKeywordMessage(NewspeakParser.KeywordMessageContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#specialReceiver}.
	 * @param ctx the parse tree
	 */
	void enterSpecialReceiver(NewspeakParser.SpecialReceiverContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#specialReceiver}.
	 * @param ctx the parse tree
	 */
	void exitSpecialReceiver(NewspeakParser.SpecialReceiverContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#nilReceiver}.
	 * @param ctx the parse tree
	 */
	void enterNilReceiver(NewspeakParser.NilReceiverContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#nilReceiver}.
	 * @param ctx the parse tree
	 */
	void exitNilReceiver(NewspeakParser.NilReceiverContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#trueReceiver}.
	 * @param ctx the parse tree
	 */
	void enterTrueReceiver(NewspeakParser.TrueReceiverContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#trueReceiver}.
	 * @param ctx the parse tree
	 */
	void exitTrueReceiver(NewspeakParser.TrueReceiverContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#falseReceiver}.
	 * @param ctx the parse tree
	 */
	void enterFalseReceiver(NewspeakParser.FalseReceiverContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#falseReceiver}.
	 * @param ctx the parse tree
	 */
	void exitFalseReceiver(NewspeakParser.FalseReceiverContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#selfReceiver}.
	 * @param ctx the parse tree
	 */
	void enterSelfReceiver(NewspeakParser.SelfReceiverContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#selfReceiver}.
	 * @param ctx the parse tree
	 */
	void exitSelfReceiver(NewspeakParser.SelfReceiverContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#superReceiver}.
	 * @param ctx the parse tree
	 */
	void enterSuperReceiver(NewspeakParser.SuperReceiverContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#superReceiver}.
	 * @param ctx the parse tree
	 */
	void exitSuperReceiver(NewspeakParser.SuperReceiverContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#outerReceiver}.
	 * @param ctx the parse tree
	 */
	void enterOuterReceiver(NewspeakParser.OuterReceiverContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#outerReceiver}.
	 * @param ctx the parse tree
	 */
	void exitOuterReceiver(NewspeakParser.OuterReceiverContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockLiteral}
	 * labeled alternative in {@link NewspeakParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterBlockLiteral(NewspeakParser.BlockLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockLiteral}
	 * labeled alternative in {@link NewspeakParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitBlockLiteral(NewspeakParser.BlockLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link NewspeakParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(NewspeakParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link NewspeakParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(NewspeakParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link NewspeakParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(NewspeakParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link NewspeakParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(NewspeakParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(NewspeakParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(NewspeakParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#blockArgs}.
	 * @param ctx the parse tree
	 */
	void enterBlockArgs(NewspeakParser.BlockArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#blockArgs}.
	 * @param ctx the parse tree
	 */
	void exitBlockArgs(NewspeakParser.BlockArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NewspeakParser#blockTemps}.
	 * @param ctx the parse tree
	 */
	void enterBlockTemps(NewspeakParser.BlockTempsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NewspeakParser#blockTemps}.
	 * @param ctx the parse tree
	 */
	void exitBlockTemps(NewspeakParser.BlockTempsContext ctx);
}