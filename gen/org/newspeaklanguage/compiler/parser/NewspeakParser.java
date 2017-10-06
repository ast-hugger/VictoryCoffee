// Generated from /home/vassili/Projects/VictoryCoffee/src/org/newspeaklanguage/compiler/parser/Newspeak.g4 by ANTLR 4.7
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
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, NIL=5, TRUE=6, FALSE=7, SELF=8, SUPER=9, 
		OUTER=10, CARET=11, COLON=12, COMMA=13, DOT=14, EQUAL_SIGN=15, CCE_SIGN=16, 
		LBRACKET=17, LCURLY=18, LPAREN=19, POUND=20, RBRACKET=21, RCURLY=22, RPAREN=23, 
		SEMICOLON=24, VBAR=25, BINARY_SELECTOR=26, IDENTIFIER=27, KEYWORD=28, 
		SETTER_KEYWORD=29, BLOCK_ARG=30, STRING=31, INTEGER=32, COMMENT=33, WHITESPACE=34;
	public static final int
		RULE_sourceUnit = 0, RULE_classDecl = 1, RULE_classBody = 2, RULE_classHeader = 3, 
		RULE_instanceSideDecl = 4, RULE_classSideDecl = 5, RULE_category = 6, 
		RULE_slotDecl = 7, RULE_mutableSlotInitializer = 8, RULE_immutableSlotInitializer = 9, 
		RULE_methodDecl = 10, RULE_accessModifier = 11, RULE_messagePattern = 12, 
		RULE_codeBody = 13, RULE_statement = 14, RULE_returnStatement = 15, RULE_expression = 16, 
		RULE_messageSend = 17, RULE_receiver = 18, RULE_binaryObject = 19, RULE_keywordObject = 20, 
		RULE_setterSend = 21, RULE_unarySend = 22, RULE_unaryMessage = 23, RULE_binarySend = 24, 
		RULE_binaryMessage = 25, RULE_keywordSend = 26, RULE_keywordMessage = 27, 
		RULE_specialReceiver = 28, RULE_nilReceiver = 29, RULE_trueReceiver = 30, 
		RULE_falseReceiver = 31, RULE_selfReceiver = 32, RULE_superReceiver = 33, 
		RULE_outerReceiver = 34, RULE_literal = 35, RULE_block = 36, RULE_blockArgs = 37, 
		RULE_blockTemps = 38;
	public static final String[] ruleNames = {
		"sourceUnit", "classDecl", "classBody", "classHeader", "instanceSideDecl", 
		"classSideDecl", "category", "slotDecl", "mutableSlotInitializer", "immutableSlotInitializer", 
		"methodDecl", "accessModifier", "messagePattern", "codeBody", "statement", 
		"returnStatement", "expression", "messageSend", "receiver", "binaryObject", 
		"keywordObject", "setterSend", "unarySend", "unaryMessage", "binarySend", 
		"binaryMessage", "keywordSend", "keywordMessage", "specialReceiver", "nilReceiver", 
		"trueReceiver", "falseReceiver", "selfReceiver", "superReceiver", "outerReceiver", 
		"literal", "block", "blockArgs", "blockTemps"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'class'", "'public'", "'protected'", "'private'", "'nil'", "'true'", 
		"'false'", "'self'", "'super'", "'outer'", "'^'", "':'", "','", "'.'", 
		"'='", "'::='", "'['", "'{'", "'('", "'#'", "']'", "'}'", "')'", "';'", 
		"'|'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "NIL", "TRUE", "FALSE", "SELF", "SUPER", 
		"OUTER", "CARET", "COLON", "COMMA", "DOT", "EQUAL_SIGN", "CCE_SIGN", "LBRACKET", 
		"LCURLY", "LPAREN", "POUND", "RBRACKET", "RCURLY", "RPAREN", "SEMICOLON", 
		"VBAR", "BINARY_SELECTOR", "IDENTIFIER", "KEYWORD", "SETTER_KEYWORD", 
		"BLOCK_ARG", "STRING", "INTEGER", "COMMENT", "WHITESPACE"
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
			setState(78);
			classDecl();
			setState(79);
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
			setState(81);
			match(T__0);
			setState(82);
			match(IDENTIFIER);
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BINARY_SELECTOR) | (1L << IDENTIFIER) | (1L << KEYWORD))) != 0)) {
				{
				setState(83);
				messagePattern();
				}
			}

			setState(86);
			match(EQUAL_SIGN);
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(87);
				match(IDENTIFIER);
				setState(88);
				messagePattern();
				}
			}

			setState(91);
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
			setState(93);
			classHeader();
			setState(94);
			instanceSideDecl();
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(95);
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
			setState(98);
			match(LPAREN);
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VBAR) {
				{
				setState(99);
				match(VBAR);
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << IDENTIFIER))) != 0)) {
					{
					{
					setState(100);
					slotDecl();
					}
					}
					setState(105);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(106);
				match(VBAR);
				}
			}

			setState(109);
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
			setState(111);
			match(LPAREN);
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(112);
				classDecl();
				}
				}
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(118);
				category();
				}
				}
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(124);
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
			setState(126);
			match(COLON);
			setState(127);
			match(LPAREN);
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(128);
				category();
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(134);
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
			setState(136);
			match(STRING);
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << BINARY_SELECTOR) | (1L << IDENTIFIER) | (1L << KEYWORD))) != 0)) {
				{
				{
				setState(137);
				methodDecl();
				}
				}
				setState(142);
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
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3))) != 0)) {
				{
				setState(143);
				accessModifier();
				}
			}

			setState(146);
			match(IDENTIFIER);
			setState(149);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CCE_SIGN:
				{
				setState(147);
				mutableSlotInitializer();
				}
				break;
			case EQUAL_SIGN:
				{
				setState(148);
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
				break;
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
			setState(151);
			match(CCE_SIGN);
			setState(152);
			expression();
			setState(153);
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
			setState(155);
			match(EQUAL_SIGN);
			setState(156);
			expression();
			setState(157);
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
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3))) != 0)) {
				{
				setState(159);
				accessModifier();
				}
			}

			setState(162);
			messagePattern();
			setState(163);
			match(EQUAL_SIGN);
			setState(164);
			match(LPAREN);
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VBAR) {
				{
				setState(165);
				match(VBAR);
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << IDENTIFIER))) != 0)) {
					{
					{
					setState(166);
					slotDecl();
					}
					}
					setState(171);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(172);
				match(VBAR);
				}
			}

			setState(175);
			codeBody();
			setState(176);
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
			setState(178);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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
			setState(189);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				_localctx = new UnaryPatternContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(180);
				match(IDENTIFIER);
				}
				break;
			case BINARY_SELECTOR:
				_localctx = new BinaryPatternContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(181);
				match(BINARY_SELECTOR);
				setState(182);
				match(IDENTIFIER);
				}
				break;
			case KEYWORD:
				_localctx = new KeywordPatternContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(185); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(183);
					match(KEYWORD);
					setState(184);
					match(IDENTIFIER);
					}
					}
					setState(187); 
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
			setState(203);
			_errHandler.sync(this);
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
			case IDENTIFIER:
			case KEYWORD:
			case SETTER_KEYWORD:
			case STRING:
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(192);
				statement();
				setState(197);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(193);
						match(DOT);
						setState(194);
						statement();
						}
						} 
					}
					setState(199);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				}
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DOT) {
					{
					setState(200);
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
			setState(207);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NIL:
			case TRUE:
			case FALSE:
			case SELF:
			case SUPER:
			case OUTER:
			case LBRACKET:
			case LPAREN:
			case IDENTIFIER:
			case KEYWORD:
			case SETTER_KEYWORD:
			case STRING:
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(205);
				expression();
				}
				break;
			case CARET:
				enterOuterAlt(_localctx, 2);
				{
				setState(206);
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
			setState(209);
			match(CARET);
			setState(210);
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
		public SetterSendContext setterSend() {
			return getRuleContext(SetterSendContext.class,0);
		}
		public KeywordMessageContext keywordMessage() {
			return getRuleContext(KeywordMessageContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(NewspeakParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(NewspeakParser.RPAREN, 0); }
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
			setState(220);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(212);
				receiver();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(213);
				messageSend();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(214);
				setterSend();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(215);
				keywordMessage();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(216);
				match(LPAREN);
				setState(217);
				expression();
				setState(218);
				match(RPAREN);
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
		public UnarySendContext unarySend() {
			return getRuleContext(UnarySendContext.class,0);
		}
		public BinarySendContext binarySend() {
			return getRuleContext(BinarySendContext.class,0);
		}
		public KeywordSendContext keywordSend() {
			return getRuleContext(KeywordSendContext.class,0);
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
			setState(225);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(222);
				unarySend();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(223);
				binarySend();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(224);
				keywordSend();
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

	public static class ReceiverContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(NewspeakParser.IDENTIFIER, 0); }
		public SpecialReceiverContext specialReceiver() {
			return getRuleContext(SpecialReceiverContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
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
		enterRule(_localctx, 36, RULE_receiver);
		try {
			setState(234);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(227);
				match(IDENTIFIER);
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
				setState(228);
				specialReceiver();
				}
				break;
			case LBRACKET:
			case STRING:
			case INTEGER:
				enterOuterAlt(_localctx, 3);
				{
				setState(229);
				literal();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 4);
				{
				setState(230);
				match(LPAREN);
				setState(231);
				expression();
				setState(232);
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

	public static class BinaryObjectContext extends ParserRuleContext {
		public ReceiverContext receiver() {
			return getRuleContext(ReceiverContext.class,0);
		}
		public UnarySendContext unarySend() {
			return getRuleContext(UnarySendContext.class,0);
		}
		public BinaryObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryObject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterBinaryObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitBinaryObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitBinaryObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryObjectContext binaryObject() throws RecognitionException {
		BinaryObjectContext _localctx = new BinaryObjectContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_binaryObject);
		try {
			setState(238);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(236);
				receiver();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(237);
				unarySend();
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

	public static class KeywordObjectContext extends ParserRuleContext {
		public ReceiverContext receiver() {
			return getRuleContext(ReceiverContext.class,0);
		}
		public UnarySendContext unarySend() {
			return getRuleContext(UnarySendContext.class,0);
		}
		public BinarySendContext binarySend() {
			return getRuleContext(BinarySendContext.class,0);
		}
		public KeywordObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keywordObject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterKeywordObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitKeywordObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitKeywordObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordObjectContext keywordObject() throws RecognitionException {
		KeywordObjectContext _localctx = new KeywordObjectContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_keywordObject);
		try {
			setState(243);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(240);
				receiver();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(241);
				unarySend();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(242);
				binarySend();
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

	public static class SetterSendContext extends ParserRuleContext {
		public TerminalNode SETTER_KEYWORD() { return getToken(NewspeakParser.SETTER_KEYWORD, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SetterSendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setterSend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterSetterSend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitSetterSend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitSetterSend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetterSendContext setterSend() throws RecognitionException {
		SetterSendContext _localctx = new SetterSendContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_setterSend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(SETTER_KEYWORD);
			setState(246);
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

	public static class UnarySendContext extends ParserRuleContext {
		public ReceiverContext receiver() {
			return getRuleContext(ReceiverContext.class,0);
		}
		public UnaryMessageContext unaryMessage() {
			return getRuleContext(UnaryMessageContext.class,0);
		}
		public UnarySendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unarySend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterUnarySend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitUnarySend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitUnarySend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnarySendContext unarySend() throws RecognitionException {
		UnarySendContext _localctx = new UnarySendContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_unarySend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			receiver();
			setState(249);
			unaryMessage();
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
		public UnaryMessageContext unaryMessage() {
			return getRuleContext(UnaryMessageContext.class,0);
		}
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
		enterRule(_localctx, 46, RULE_unaryMessage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(IDENTIFIER);
			setState(253);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(252);
				unaryMessage();
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

	public static class BinarySendContext extends ParserRuleContext {
		public BinaryObjectContext binaryObject() {
			return getRuleContext(BinaryObjectContext.class,0);
		}
		public BinaryMessageContext binaryMessage() {
			return getRuleContext(BinaryMessageContext.class,0);
		}
		public BinarySendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binarySend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterBinarySend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitBinarySend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitBinarySend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinarySendContext binarySend() throws RecognitionException {
		BinarySendContext _localctx = new BinarySendContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_binarySend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			binaryObject();
			setState(256);
			binaryMessage();
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
		public BinaryObjectContext binaryObject() {
			return getRuleContext(BinaryObjectContext.class,0);
		}
		public BinaryMessageContext binaryMessage() {
			return getRuleContext(BinaryMessageContext.class,0);
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
		enterRule(_localctx, 50, RULE_binaryMessage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			match(BINARY_SELECTOR);
			setState(259);
			binaryObject();
			setState(261);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BINARY_SELECTOR) {
				{
				setState(260);
				binaryMessage();
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

	public static class KeywordSendContext extends ParserRuleContext {
		public KeywordObjectContext keywordObject() {
			return getRuleContext(KeywordObjectContext.class,0);
		}
		public KeywordMessageContext keywordMessage() {
			return getRuleContext(KeywordMessageContext.class,0);
		}
		public KeywordSendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keywordSend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterKeywordSend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitKeywordSend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitKeywordSend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordSendContext keywordSend() throws RecognitionException {
		KeywordSendContext _localctx = new KeywordSendContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_keywordSend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			keywordObject();
			setState(264);
			keywordMessage();
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
		public List<KeywordObjectContext> keywordObject() {
			return getRuleContexts(KeywordObjectContext.class);
		}
		public KeywordObjectContext keywordObject(int i) {
			return getRuleContext(KeywordObjectContext.class,i);
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
		enterRule(_localctx, 54, RULE_keywordMessage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(266);
				match(KEYWORD);
				setState(267);
				keywordObject();
				}
				}
				setState(270); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==KEYWORD );
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
		enterRule(_localctx, 56, RULE_specialReceiver);
		try {
			setState(278);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NIL:
				enterOuterAlt(_localctx, 1);
				{
				setState(272);
				nilReceiver();
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(273);
				trueReceiver();
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(274);
				falseReceiver();
				}
				break;
			case SELF:
				enterOuterAlt(_localctx, 4);
				{
				setState(275);
				selfReceiver();
				}
				break;
			case SUPER:
				enterOuterAlt(_localctx, 5);
				{
				setState(276);
				superReceiver();
				}
				break;
			case OUTER:
				enterOuterAlt(_localctx, 6);
				{
				setState(277);
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
		enterRule(_localctx, 58, RULE_nilReceiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
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
		enterRule(_localctx, 60, RULE_trueReceiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
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
		enterRule(_localctx, 62, RULE_falseReceiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
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
		enterRule(_localctx, 64, RULE_selfReceiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
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
		enterRule(_localctx, 66, RULE_superReceiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
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
		enterRule(_localctx, 68, RULE_outerReceiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			match(OUTER);
			setState(291);
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
		enterRule(_localctx, 70, RULE_literal);
		try {
			setState(296);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACKET:
				_localctx = new BlockLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(293);
				block();
				}
				break;
			case INTEGER:
				_localctx = new IntegerLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(294);
				match(INTEGER);
				}
				break;
			case STRING:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(295);
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
		enterRule(_localctx, 72, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			match(LBRACKET);
			setState(300);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(299);
				blockArgs();
				}
				break;
			}
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VBAR) {
				{
				setState(302);
				blockTemps();
				}
			}

			setState(305);
			codeBody();
			setState(306);
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
		enterRule(_localctx, 74, RULE_blockArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BLOCK_ARG) {
				{
				{
				setState(308);
				match(BLOCK_ARG);
				}
				}
				setState(313);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(314);
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
		enterRule(_localctx, 76, RULE_blockTemps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			match(VBAR);
			setState(320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(317);
				slotDecl();
				}
				}
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(323);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3$\u0148\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\5\3W\n\3\3\3\3\3\3\3\5\3\\\n\3\3\3\3\3\3\4\3\4\3\4\5\4c\n\4\3\5\3"+
		"\5\3\5\7\5h\n\5\f\5\16\5k\13\5\3\5\5\5n\n\5\3\5\3\5\3\6\3\6\7\6t\n\6\f"+
		"\6\16\6w\13\6\3\6\7\6z\n\6\f\6\16\6}\13\6\3\6\3\6\3\7\3\7\3\7\7\7\u0084"+
		"\n\7\f\7\16\7\u0087\13\7\3\7\3\7\3\b\3\b\7\b\u008d\n\b\f\b\16\b\u0090"+
		"\13\b\3\t\5\t\u0093\n\t\3\t\3\t\3\t\5\t\u0098\n\t\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\f\5\f\u00a3\n\f\3\f\3\f\3\f\3\f\3\f\7\f\u00aa\n\f\f"+
		"\f\16\f\u00ad\13\f\3\f\5\f\u00b0\n\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\6\16\u00bc\n\16\r\16\16\16\u00bd\5\16\u00c0\n\16\3\17\3\17"+
		"\3\17\3\17\7\17\u00c6\n\17\f\17\16\17\u00c9\13\17\3\17\5\17\u00cc\n\17"+
		"\5\17\u00ce\n\17\3\20\3\20\5\20\u00d2\n\20\3\21\3\21\3\21\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\5\22\u00df\n\22\3\23\3\23\3\23\5\23\u00e4"+
		"\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u00ed\n\24\3\25\3\25\5\25"+
		"\u00f1\n\25\3\26\3\26\3\26\5\26\u00f6\n\26\3\27\3\27\3\27\3\30\3\30\3"+
		"\30\3\31\3\31\5\31\u0100\n\31\3\32\3\32\3\32\3\33\3\33\3\33\5\33\u0108"+
		"\n\33\3\34\3\34\3\34\3\35\3\35\6\35\u010f\n\35\r\35\16\35\u0110\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\5\36\u0119\n\36\3\37\3\37\3 \3 \3!\3!\3\"\3\""+
		"\3#\3#\3$\3$\3$\3%\3%\3%\5%\u012b\n%\3&\3&\5&\u012f\n&\3&\5&\u0132\n&"+
		"\3&\3&\3&\3\'\7\'\u0138\n\'\f\'\16\'\u013b\13\'\3\'\3\'\3(\3(\7(\u0141"+
		"\n(\f(\16(\u0144\13(\3(\3(\3(\2\2)\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\64\668:<>@BDFHJLN\2\3\3\2\4\6\2\u0150\2P\3\2\2\2\4"+
		"S\3\2\2\2\6_\3\2\2\2\bd\3\2\2\2\nq\3\2\2\2\f\u0080\3\2\2\2\16\u008a\3"+
		"\2\2\2\20\u0092\3\2\2\2\22\u0099\3\2\2\2\24\u009d\3\2\2\2\26\u00a2\3\2"+
		"\2\2\30\u00b4\3\2\2\2\32\u00bf\3\2\2\2\34\u00cd\3\2\2\2\36\u00d1\3\2\2"+
		"\2 \u00d3\3\2\2\2\"\u00de\3\2\2\2$\u00e3\3\2\2\2&\u00ec\3\2\2\2(\u00f0"+
		"\3\2\2\2*\u00f5\3\2\2\2,\u00f7\3\2\2\2.\u00fa\3\2\2\2\60\u00fd\3\2\2\2"+
		"\62\u0101\3\2\2\2\64\u0104\3\2\2\2\66\u0109\3\2\2\28\u010e\3\2\2\2:\u0118"+
		"\3\2\2\2<\u011a\3\2\2\2>\u011c\3\2\2\2@\u011e\3\2\2\2B\u0120\3\2\2\2D"+
		"\u0122\3\2\2\2F\u0124\3\2\2\2H\u012a\3\2\2\2J\u012c\3\2\2\2L\u0139\3\2"+
		"\2\2N\u013e\3\2\2\2PQ\5\4\3\2QR\7\2\2\3R\3\3\2\2\2ST\7\3\2\2TV\7\35\2"+
		"\2UW\5\32\16\2VU\3\2\2\2VW\3\2\2\2WX\3\2\2\2X[\7\21\2\2YZ\7\35\2\2Z\\"+
		"\5\32\16\2[Y\3\2\2\2[\\\3\2\2\2\\]\3\2\2\2]^\5\6\4\2^\5\3\2\2\2_`\5\b"+
		"\5\2`b\5\n\6\2ac\5\f\7\2ba\3\2\2\2bc\3\2\2\2c\7\3\2\2\2dm\7\25\2\2ei\7"+
		"\33\2\2fh\5\20\t\2gf\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2jl\3\2\2\2k"+
		"i\3\2\2\2ln\7\33\2\2me\3\2\2\2mn\3\2\2\2no\3\2\2\2op\7\31\2\2p\t\3\2\2"+
		"\2qu\7\25\2\2rt\5\4\3\2sr\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2v{\3\2"+
		"\2\2wu\3\2\2\2xz\5\16\b\2yx\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|~\3"+
		"\2\2\2}{\3\2\2\2~\177\7\31\2\2\177\13\3\2\2\2\u0080\u0081\7\16\2\2\u0081"+
		"\u0085\7\25\2\2\u0082\u0084\5\16\b\2\u0083\u0082\3\2\2\2\u0084\u0087\3"+
		"\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0088\u0089\7\31\2\2\u0089\r\3\2\2\2\u008a\u008e\7!\2\2"+
		"\u008b\u008d\5\26\f\2\u008c\u008b\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c"+
		"\3\2\2\2\u008e\u008f\3\2\2\2\u008f\17\3\2\2\2\u0090\u008e\3\2\2\2\u0091"+
		"\u0093\5\30\r\2\u0092\u0091\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\3"+
		"\2\2\2\u0094\u0097\7\35\2\2\u0095\u0098\5\22\n\2\u0096\u0098\5\24\13\2"+
		"\u0097\u0095\3\2\2\2\u0097\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098\21"+
		"\3\2\2\2\u0099\u009a\7\22\2\2\u009a\u009b\5\"\22\2\u009b\u009c\7\20\2"+
		"\2\u009c\23\3\2\2\2\u009d\u009e\7\21\2\2\u009e\u009f\5\"\22\2\u009f\u00a0"+
		"\7\20\2\2\u00a0\25\3\2\2\2\u00a1\u00a3\5\30\r\2\u00a2\u00a1\3\2\2\2\u00a2"+
		"\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\5\32\16\2\u00a5\u00a6\7"+
		"\21\2\2\u00a6\u00af\7\25\2\2\u00a7\u00ab\7\33\2\2\u00a8\u00aa\5\20\t\2"+
		"\u00a9\u00a8\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac"+
		"\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00b0\7\33\2\2"+
		"\u00af\u00a7\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2"+
		"\5\34\17\2\u00b2\u00b3\7\31\2\2\u00b3\27\3\2\2\2\u00b4\u00b5\t\2\2\2\u00b5"+
		"\31\3\2\2\2\u00b6\u00c0\7\35\2\2\u00b7\u00b8\7\34\2\2\u00b8\u00c0\7\35"+
		"\2\2\u00b9\u00ba\7\36\2\2\u00ba\u00bc\7\35\2\2\u00bb\u00b9\3\2\2\2\u00bc"+
		"\u00bd\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00c0\3\2"+
		"\2\2\u00bf\u00b6\3\2\2\2\u00bf\u00b7\3\2\2\2\u00bf\u00bb\3\2\2\2\u00c0"+
		"\33\3\2\2\2\u00c1\u00ce\3\2\2\2\u00c2\u00c7\5\36\20\2\u00c3\u00c4\7\20"+
		"\2\2\u00c4\u00c6\5\36\20\2\u00c5\u00c3\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7"+
		"\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00c7\3\2"+
		"\2\2\u00ca\u00cc\7\20\2\2\u00cb\u00ca\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc"+
		"\u00ce\3\2\2\2\u00cd\u00c1\3\2\2\2\u00cd\u00c2\3\2\2\2\u00ce\35\3\2\2"+
		"\2\u00cf\u00d2\5\"\22\2\u00d0\u00d2\5 \21\2\u00d1\u00cf\3\2\2\2\u00d1"+
		"\u00d0\3\2\2\2\u00d2\37\3\2\2\2\u00d3\u00d4\7\r\2\2\u00d4\u00d5\5\"\22"+
		"\2\u00d5!\3\2\2\2\u00d6\u00df\5&\24\2\u00d7\u00df\5$\23\2\u00d8\u00df"+
		"\5,\27\2\u00d9\u00df\58\35\2\u00da\u00db\7\25\2\2\u00db\u00dc\5\"\22\2"+
		"\u00dc\u00dd\7\31\2\2\u00dd\u00df\3\2\2\2\u00de\u00d6\3\2\2\2\u00de\u00d7"+
		"\3\2\2\2\u00de\u00d8\3\2\2\2\u00de\u00d9\3\2\2\2\u00de\u00da\3\2\2\2\u00df"+
		"#\3\2\2\2\u00e0\u00e4\5.\30\2\u00e1\u00e4\5\62\32\2\u00e2\u00e4\5\66\34"+
		"\2\u00e3\u00e0\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e2\3\2\2\2\u00e4%"+
		"\3\2\2\2\u00e5\u00ed\7\35\2\2\u00e6\u00ed\5:\36\2\u00e7\u00ed\5H%\2\u00e8"+
		"\u00e9\7\25\2\2\u00e9\u00ea\5\"\22\2\u00ea\u00eb\7\31\2\2\u00eb\u00ed"+
		"\3\2\2\2\u00ec\u00e5\3\2\2\2\u00ec\u00e6\3\2\2\2\u00ec\u00e7\3\2\2\2\u00ec"+
		"\u00e8\3\2\2\2\u00ed\'\3\2\2\2\u00ee\u00f1\5&\24\2\u00ef\u00f1\5.\30\2"+
		"\u00f0\u00ee\3\2\2\2\u00f0\u00ef\3\2\2\2\u00f1)\3\2\2\2\u00f2\u00f6\5"+
		"&\24\2\u00f3\u00f6\5.\30\2\u00f4\u00f6\5\62\32\2\u00f5\u00f2\3\2\2\2\u00f5"+
		"\u00f3\3\2\2\2\u00f5\u00f4\3\2\2\2\u00f6+\3\2\2\2\u00f7\u00f8\7\37\2\2"+
		"\u00f8\u00f9\5\"\22\2\u00f9-\3\2\2\2\u00fa\u00fb\5&\24\2\u00fb\u00fc\5"+
		"\60\31\2\u00fc/\3\2\2\2\u00fd\u00ff\7\35\2\2\u00fe\u0100\5\60\31\2\u00ff"+
		"\u00fe\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\61\3\2\2\2\u0101\u0102\5(\25"+
		"\2\u0102\u0103\5\64\33\2\u0103\63\3\2\2\2\u0104\u0105\7\34\2\2\u0105\u0107"+
		"\5(\25\2\u0106\u0108\5\64\33\2\u0107\u0106\3\2\2\2\u0107\u0108\3\2\2\2"+
		"\u0108\65\3\2\2\2\u0109\u010a\5*\26\2\u010a\u010b\58\35\2\u010b\67\3\2"+
		"\2\2\u010c\u010d\7\36\2\2\u010d\u010f\5*\26\2\u010e\u010c\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u01119\3\2\2\2"+
		"\u0112\u0119\5<\37\2\u0113\u0119\5> \2\u0114\u0119\5@!\2\u0115\u0119\5"+
		"B\"\2\u0116\u0119\5D#\2\u0117\u0119\5F$\2\u0118\u0112\3\2\2\2\u0118\u0113"+
		"\3\2\2\2\u0118\u0114\3\2\2\2\u0118\u0115\3\2\2\2\u0118\u0116\3\2\2\2\u0118"+
		"\u0117\3\2\2\2\u0119;\3\2\2\2\u011a\u011b\7\7\2\2\u011b=\3\2\2\2\u011c"+
		"\u011d\7\b\2\2\u011d?\3\2\2\2\u011e\u011f\7\t\2\2\u011fA\3\2\2\2\u0120"+
		"\u0121\7\n\2\2\u0121C\3\2\2\2\u0122\u0123\7\13\2\2\u0123E\3\2\2\2\u0124"+
		"\u0125\7\f\2\2\u0125\u0126\7\35\2\2\u0126G\3\2\2\2\u0127\u012b\5J&\2\u0128"+
		"\u012b\7\"\2\2\u0129\u012b\7!\2\2\u012a\u0127\3\2\2\2\u012a\u0128\3\2"+
		"\2\2\u012a\u0129\3\2\2\2\u012bI\3\2\2\2\u012c\u012e\7\23\2\2\u012d\u012f"+
		"\5L\'\2\u012e\u012d\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0131\3\2\2\2\u0130"+
		"\u0132\5N(\2\u0131\u0130\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0133\3\2\2"+
		"\2\u0133\u0134\5\34\17\2\u0134\u0135\7\27\2\2\u0135K\3\2\2\2\u0136\u0138"+
		"\7 \2\2\u0137\u0136\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u0137\3\2\2\2\u0139"+
		"\u013a\3\2\2\2\u013a\u013c\3\2\2\2\u013b\u0139\3\2\2\2\u013c\u013d\7\33"+
		"\2\2\u013dM\3\2\2\2\u013e\u0142\7\33\2\2\u013f\u0141\5\20\t\2\u0140\u013f"+
		"\3\2\2\2\u0141\u0144\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143"+
		"\u0145\3\2\2\2\u0144\u0142\3\2\2\2\u0145\u0146\7\33\2\2\u0146O\3\2\2\2"+
		"$V[bimu{\u0085\u008e\u0092\u0097\u00a2\u00ab\u00af\u00bd\u00bf\u00c7\u00cb"+
		"\u00cd\u00d1\u00de\u00e3\u00ec\u00f0\u00f5\u00ff\u0107\u0110\u0118\u012a"+
		"\u012e\u0131\u0139\u0142";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}