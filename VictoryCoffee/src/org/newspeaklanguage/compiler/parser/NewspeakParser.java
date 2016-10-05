// Generated from Newspeak.g4 by ANTLR 4.5.3
package org.newspeaklanguage.compiler.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NewspeakParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, NIL=5, TRUE=6, FALSE=7, SELF=8, SUPER=9, 
		OUTER=10, CARET=11, COLON=12, COMMA=13, DOT=14, EQUAL_SIGN=15, CCE_SIGN=16, 
		LBRACKET=17, LCURLY=18, LPAREN=19, LANGLE=20, POUND=21, RANGLE=22, RBRACKET=23, 
		RCURLY=24, RPAREN=25, SEMICOLON=26, VBAR=27, BINARY_SELECTOR=28, IDENTIFIER=29, 
		KEYWORD=30, SETTER_KEYWORD=31, BLOCK_ARG=32, STRING=33, INTEGER=34, COMMENT=35, 
		WHITESPACE=36;
	public static final int
		RULE_sourceUnit = 0, RULE_classDecl = 1, RULE_classBody = 2, RULE_classHeader = 3, 
		RULE_instanceSideDecl = 4, RULE_classSideDecl = 5, RULE_category = 6, 
		RULE_slotDecl = 7, RULE_mutableSlotInitializer = 8, RULE_immutableSlotInitializer = 9, 
		RULE_methodDecl = 10, RULE_accessModifier = 11, RULE_messagePattern = 12, 
		RULE_codeBody = 13, RULE_statement = 14, RULE_returnStatement = 15, RULE_expression = 16, 
		RULE_messageSend = 17, RULE_receiverlessSend = 18, RULE_receiverfulSend = 19, 
		RULE_receiver = 20, RULE_message = 21, RULE_unaryMessage = 22, RULE_binaryMessage = 23, 
		RULE_keywordMessage = 24, RULE_specialReceiver = 25, RULE_nilReceiver = 26, 
		RULE_trueReceiver = 27, RULE_falseReceiver = 28, RULE_selfReceiver = 29, 
		RULE_superReceiver = 30, RULE_outerReceiver = 31, RULE_literal = 32, RULE_block = 33, 
		RULE_blockArgs = 34, RULE_blockTemps = 35;
	public static final String[] ruleNames = {
		"sourceUnit", "classDecl", "classBody", "classHeader", "instanceSideDecl", 
		"classSideDecl", "category", "slotDecl", "mutableSlotInitializer", "immutableSlotInitializer", 
		"methodDecl", "accessModifier", "messagePattern", "codeBody", "statement", 
		"returnStatement", "expression", "messageSend", "receiverlessSend", "receiverfulSend", 
		"receiver", "message", "unaryMessage", "binaryMessage", "keywordMessage", 
		"specialReceiver", "nilReceiver", "trueReceiver", "falseReceiver", "selfReceiver", 
		"superReceiver", "outerReceiver", "literal", "block", "blockArgs", "blockTemps"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'class'", "'public'", "'protected'", "'private'", "'nil'", "'true'", 
		"'false'", "'self'", "'super'", "'outer'", "'^'", "':'", "','", "'.'", 
		"'='", "'::='", "'['", "'{'", "'('", "'<'", "'#'", "'>'", "']'", "'}'", 
		"')'", "';'", "'|'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "NIL", "TRUE", "FALSE", "SELF", "SUPER", 
		"OUTER", "CARET", "COLON", "COMMA", "DOT", "EQUAL_SIGN", "CCE_SIGN", "LBRACKET", 
		"LCURLY", "LPAREN", "LANGLE", "POUND", "RANGLE", "RBRACKET", "RCURLY", 
		"RPAREN", "SEMICOLON", "VBAR", "BINARY_SELECTOR", "IDENTIFIER", "KEYWORD", 
		"SETTER_KEYWORD", "BLOCK_ARG", "STRING", "INTEGER", "COMMENT", "WHITESPACE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Newspeak.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public NewspeakParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class SourceUnitContext extends ParserRuleContext {
		public ClassDeclContext classDecl() {
			return getRuleContext(ClassDeclContext.class,0);
		}
		public TerminalNode EOF() { return getToken(NewspeakParser.EOF, 0); }
		public SourceUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sourceUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterSourceUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitSourceUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitSourceUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SourceUnitContext sourceUnit() throws RecognitionException {
		SourceUnitContext _localctx = new SourceUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sourceUnit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			classDecl();
			setState(73);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(NewspeakParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(NewspeakParser.IDENTIFIER, i);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public List<MessagePatternContext> messagePattern() {
			return getRuleContexts(MessagePatternContext.class);
		}
		public MessagePatternContext messagePattern(int i) {
			return getRuleContext(MessagePatternContext.class,i);
		}
		public ClassDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterClassDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitClassDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitClassDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclContext classDecl() throws RecognitionException {
		ClassDeclContext _localctx = new ClassDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__0);
			setState(76);
			match(IDENTIFIER);
			setState(78);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BINARY_SELECTOR) | (1L << IDENTIFIER) | (1L << KEYWORD))) != 0)) {
				{
				setState(77);
				messagePattern();
				}
			}

			setState(80);
			match(EQUAL_SIGN);
			setState(83);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(81);
				match(IDENTIFIER);
				setState(82);
				messagePattern();
				}
			}

			setState(85);
			classBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyContext extends ParserRuleContext {
		public ClassHeaderContext classHeader() {
			return getRuleContext(ClassHeaderContext.class,0);
		}
		public InstanceSideDeclContext instanceSideDecl() {
			return getRuleContext(InstanceSideDeclContext.class,0);
		}
		public ClassSideDeclContext classSideDecl() {
			return getRuleContext(ClassSideDeclContext.class,0);
		}
		public ClassBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterClassBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitClassBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitClassBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassBodyContext classBody() throws RecognitionException {
		ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			classHeader();
			setState(88);
			instanceSideDecl();
			setState(90);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(89);
				classSideDecl();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassHeaderContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(NewspeakParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(NewspeakParser.RPAREN, 0); }
		public List<TerminalNode> VBAR() { return getTokens(NewspeakParser.VBAR); }
		public TerminalNode VBAR(int i) {
			return getToken(NewspeakParser.VBAR, i);
		}
		public List<SlotDeclContext> slotDecl() {
			return getRuleContexts(SlotDeclContext.class);
		}
		public SlotDeclContext slotDecl(int i) {
			return getRuleContext(SlotDeclContext.class,i);
		}
		public ClassHeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classHeader; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterClassHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitClassHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitClassHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassHeaderContext classHeader() throws RecognitionException {
		ClassHeaderContext _localctx = new ClassHeaderContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_classHeader);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(LPAREN);
			setState(101);
			_la = _input.LA(1);
			if (_la==VBAR) {
				{
				setState(93);
				match(VBAR);
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << IDENTIFIER))) != 0)) {
					{
					{
					setState(94);
					slotDecl();
					}
					}
					setState(99);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(100);
				match(VBAR);
				}
			}

			setState(103);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstanceSideDeclContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(NewspeakParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(NewspeakParser.RPAREN, 0); }
		public List<ClassDeclContext> classDecl() {
			return getRuleContexts(ClassDeclContext.class);
		}
		public ClassDeclContext classDecl(int i) {
			return getRuleContext(ClassDeclContext.class,i);
		}
		public List<CategoryContext> category() {
			return getRuleContexts(CategoryContext.class);
		}
		public CategoryContext category(int i) {
			return getRuleContext(CategoryContext.class,i);
		}
		public InstanceSideDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instanceSideDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterInstanceSideDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitInstanceSideDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitInstanceSideDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstanceSideDeclContext instanceSideDecl() throws RecognitionException {
		InstanceSideDeclContext _localctx = new InstanceSideDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_instanceSideDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(LPAREN);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(106);
				classDecl();
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(112);
				category();
				}
				}
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(118);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassSideDeclContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(NewspeakParser.COLON, 0); }
		public TerminalNode LPAREN() { return getToken(NewspeakParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(NewspeakParser.RPAREN, 0); }
		public List<CategoryContext> category() {
			return getRuleContexts(CategoryContext.class);
		}
		public CategoryContext category(int i) {
			return getRuleContext(CategoryContext.class,i);
		}
		public ClassSideDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classSideDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterClassSideDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitClassSideDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitClassSideDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassSideDeclContext classSideDecl() throws RecognitionException {
		ClassSideDeclContext _localctx = new ClassSideDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_classSideDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(COLON);
			setState(121);
			match(LPAREN);
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(122);
				category();
				}
				}
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(128);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CategoryContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(NewspeakParser.STRING, 0); }
		public List<MethodDeclContext> methodDecl() {
			return getRuleContexts(MethodDeclContext.class);
		}
		public MethodDeclContext methodDecl(int i) {
			return getRuleContext(MethodDeclContext.class,i);
		}
		public CategoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterCategory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitCategory(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitCategory(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CategoryContext category() throws RecognitionException {
		CategoryContext _localctx = new CategoryContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_category);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(STRING);
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << BINARY_SELECTOR) | (1L << IDENTIFIER) | (1L << KEYWORD))) != 0)) {
				{
				{
				setState(131);
				methodDecl();
				}
				}
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SlotDeclContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(NewspeakParser.IDENTIFIER, 0); }
		public AccessModifierContext accessModifier() {
			return getRuleContext(AccessModifierContext.class,0);
		}
		public MutableSlotInitializerContext mutableSlotInitializer() {
			return getRuleContext(MutableSlotInitializerContext.class,0);
		}
		public ImmutableSlotInitializerContext immutableSlotInitializer() {
			return getRuleContext(ImmutableSlotInitializerContext.class,0);
		}
		public SlotDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slotDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterSlotDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitSlotDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitSlotDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SlotDeclContext slotDecl() throws RecognitionException {
		SlotDeclContext _localctx = new SlotDeclContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_slotDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3))) != 0)) {
				{
				setState(137);
				accessModifier();
				}
			}

			setState(140);
			match(IDENTIFIER);
			setState(143);
			switch (_input.LA(1)) {
			case CCE_SIGN:
				{
				setState(141);
				mutableSlotInitializer();
				}
				break;
			case EQUAL_SIGN:
				{
				setState(142);
				immutableSlotInitializer();
				}
				break;
			case T__1:
			case T__2:
			case T__3:
			case VBAR:
			case IDENTIFIER:
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MutableSlotInitializerContext extends ParserRuleContext {
		public TerminalNode CCE_SIGN() { return getToken(NewspeakParser.CCE_SIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(NewspeakParser.DOT, 0); }
		public MutableSlotInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mutableSlotInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterMutableSlotInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitMutableSlotInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitMutableSlotInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MutableSlotInitializerContext mutableSlotInitializer() throws RecognitionException {
		MutableSlotInitializerContext _localctx = new MutableSlotInitializerContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_mutableSlotInitializer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(CCE_SIGN);
			setState(146);
			expression();
			setState(147);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImmutableSlotInitializerContext extends ParserRuleContext {
		public TerminalNode EQUAL_SIGN() { return getToken(NewspeakParser.EQUAL_SIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(NewspeakParser.DOT, 0); }
		public ImmutableSlotInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_immutableSlotInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterImmutableSlotInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitImmutableSlotInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitImmutableSlotInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImmutableSlotInitializerContext immutableSlotInitializer() throws RecognitionException {
		ImmutableSlotInitializerContext _localctx = new ImmutableSlotInitializerContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_immutableSlotInitializer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(EQUAL_SIGN);
			setState(150);
			expression();
			setState(151);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclContext extends ParserRuleContext {
		public MessagePatternContext messagePattern() {
			return getRuleContext(MessagePatternContext.class,0);
		}
		public TerminalNode EQUAL_SIGN() { return getToken(NewspeakParser.EQUAL_SIGN, 0); }
		public TerminalNode LPAREN() { return getToken(NewspeakParser.LPAREN, 0); }
		public CodeBodyContext codeBody() {
			return getRuleContext(CodeBodyContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(NewspeakParser.RPAREN, 0); }
		public AccessModifierContext accessModifier() {
			return getRuleContext(AccessModifierContext.class,0);
		}
		public List<TerminalNode> VBAR() { return getTokens(NewspeakParser.VBAR); }
		public TerminalNode VBAR(int i) {
			return getToken(NewspeakParser.VBAR, i);
		}
		public List<SlotDeclContext> slotDecl() {
			return getRuleContexts(SlotDeclContext.class);
		}
		public SlotDeclContext slotDecl(int i) {
			return getRuleContext(SlotDeclContext.class,i);
		}
		public MethodDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterMethodDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitMethodDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitMethodDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodDeclContext methodDecl() throws RecognitionException {
		MethodDeclContext _localctx = new MethodDeclContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_methodDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3))) != 0)) {
				{
				setState(153);
				accessModifier();
				}
			}

			setState(156);
			messagePattern();
			setState(157);
			match(EQUAL_SIGN);
			setState(158);
			match(LPAREN);
			setState(167);
			_la = _input.LA(1);
			if (_la==VBAR) {
				{
				setState(159);
				match(VBAR);
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << IDENTIFIER))) != 0)) {
					{
					{
					setState(160);
					slotDecl();
					}
					}
					setState(165);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(166);
				match(VBAR);
				}
			}

			setState(169);
			codeBody();
			setState(170);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AccessModifierContext extends ParserRuleContext {
		public AccessModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterAccessModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitAccessModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitAccessModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccessModifierContext accessModifier() throws RecognitionException {
		AccessModifierContext _localctx = new AccessModifierContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_accessModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MessagePatternContext extends ParserRuleContext {
		public MessagePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_messagePattern; }
	 
		public MessagePatternContext() { }
		public void copyFrom(MessagePatternContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UnaryPatternContext extends MessagePatternContext {
		public TerminalNode IDENTIFIER() { return getToken(NewspeakParser.IDENTIFIER, 0); }
		public UnaryPatternContext(MessagePatternContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterUnaryPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitUnaryPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitUnaryPattern(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class KeywordPatternContext extends MessagePatternContext {
		public List<TerminalNode> KEYWORD() { return getTokens(NewspeakParser.KEYWORD); }
		public TerminalNode KEYWORD(int i) {
			return getToken(NewspeakParser.KEYWORD, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(NewspeakParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(NewspeakParser.IDENTIFIER, i);
		}
		public KeywordPatternContext(MessagePatternContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterKeywordPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitKeywordPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitKeywordPattern(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryPatternContext extends MessagePatternContext {
		public TerminalNode BINARY_SELECTOR() { return getToken(NewspeakParser.BINARY_SELECTOR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(NewspeakParser.IDENTIFIER, 0); }
		public BinaryPatternContext(MessagePatternContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterBinaryPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitBinaryPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitBinaryPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessagePatternContext messagePattern() throws RecognitionException {
		MessagePatternContext _localctx = new MessagePatternContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_messagePattern);
		int _la;
		try {
			setState(183);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				_localctx = new UnaryPatternContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				match(IDENTIFIER);
				}
				break;
			case BINARY_SELECTOR:
				_localctx = new BinaryPatternContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				match(BINARY_SELECTOR);
				setState(176);
				match(IDENTIFIER);
				}
				break;
			case KEYWORD:
				_localctx = new KeywordPatternContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(179); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(177);
					match(KEYWORD);
					setState(178);
					match(IDENTIFIER);
					}
					}
					setState(181); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==KEYWORD );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CodeBodyContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(NewspeakParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(NewspeakParser.DOT, i);
		}
		public CodeBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterCodeBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitCodeBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitCodeBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeBodyContext codeBody() throws RecognitionException {
		CodeBodyContext _localctx = new CodeBodyContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_codeBody);
		int _la;
		try {
			int _alt;
			setState(197);
			switch (_input.LA(1)) {
			case RBRACKET:
			case RPAREN:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case NIL:
			case TRUE:
			case FALSE:
			case SELF:
			case SUPER:
			case OUTER:
			case CARET:
			case LBRACKET:
			case LPAREN:
			case BINARY_SELECTOR:
			case IDENTIFIER:
			case KEYWORD:
			case STRING:
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				statement();
				setState(191);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(187);
						match(DOT);
						setState(188);
						statement();
						}
						} 
					}
					setState(193);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				}
				setState(195);
				_la = _input.LA(1);
				if (_la==DOT) {
					{
					setState(194);
					match(DOT);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_statement);
		try {
			setState(201);
			switch (_input.LA(1)) {
			case NIL:
			case TRUE:
			case FALSE:
			case SELF:
			case SUPER:
			case OUTER:
			case LBRACKET:
			case LPAREN:
			case BINARY_SELECTOR:
			case IDENTIFIER:
			case KEYWORD:
			case STRING:
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
				expression();
				}
				break;
			case CARET:
				enterOuterAlt(_localctx, 2);
				{
				setState(200);
				returnStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode CARET() { return getToken(NewspeakParser.CARET, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_returnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(CARET);
			setState(204);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ReceiverContext receiver() {
			return getRuleContext(ReceiverContext.class,0);
		}
		public MessageSendContext messageSend() {
			return getRuleContext(MessageSendContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expression);
		try {
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(206);
				receiver();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(207);
				messageSend();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MessageSendContext extends ParserRuleContext {
		public ReceiverlessSendContext receiverlessSend() {
			return getRuleContext(ReceiverlessSendContext.class,0);
		}
		public ReceiverfulSendContext receiverfulSend() {
			return getRuleContext(ReceiverfulSendContext.class,0);
		}
		public MessageSendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_messageSend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterMessageSend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitMessageSend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitMessageSend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageSendContext messageSend() throws RecognitionException {
		MessageSendContext _localctx = new MessageSendContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_messageSend);
		try {
			setState(212);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				receiverlessSend();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(211);
				receiverfulSend();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReceiverlessSendContext extends ParserRuleContext {
		public MessageContext message() {
			return getRuleContext(MessageContext.class,0);
		}
		public ReceiverlessSendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_receiverlessSend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterReceiverlessSend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitReceiverlessSend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitReceiverlessSend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReceiverlessSendContext receiverlessSend() throws RecognitionException {
		ReceiverlessSendContext _localctx = new ReceiverlessSendContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_receiverlessSend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			message();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReceiverfulSendContext extends ParserRuleContext {
		public ReceiverContext receiver() {
			return getRuleContext(ReceiverContext.class,0);
		}
		public MessageContext message() {
			return getRuleContext(MessageContext.class,0);
		}
		public ReceiverfulSendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_receiverfulSend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterReceiverfulSend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitReceiverfulSend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitReceiverfulSend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReceiverfulSendContext receiverfulSend() throws RecognitionException {
		ReceiverfulSendContext _localctx = new ReceiverfulSendContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_receiverfulSend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			receiver();
			setState(217);
			message();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReceiverContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public SpecialReceiverContext specialReceiver() {
			return getRuleContext(SpecialReceiverContext.class,0);
		}
		public ReceiverlessSendContext receiverlessSend() {
			return getRuleContext(ReceiverlessSendContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(NewspeakParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(NewspeakParser.RPAREN, 0); }
		public ReceiverContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_receiver; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterReceiver(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitReceiver(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitReceiver(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReceiverContext receiver() throws RecognitionException {
		ReceiverContext _localctx = new ReceiverContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_receiver);
		try {
			setState(226);
			switch (_input.LA(1)) {
			case LBRACKET:
			case STRING:
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(219);
				literal();
				}
				break;
			case NIL:
			case TRUE:
			case FALSE:
			case SELF:
			case SUPER:
			case OUTER:
				enterOuterAlt(_localctx, 2);
				{
				setState(220);
				specialReceiver();
				}
				break;
			case BINARY_SELECTOR:
			case IDENTIFIER:
			case KEYWORD:
				enterOuterAlt(_localctx, 3);
				{
				setState(221);
				receiverlessSend();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 4);
				{
				setState(222);
				match(LPAREN);
				setState(223);
				expression();
				setState(224);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MessageContext extends ParserRuleContext {
		public UnaryMessageContext unaryMessage() {
			return getRuleContext(UnaryMessageContext.class,0);
		}
		public BinaryMessageContext binaryMessage() {
			return getRuleContext(BinaryMessageContext.class,0);
		}
		public KeywordMessageContext keywordMessage() {
			return getRuleContext(KeywordMessageContext.class,0);
		}
		public MessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_message; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageContext message() throws RecognitionException {
		MessageContext _localctx = new MessageContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_message);
		try {
			setState(231);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				unaryMessage();
				}
				break;
			case BINARY_SELECTOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(229);
				binaryMessage();
				}
				break;
			case KEYWORD:
				enterOuterAlt(_localctx, 3);
				{
				setState(230);
				keywordMessage();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryMessageContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(NewspeakParser.IDENTIFIER, 0); }
		public UnaryMessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryMessage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterUnaryMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitUnaryMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitUnaryMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryMessageContext unaryMessage() throws RecognitionException {
		UnaryMessageContext _localctx = new UnaryMessageContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_unaryMessage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryMessageContext extends ParserRuleContext {
		public TerminalNode BINARY_SELECTOR() { return getToken(NewspeakParser.BINARY_SELECTOR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BinaryMessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryMessage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterBinaryMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitBinaryMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitBinaryMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryMessageContext binaryMessage() throws RecognitionException {
		BinaryMessageContext _localctx = new BinaryMessageContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_binaryMessage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(BINARY_SELECTOR);
			setState(236);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordMessageContext extends ParserRuleContext {
		public List<TerminalNode> KEYWORD() { return getTokens(NewspeakParser.KEYWORD); }
		public TerminalNode KEYWORD(int i) {
			return getToken(NewspeakParser.KEYWORD, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public KeywordMessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keywordMessage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterKeywordMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitKeywordMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitKeywordMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordMessageContext keywordMessage() throws RecognitionException {
		KeywordMessageContext _localctx = new KeywordMessageContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_keywordMessage);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(240); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(238);
					match(KEYWORD);
					setState(239);
					expression();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(242); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpecialReceiverContext extends ParserRuleContext {
		public NilReceiverContext nilReceiver() {
			return getRuleContext(NilReceiverContext.class,0);
		}
		public TrueReceiverContext trueReceiver() {
			return getRuleContext(TrueReceiverContext.class,0);
		}
		public FalseReceiverContext falseReceiver() {
			return getRuleContext(FalseReceiverContext.class,0);
		}
		public SelfReceiverContext selfReceiver() {
			return getRuleContext(SelfReceiverContext.class,0);
		}
		public SuperReceiverContext superReceiver() {
			return getRuleContext(SuperReceiverContext.class,0);
		}
		public OuterReceiverContext outerReceiver() {
			return getRuleContext(OuterReceiverContext.class,0);
		}
		public SpecialReceiverContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specialReceiver; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterSpecialReceiver(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitSpecialReceiver(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitSpecialReceiver(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecialReceiverContext specialReceiver() throws RecognitionException {
		SpecialReceiverContext _localctx = new SpecialReceiverContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_specialReceiver);
		try {
			setState(250);
			switch (_input.LA(1)) {
			case NIL:
				enterOuterAlt(_localctx, 1);
				{
				setState(244);
				nilReceiver();
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
				trueReceiver();
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(246);
				falseReceiver();
				}
				break;
			case SELF:
				enterOuterAlt(_localctx, 4);
				{
				setState(247);
				selfReceiver();
				}
				break;
			case SUPER:
				enterOuterAlt(_localctx, 5);
				{
				setState(248);
				superReceiver();
				}
				break;
			case OUTER:
				enterOuterAlt(_localctx, 6);
				{
				setState(249);
				outerReceiver();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NilReceiverContext extends ParserRuleContext {
		public TerminalNode NIL() { return getToken(NewspeakParser.NIL, 0); }
		public NilReceiverContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nilReceiver; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterNilReceiver(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitNilReceiver(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitNilReceiver(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NilReceiverContext nilReceiver() throws RecognitionException {
		NilReceiverContext _localctx = new NilReceiverContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_nilReceiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(NIL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TrueReceiverContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(NewspeakParser.TRUE, 0); }
		public TrueReceiverContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueReceiver; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterTrueReceiver(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitTrueReceiver(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitTrueReceiver(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueReceiverContext trueReceiver() throws RecognitionException {
		TrueReceiverContext _localctx = new TrueReceiverContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_trueReceiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(TRUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FalseReceiverContext extends ParserRuleContext {
		public TerminalNode FALSE() { return getToken(NewspeakParser.FALSE, 0); }
		public FalseReceiverContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_falseReceiver; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterFalseReceiver(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitFalseReceiver(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitFalseReceiver(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FalseReceiverContext falseReceiver() throws RecognitionException {
		FalseReceiverContext _localctx = new FalseReceiverContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_falseReceiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(FALSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelfReceiverContext extends ParserRuleContext {
		public TerminalNode SELF() { return getToken(NewspeakParser.SELF, 0); }
		public SelfReceiverContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selfReceiver; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterSelfReceiver(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitSelfReceiver(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitSelfReceiver(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelfReceiverContext selfReceiver() throws RecognitionException {
		SelfReceiverContext _localctx = new SelfReceiverContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_selfReceiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			match(SELF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuperReceiverContext extends ParserRuleContext {
		public TerminalNode SUPER() { return getToken(NewspeakParser.SUPER, 0); }
		public SuperReceiverContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superReceiver; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterSuperReceiver(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitSuperReceiver(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitSuperReceiver(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuperReceiverContext superReceiver() throws RecognitionException {
		SuperReceiverContext _localctx = new SuperReceiverContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_superReceiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(SUPER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OuterReceiverContext extends ParserRuleContext {
		public TerminalNode OUTER() { return getToken(NewspeakParser.OUTER, 0); }
		public TerminalNode IDENTIFIER() { return getToken(NewspeakParser.IDENTIFIER, 0); }
		public OuterReceiverContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outerReceiver; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterOuterReceiver(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitOuterReceiver(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitOuterReceiver(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OuterReceiverContext outerReceiver() throws RecognitionException {
		OuterReceiverContext _localctx = new OuterReceiverContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_outerReceiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(OUTER);
			setState(263);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BlockLiteralContext extends LiteralContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterBlockLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitBlockLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitBlockLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringLiteralContext extends LiteralContext {
		public TerminalNode STRING() { return getToken(NewspeakParser.STRING, 0); }
		public StringLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerLiteralContext extends LiteralContext {
		public TerminalNode INTEGER() { return getToken(NewspeakParser.INTEGER, 0); }
		public IntegerLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitIntegerLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_literal);
		try {
			setState(268);
			switch (_input.LA(1)) {
			case LBRACKET:
				_localctx = new BlockLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(265);
				block();
				}
				break;
			case INTEGER:
				_localctx = new IntegerLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(266);
				match(INTEGER);
				}
				break;
			case STRING:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(267);
				match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(NewspeakParser.LBRACKET, 0); }
		public CodeBodyContext codeBody() {
			return getRuleContext(CodeBodyContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(NewspeakParser.RBRACKET, 0); }
		public BlockArgsContext blockArgs() {
			return getRuleContext(BlockArgsContext.class,0);
		}
		public BlockTempsContext blockTemps() {
			return getRuleContext(BlockTempsContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			match(LBRACKET);
			setState(272);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(271);
				blockArgs();
				}
				break;
			}
			setState(275);
			_la = _input.LA(1);
			if (_la==VBAR) {
				{
				setState(274);
				blockTemps();
				}
			}

			setState(277);
			codeBody();
			setState(278);
			match(RBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockArgsContext extends ParserRuleContext {
		public TerminalNode VBAR() { return getToken(NewspeakParser.VBAR, 0); }
		public List<TerminalNode> BLOCK_ARG() { return getTokens(NewspeakParser.BLOCK_ARG); }
		public TerminalNode BLOCK_ARG(int i) {
			return getToken(NewspeakParser.BLOCK_ARG, i);
		}
		public BlockArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterBlockArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitBlockArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitBlockArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockArgsContext blockArgs() throws RecognitionException {
		BlockArgsContext _localctx = new BlockArgsContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_blockArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BLOCK_ARG) {
				{
				{
				setState(280);
				match(BLOCK_ARG);
				}
				}
				setState(285);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(286);
			match(VBAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockTempsContext extends ParserRuleContext {
		public List<TerminalNode> VBAR() { return getTokens(NewspeakParser.VBAR); }
		public TerminalNode VBAR(int i) {
			return getToken(NewspeakParser.VBAR, i);
		}
		public List<SlotDeclContext> slotDecl() {
			return getRuleContexts(SlotDeclContext.class);
		}
		public SlotDeclContext slotDecl(int i) {
			return getRuleContext(SlotDeclContext.class,i);
		}
		public BlockTempsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockTemps; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterBlockTemps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitBlockTemps(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitBlockTemps(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockTempsContext blockTemps() throws RecognitionException {
		BlockTempsContext _localctx = new BlockTempsContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_blockTemps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			match(VBAR);
			setState(292);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(289);
				slotDecl();
				}
				}
				setState(294);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(295);
			match(VBAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3&\u012c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\3\3\3\3\3\5\3Q\n\3\3\3\3\3"+
		"\3\3\5\3V\n\3\3\3\3\3\3\4\3\4\3\4\5\4]\n\4\3\5\3\5\3\5\7\5b\n\5\f\5\16"+
		"\5e\13\5\3\5\5\5h\n\5\3\5\3\5\3\6\3\6\7\6n\n\6\f\6\16\6q\13\6\3\6\7\6"+
		"t\n\6\f\6\16\6w\13\6\3\6\3\6\3\7\3\7\3\7\7\7~\n\7\f\7\16\7\u0081\13\7"+
		"\3\7\3\7\3\b\3\b\7\b\u0087\n\b\f\b\16\b\u008a\13\b\3\t\5\t\u008d\n\t\3"+
		"\t\3\t\3\t\5\t\u0092\n\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\5\f\u009d"+
		"\n\f\3\f\3\f\3\f\3\f\3\f\7\f\u00a4\n\f\f\f\16\f\u00a7\13\f\3\f\5\f\u00aa"+
		"\n\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\6\16\u00b6\n\16\r\16"+
		"\16\16\u00b7\5\16\u00ba\n\16\3\17\3\17\3\17\3\17\7\17\u00c0\n\17\f\17"+
		"\16\17\u00c3\13\17\3\17\5\17\u00c6\n\17\5\17\u00c8\n\17\3\20\3\20\5\20"+
		"\u00cc\n\20\3\21\3\21\3\21\3\22\3\22\5\22\u00d3\n\22\3\23\3\23\5\23\u00d7"+
		"\n\23\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26"+
		"\u00e5\n\26\3\27\3\27\3\27\5\27\u00ea\n\27\3\30\3\30\3\31\3\31\3\31\3"+
		"\32\3\32\6\32\u00f3\n\32\r\32\16\32\u00f4\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\5\33\u00fd\n\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3"+
		"!\3\"\3\"\3\"\5\"\u010f\n\"\3#\3#\5#\u0113\n#\3#\5#\u0116\n#\3#\3#\3#"+
		"\3$\7$\u011c\n$\f$\16$\u011f\13$\3$\3$\3%\3%\7%\u0125\n%\f%\16%\u0128"+
		"\13%\3%\3%\3%\2\2&\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:<>@BDFH\2\3\3\2\4\6\u0130\2J\3\2\2\2\4M\3\2\2\2\6Y\3\2\2\2"+
		"\b^\3\2\2\2\nk\3\2\2\2\fz\3\2\2\2\16\u0084\3\2\2\2\20\u008c\3\2\2\2\22"+
		"\u0093\3\2\2\2\24\u0097\3\2\2\2\26\u009c\3\2\2\2\30\u00ae\3\2\2\2\32\u00b9"+
		"\3\2\2\2\34\u00c7\3\2\2\2\36\u00cb\3\2\2\2 \u00cd\3\2\2\2\"\u00d2\3\2"+
		"\2\2$\u00d6\3\2\2\2&\u00d8\3\2\2\2(\u00da\3\2\2\2*\u00e4\3\2\2\2,\u00e9"+
		"\3\2\2\2.\u00eb\3\2\2\2\60\u00ed\3\2\2\2\62\u00f2\3\2\2\2\64\u00fc\3\2"+
		"\2\2\66\u00fe\3\2\2\28\u0100\3\2\2\2:\u0102\3\2\2\2<\u0104\3\2\2\2>\u0106"+
		"\3\2\2\2@\u0108\3\2\2\2B\u010e\3\2\2\2D\u0110\3\2\2\2F\u011d\3\2\2\2H"+
		"\u0122\3\2\2\2JK\5\4\3\2KL\7\2\2\3L\3\3\2\2\2MN\7\3\2\2NP\7\37\2\2OQ\5"+
		"\32\16\2PO\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RU\7\21\2\2ST\7\37\2\2TV\5\32\16"+
		"\2US\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\5\6\4\2X\5\3\2\2\2YZ\5\b\5\2Z\\\5\n"+
		"\6\2[]\5\f\7\2\\[\3\2\2\2\\]\3\2\2\2]\7\3\2\2\2^g\7\25\2\2_c\7\35\2\2"+
		"`b\5\20\t\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2df\3\2\2\2ec\3\2\2"+
		"\2fh\7\35\2\2g_\3\2\2\2gh\3\2\2\2hi\3\2\2\2ij\7\33\2\2j\t\3\2\2\2ko\7"+
		"\25\2\2ln\5\4\3\2ml\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2\2pu\3\2\2\2qo"+
		"\3\2\2\2rt\5\16\b\2sr\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2vx\3\2\2\2"+
		"wu\3\2\2\2xy\7\33\2\2y\13\3\2\2\2z{\7\16\2\2{\177\7\25\2\2|~\5\16\b\2"+
		"}|\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0082\3"+
		"\2\2\2\u0081\177\3\2\2\2\u0082\u0083\7\33\2\2\u0083\r\3\2\2\2\u0084\u0088"+
		"\7#\2\2\u0085\u0087\5\26\f\2\u0086\u0085\3\2\2\2\u0087\u008a\3\2\2\2\u0088"+
		"\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\17\3\2\2\2\u008a\u0088\3\2\2"+
		"\2\u008b\u008d\5\30\r\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"\u008e\3\2\2\2\u008e\u0091\7\37\2\2\u008f\u0092\5\22\n\2\u0090\u0092\5"+
		"\24\13\2\u0091\u008f\3\2\2\2\u0091\u0090\3\2\2\2\u0091\u0092\3\2\2\2\u0092"+
		"\21\3\2\2\2\u0093\u0094\7\22\2\2\u0094\u0095\5\"\22\2\u0095\u0096\7\20"+
		"\2\2\u0096\23\3\2\2\2\u0097\u0098\7\21\2\2\u0098\u0099\5\"\22\2\u0099"+
		"\u009a\7\20\2\2\u009a\25\3\2\2\2\u009b\u009d\5\30\r\2\u009c\u009b\3\2"+
		"\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\5\32\16\2\u009f"+
		"\u00a0\7\21\2\2\u00a0\u00a9\7\25\2\2\u00a1\u00a5\7\35\2\2\u00a2\u00a4"+
		"\5\20\t\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2"+
		"\u00a5\u00a6\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00aa"+
		"\7\35\2\2\u00a9\u00a1\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2\2\2"+
		"\u00ab\u00ac\5\34\17\2\u00ac\u00ad\7\33\2\2\u00ad\27\3\2\2\2\u00ae\u00af"+
		"\t\2\2\2\u00af\31\3\2\2\2\u00b0\u00ba\7\37\2\2\u00b1\u00b2\7\36\2\2\u00b2"+
		"\u00ba\7\37\2\2\u00b3\u00b4\7 \2\2\u00b4\u00b6\7\37\2\2\u00b5\u00b3\3"+
		"\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8"+
		"\u00ba\3\2\2\2\u00b9\u00b0\3\2\2\2\u00b9\u00b1\3\2\2\2\u00b9\u00b5\3\2"+
		"\2\2\u00ba\33\3\2\2\2\u00bb\u00c8\3\2\2\2\u00bc\u00c1\5\36\20\2\u00bd"+
		"\u00be\7\20\2\2\u00be\u00c0\5\36\20\2\u00bf\u00bd\3\2\2\2\u00c0\u00c3"+
		"\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3"+
		"\u00c1\3\2\2\2\u00c4\u00c6\7\20\2\2\u00c5\u00c4\3\2\2\2\u00c5\u00c6\3"+
		"\2\2\2\u00c6\u00c8\3\2\2\2\u00c7\u00bb\3\2\2\2\u00c7\u00bc\3\2\2\2\u00c8"+
		"\35\3\2\2\2\u00c9\u00cc\5\"\22\2\u00ca\u00cc\5 \21\2\u00cb\u00c9\3\2\2"+
		"\2\u00cb\u00ca\3\2\2\2\u00cc\37\3\2\2\2\u00cd\u00ce\7\r\2\2\u00ce\u00cf"+
		"\5\"\22\2\u00cf!\3\2\2\2\u00d0\u00d3\5*\26\2\u00d1\u00d3\5$\23\2\u00d2"+
		"\u00d0\3\2\2\2\u00d2\u00d1\3\2\2\2\u00d3#\3\2\2\2\u00d4\u00d7\5&\24\2"+
		"\u00d5\u00d7\5(\25\2\u00d6\u00d4\3\2\2\2\u00d6\u00d5\3\2\2\2\u00d7%\3"+
		"\2\2\2\u00d8\u00d9\5,\27\2\u00d9\'\3\2\2\2\u00da\u00db\5*\26\2\u00db\u00dc"+
		"\5,\27\2\u00dc)\3\2\2\2\u00dd\u00e5\5B\"\2\u00de\u00e5\5\64\33\2\u00df"+
		"\u00e5\5&\24\2\u00e0\u00e1\7\25\2\2\u00e1\u00e2\5\"\22\2\u00e2\u00e3\7"+
		"\33\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00dd\3\2\2\2\u00e4\u00de\3\2\2\2\u00e4"+
		"\u00df\3\2\2\2\u00e4\u00e0\3\2\2\2\u00e5+\3\2\2\2\u00e6\u00ea\5.\30\2"+
		"\u00e7\u00ea\5\60\31\2\u00e8\u00ea\5\62\32\2\u00e9\u00e6\3\2\2\2\u00e9"+
		"\u00e7\3\2\2\2\u00e9\u00e8\3\2\2\2\u00ea-\3\2\2\2\u00eb\u00ec\7\37\2\2"+
		"\u00ec/\3\2\2\2\u00ed\u00ee\7\36\2\2\u00ee\u00ef\5\"\22\2\u00ef\61\3\2"+
		"\2\2\u00f0\u00f1\7 \2\2\u00f1\u00f3\5\"\22\2\u00f2\u00f0\3\2\2\2\u00f3"+
		"\u00f4\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\63\3\2\2"+
		"\2\u00f6\u00fd\5\66\34\2\u00f7\u00fd\58\35\2\u00f8\u00fd\5:\36\2\u00f9"+
		"\u00fd\5<\37\2\u00fa\u00fd\5> \2\u00fb\u00fd\5@!\2\u00fc\u00f6\3\2\2\2"+
		"\u00fc\u00f7\3\2\2\2\u00fc\u00f8\3\2\2\2\u00fc\u00f9\3\2\2\2\u00fc\u00fa"+
		"\3\2\2\2\u00fc\u00fb\3\2\2\2\u00fd\65\3\2\2\2\u00fe\u00ff\7\7\2\2\u00ff"+
		"\67\3\2\2\2\u0100\u0101\7\b\2\2\u01019\3\2\2\2\u0102\u0103\7\t\2\2\u0103"+
		";\3\2\2\2\u0104\u0105\7\n\2\2\u0105=\3\2\2\2\u0106\u0107\7\13\2\2\u0107"+
		"?\3\2\2\2\u0108\u0109\7\f\2\2\u0109\u010a\7\37\2\2\u010aA\3\2\2\2\u010b"+
		"\u010f\5D#\2\u010c\u010f\7$\2\2\u010d\u010f\7#\2\2\u010e\u010b\3\2\2\2"+
		"\u010e\u010c\3\2\2\2\u010e\u010d\3\2\2\2\u010fC\3\2\2\2\u0110\u0112\7"+
		"\23\2\2\u0111\u0113\5F$\2\u0112\u0111\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"\u0115\3\2\2\2\u0114\u0116\5H%\2\u0115\u0114\3\2\2\2\u0115\u0116\3\2\2"+
		"\2\u0116\u0117\3\2\2\2\u0117\u0118\5\34\17\2\u0118\u0119\7\31\2\2\u0119"+
		"E\3\2\2\2\u011a\u011c\7\"\2\2\u011b\u011a\3\2\2\2\u011c\u011f\3\2\2\2"+
		"\u011d\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u0120\3\2\2\2\u011f\u011d"+
		"\3\2\2\2\u0120\u0121\7\35\2\2\u0121G\3\2\2\2\u0122\u0126\7\35\2\2\u0123"+
		"\u0125\5\20\t\2\u0124\u0123\3\2\2\2\u0125\u0128\3\2\2\2\u0126\u0124\3"+
		"\2\2\2\u0126\u0127\3\2\2\2\u0127\u0129\3\2\2\2\u0128\u0126\3\2\2\2\u0129"+
		"\u012a\7\35\2\2\u012aI\3\2\2\2!PU\\cgou\177\u0088\u008c\u0091\u009c\u00a5"+
		"\u00a9\u00b7\u00b9\u00c1\u00c5\u00c7\u00cb\u00d2\u00d6\u00e4\u00e9\u00f4"+
		"\u00fc\u010e\u0112\u0115\u011d\u0126";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}