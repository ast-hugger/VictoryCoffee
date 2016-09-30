// Generated from /home/vassili/VictoryCoffee/VictoryCoffee/src/org/newspeaklanguage/parser/Newspeak.g4 by ANTLR 4.5.3
package org.newspeaklanguage.parser; 
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link NewspeakParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface NewspeakVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#sourceUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSourceUnit(NewspeakParser.SourceUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#classDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDecl(NewspeakParser.ClassDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#classBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBody(NewspeakParser.ClassBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#classHeader}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassHeader(NewspeakParser.ClassHeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#instanceSideDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstanceSideDecl(NewspeakParser.InstanceSideDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#classSideDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassSideDecl(NewspeakParser.ClassSideDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#category}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCategory(NewspeakParser.CategoryContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#slotDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSlotDecl(NewspeakParser.SlotDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#mutableSlotInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMutableSlotInitializer(NewspeakParser.MutableSlotInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#immutableSlotInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImmutableSlotInitializer(NewspeakParser.ImmutableSlotInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#methodDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDecl(NewspeakParser.MethodDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#accessModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccessModifier(NewspeakParser.AccessModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#messagePattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessagePattern(NewspeakParser.MessagePatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#codeBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeBody(NewspeakParser.CodeBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(NewspeakParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(NewspeakParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(NewspeakParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(NewspeakParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#blockArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockArgs(NewspeakParser.BlockArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#blockTemps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockTemps(NewspeakParser.BlockTempsContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#messageSend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessageSend(NewspeakParser.MessageSendContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#receiverlessSend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReceiverlessSend(NewspeakParser.ReceiverlessSendContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#receiverfulSend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReceiverfulSend(NewspeakParser.ReceiverfulSendContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#receiver}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReceiver(NewspeakParser.ReceiverContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#specialReceiver}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecialReceiver(NewspeakParser.SpecialReceiverContext ctx);
}