/*
 * Copyright (c) 2016 Vassili Bykov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Generated from /home/vassili/VictoryCoffee/Newspeak/src/org/newspeaklanguage/compiler/parser/Newspeak.g4 by ANTLR 4.5.3
package org.newspeaklanguage.compiler.parser;
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
	 * Visit a parse tree produced by the {@code unaryPattern}
	 * labeled alternative in {@link NewspeakParser#messagePattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryPattern(NewspeakParser.UnaryPatternContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryPattern}
	 * labeled alternative in {@link NewspeakParser#messagePattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryPattern(NewspeakParser.BinaryPatternContext ctx);
	/**
	 * Visit a parse tree produced by the {@code keywordPattern}
	 * labeled alternative in {@link NewspeakParser#messagePattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordPattern(NewspeakParser.KeywordPatternContext ctx);
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
	 * Visit a parse tree produced by {@link NewspeakParser#messageSend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessageSend(NewspeakParser.MessageSendContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#receiver}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReceiver(NewspeakParser.ReceiverContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#binaryObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryObject(NewspeakParser.BinaryObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#keywordObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordObject(NewspeakParser.KeywordObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#setterSend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetterSend(NewspeakParser.SetterSendContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#unarySend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnarySend(NewspeakParser.UnarySendContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#unaryMessage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryMessage(NewspeakParser.UnaryMessageContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#binarySend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinarySend(NewspeakParser.BinarySendContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#binaryMessage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryMessage(NewspeakParser.BinaryMessageContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#keywordSend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordSend(NewspeakParser.KeywordSendContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#keywordMessage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordMessage(NewspeakParser.KeywordMessageContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#specialReceiver}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecialReceiver(NewspeakParser.SpecialReceiverContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#nilReceiver}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNilReceiver(NewspeakParser.NilReceiverContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#trueReceiver}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueReceiver(NewspeakParser.TrueReceiverContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#falseReceiver}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseReceiver(NewspeakParser.FalseReceiverContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#selfReceiver}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfReceiver(NewspeakParser.SelfReceiverContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#superReceiver}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperReceiver(NewspeakParser.SuperReceiverContext ctx);
	/**
	 * Visit a parse tree produced by {@link NewspeakParser#outerReceiver}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOuterReceiver(NewspeakParser.OuterReceiverContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockLiteral}
	 * labeled alternative in {@link NewspeakParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockLiteral(NewspeakParser.BlockLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link NewspeakParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteral(NewspeakParser.IntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link NewspeakParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(NewspeakParser.StringLiteralContext ctx);
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
}