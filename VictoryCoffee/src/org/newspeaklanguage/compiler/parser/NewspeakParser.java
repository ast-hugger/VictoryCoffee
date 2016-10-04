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
		T__0=1, T__1=2, T__2=3, T__3=4, CARET=5, COLON=6, COMMA=7, DOT=8, EQUAL_SIGN=9, 
		CCE_SIGN=10, LBRACKET=11, LCURLY=12, LPAREN=13, LANGLE=14, POUND=15, RANGLE=16, 
		RBRACKET=17, RCURLY=18, RPAREN=19, SEMICOLON=20, SLASH=21, VBAR=22, SPECIAL_CHAR=23, 
		BINARY_SELECTOR=24, IDENTIFIER=25, KEYWORD=26, SETTER_KEYWORD=27, BLOCK_ARG=28, 
		STRING=29, INTEGER=30, COMMENT=31, WHITESPACE=32;
	public static final int
		RULE_sourceUnit = 0, RULE_classDecl = 1, RULE_classBody = 2, RULE_classHeader = 3, 
		RULE_instanceSideDecl = 4, RULE_classSideDecl = 5, RULE_category = 6, 
		RULE_slotDecl = 7, RULE_mutableSlotInitializer = 8, RULE_immutableSlotInitializer = 9, 
		RULE_methodDecl = 10, RULE_accessModifier = 11, RULE_messagePattern = 12, 
		RULE_codeBody = 13, RULE_statement = 14, RULE_returnStatement = 15, RULE_expression = 16, 
		RULE_messageSend = 17, RULE_receiverlessSend = 18, RULE_receiverfulSend = 19, 
		RULE_receiver = 20, RULE_message = 21, RULE_unaryMessage = 22, RULE_binaryMessage = 23, 
		RULE_keywordMessage = 24, RULE_literal = 25, RULE_block = 26, RULE_blockArgs = 27, 
		RULE_blockTemps = 28;
	public static final String[] ruleNames = {
		"sourceUnit", "classDecl", "classBody", "classHeader", "instanceSideDecl", 
		"classSideDecl", "category", "slotDecl", "mutableSlotInitializer", "immutableSlotInitializer", 
		"methodDecl", "accessModifier", "messagePattern", "codeBody", "statement", 
		"returnStatement", "expression", "messageSend", "receiverlessSend", "receiverfulSend", 
		"receiver", "message", "unaryMessage", "binaryMessage", "keywordMessage", 
		"literal", "block", "blockArgs", "blockTemps"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'class'", "'public'", "'protected'", "'private'", "'^'", "':'", 
		"','", "'.'", "'='", "'::='", "'['", "'{'", "'('", "'<'", "'#'", "'>'", 
		"']'", "'}'", "')'", "';'", "'/'", "'|'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "CARET", "COLON", "COMMA", "DOT", "EQUAL_SIGN", 
		"CCE_SIGN", "LBRACKET", "LCURLY", "LPAREN", "LANGLE", "POUND", "RANGLE", 
		"RBRACKET", "RCURLY", "RPAREN", "SEMICOLON", "SLASH", "VBAR", "SPECIAL_CHAR", 
		"BINARY_SELECTOR", "IDENTIFIER", "KEYWORD", "SETTER_KEYWORD", "BLOCK_ARG", 
		"STRING", "INTEGER", "COMMENT", "WHITESPACE"
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
			setState(58);
			classDecl();
			setState(59);
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
			setState(61);
			match(T__0);
			setState(62);
			match(IDENTIFIER);
			setState(64);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BINARY_SELECTOR) | (1L << IDENTIFIER) | (1L << KEYWORD))) != 0)) {
				{
				setState(63);
				messagePattern();
				}
			}

			setState(66);
			match(EQUAL_SIGN);
			setState(69);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(67);
				match(IDENTIFIER);
				setState(68);
				messagePattern();
				}
			}

			setState(71);
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
			setState(73);
			classHeader();
			setState(74);
			instanceSideDecl();
			setState(76);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(75);
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
		public List<TerminalNode> VBAR() { return getTokens(NewspeakParser.VBAR); }
		public TerminalNode VBAR(int i) {
			return getToken(NewspeakParser.VBAR, i);
		}
		public TerminalNode RPAREN() { return getToken(NewspeakParser.RPAREN, 0); }
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
			setState(78);
			match(LPAREN);
			setState(79);
			match(VBAR);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(80);
				slotDecl();
				}
				}
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(86);
			match(VBAR);
			setState(87);
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
			setState(89);
			match(LPAREN);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(90);
				classDecl();
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(96);
				category();
				}
				}
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(102);
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
			setState(104);
			match(COLON);
			setState(105);
			match(LPAREN);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(106);
				category();
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(112);
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
			setState(114);
			match(STRING);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << BINARY_SELECTOR) | (1L << IDENTIFIER) | (1L << KEYWORD))) != 0)) {
				{
				{
				setState(115);
				methodDecl();
				}
				}
				setState(120);
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
			setState(122);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3))) != 0)) {
				{
				setState(121);
				accessModifier();
				}
			}

			setState(124);
			match(IDENTIFIER);
			setState(127);
			switch (_input.LA(1)) {
			case CCE_SIGN:
				{
				setState(125);
				mutableSlotInitializer();
				}
				break;
			case EQUAL_SIGN:
				{
				setState(126);
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
			setState(129);
			match(CCE_SIGN);
			setState(130);
			expression();
			setState(131);
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
			setState(133);
			match(EQUAL_SIGN);
			setState(134);
			expression();
			setState(135);
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
		public TerminalNode RPAREN() { return getToken(NewspeakParser.RPAREN, 0); }
		public AccessModifierContext accessModifier() {
			return getRuleContext(AccessModifierContext.class,0);
		}
		public List<TerminalNode> VBAR() { return getTokens(NewspeakParser.VBAR); }
		public TerminalNode VBAR(int i) {
			return getToken(NewspeakParser.VBAR, i);
		}
		public CodeBodyContext codeBody() {
			return getRuleContext(CodeBodyContext.class,0);
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
			setState(138);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3))) != 0)) {
				{
				setState(137);
				accessModifier();
				}
			}

			setState(140);
			messagePattern();
			setState(141);
			match(EQUAL_SIGN);
			setState(142);
			match(LPAREN);
			setState(151);
			_la = _input.LA(1);
			if (_la==VBAR) {
				{
				setState(143);
				match(VBAR);
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << IDENTIFIER))) != 0)) {
					{
					{
					setState(144);
					slotDecl();
					}
					}
					setState(149);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(150);
				match(VBAR);
				}
			}

			setState(154);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CARET) | (1L << LBRACKET) | (1L << LPAREN) | (1L << BINARY_SELECTOR) | (1L << IDENTIFIER) | (1L << KEYWORD) | (1L << STRING) | (1L << INTEGER))) != 0)) {
				{
				setState(153);
				codeBody();
				}
			}

			setState(156);
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
			setState(158);
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
		public List<TerminalNode> IDENTIFIER() { return getTokens(NewspeakParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(NewspeakParser.IDENTIFIER, i);
		}
		public TerminalNode BINARY_SELECTOR() { return getToken(NewspeakParser.BINARY_SELECTOR, 0); }
		public List<TerminalNode> KEYWORD() { return getTokens(NewspeakParser.KEYWORD); }
		public TerminalNode KEYWORD(int i) {
			return getToken(NewspeakParser.KEYWORD, i);
		}
		public MessagePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_messagePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterMessagePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitMessagePattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitMessagePattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessagePatternContext messagePattern() throws RecognitionException {
		MessagePatternContext _localctx = new MessagePatternContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_messagePattern);
		int _la;
		try {
			setState(169);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				match(IDENTIFIER);
				}
				break;
			case BINARY_SELECTOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				match(BINARY_SELECTOR);
				setState(162);
				match(IDENTIFIER);
				}
				break;
			case KEYWORD:
				enterOuterAlt(_localctx, 3);
				{
				setState(165); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(163);
					match(KEYWORD);
					setState(164);
					match(IDENTIFIER);
					}
					}
					setState(167); 
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
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			statement();
			setState(176);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(172);
					match(DOT);
					setState(173);
					statement();
					}
					} 
				}
				setState(178);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			setState(180);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(179);
				match(DOT);
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
			setState(184);
			switch (_input.LA(1)) {
			case LBRACKET:
			case LPAREN:
			case BINARY_SELECTOR:
			case IDENTIFIER:
			case KEYWORD:
			case STRING:
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				expression();
				}
				break;
			case CARET:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
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
			setState(186);
			match(CARET);
			setState(187);
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
			setState(191);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				receiver();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(190);
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
			setState(195);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				receiverlessSend();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
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
			setState(197);
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
			setState(199);
			receiver();
			setState(200);
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
			setState(208);
			switch (_input.LA(1)) {
			case LBRACKET:
			case STRING:
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(202);
				literal();
				}
				break;
			case BINARY_SELECTOR:
			case IDENTIFIER:
			case KEYWORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(203);
				receiverlessSend();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 3);
				{
				setState(204);
				match(LPAREN);
				setState(205);
				expression();
				setState(206);
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
			setState(213);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				unaryMessage();
				}
				break;
			case BINARY_SELECTOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(211);
				binaryMessage();
				}
				break;
			case KEYWORD:
				enterOuterAlt(_localctx, 3);
				{
				setState(212);
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
			setState(215);
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
			setState(217);
			match(BINARY_SELECTOR);
			setState(218);
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
			setState(222); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(220);
					match(KEYWORD);
					setState(221);
					expression();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(224); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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

	public static class LiteralContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode INTEGER() { return getToken(NewspeakParser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(NewspeakParser.STRING, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NewspeakListener ) ((NewspeakListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof NewspeakVisitor ) return ((NewspeakVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_literal);
		try {
			setState(229);
			switch (_input.LA(1)) {
			case LBRACKET:
				enterOuterAlt(_localctx, 1);
				{
				setState(226);
				block();
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(227);
				match(INTEGER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(228);
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
		enterRule(_localctx, 52, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(LBRACKET);
			setState(233);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(232);
				blockArgs();
				}
				break;
			}
			setState(236);
			_la = _input.LA(1);
			if (_la==VBAR) {
				{
				setState(235);
				blockTemps();
				}
			}

			setState(238);
			codeBody();
			setState(239);
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
		enterRule(_localctx, 54, RULE_blockArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BLOCK_ARG) {
				{
				{
				setState(241);
				match(BLOCK_ARG);
				}
				}
				setState(246);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(247);
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
		enterRule(_localctx, 56, RULE_blockTemps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(VBAR);
			setState(253);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(250);
				slotDecl();
				}
				}
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(256);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\"\u0105\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\5\3C\n\3\3\3\3\3\3\3\5\3H\n\3\3\3\3\3\3\4\3\4\3\4\5\4O\n\4\3\5\3"+
		"\5\3\5\7\5T\n\5\f\5\16\5W\13\5\3\5\3\5\3\5\3\6\3\6\7\6^\n\6\f\6\16\6a"+
		"\13\6\3\6\7\6d\n\6\f\6\16\6g\13\6\3\6\3\6\3\7\3\7\3\7\7\7n\n\7\f\7\16"+
		"\7q\13\7\3\7\3\7\3\b\3\b\7\bw\n\b\f\b\16\bz\13\b\3\t\5\t}\n\t\3\t\3\t"+
		"\3\t\5\t\u0082\n\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\5\f\u008d\n"+
		"\f\3\f\3\f\3\f\3\f\3\f\7\f\u0094\n\f\f\f\16\f\u0097\13\f\3\f\5\f\u009a"+
		"\n\f\3\f\5\f\u009d\n\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\6\16\u00a8"+
		"\n\16\r\16\16\16\u00a9\5\16\u00ac\n\16\3\17\3\17\3\17\7\17\u00b1\n\17"+
		"\f\17\16\17\u00b4\13\17\3\17\5\17\u00b7\n\17\3\20\3\20\5\20\u00bb\n\20"+
		"\3\21\3\21\3\21\3\22\3\22\5\22\u00c2\n\22\3\23\3\23\5\23\u00c6\n\23\3"+
		"\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u00d3\n\26"+
		"\3\27\3\27\3\27\5\27\u00d8\n\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\6\32"+
		"\u00e1\n\32\r\32\16\32\u00e2\3\33\3\33\3\33\5\33\u00e8\n\33\3\34\3\34"+
		"\5\34\u00ec\n\34\3\34\5\34\u00ef\n\34\3\34\3\34\3\34\3\35\7\35\u00f5\n"+
		"\35\f\35\16\35\u00f8\13\35\3\35\3\35\3\36\3\36\7\36\u00fe\n\36\f\36\16"+
		"\36\u0101\13\36\3\36\3\36\3\36\2\2\37\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\62\64\668:\2\3\3\2\4\6\u0109\2<\3\2\2\2\4?\3\2\2\2"+
		"\6K\3\2\2\2\bP\3\2\2\2\n[\3\2\2\2\fj\3\2\2\2\16t\3\2\2\2\20|\3\2\2\2\22"+
		"\u0083\3\2\2\2\24\u0087\3\2\2\2\26\u008c\3\2\2\2\30\u00a0\3\2\2\2\32\u00ab"+
		"\3\2\2\2\34\u00ad\3\2\2\2\36\u00ba\3\2\2\2 \u00bc\3\2\2\2\"\u00c1\3\2"+
		"\2\2$\u00c5\3\2\2\2&\u00c7\3\2\2\2(\u00c9\3\2\2\2*\u00d2\3\2\2\2,\u00d7"+
		"\3\2\2\2.\u00d9\3\2\2\2\60\u00db\3\2\2\2\62\u00e0\3\2\2\2\64\u00e7\3\2"+
		"\2\2\66\u00e9\3\2\2\28\u00f6\3\2\2\2:\u00fb\3\2\2\2<=\5\4\3\2=>\7\2\2"+
		"\3>\3\3\2\2\2?@\7\3\2\2@B\7\33\2\2AC\5\32\16\2BA\3\2\2\2BC\3\2\2\2CD\3"+
		"\2\2\2DG\7\13\2\2EF\7\33\2\2FH\5\32\16\2GE\3\2\2\2GH\3\2\2\2HI\3\2\2\2"+
		"IJ\5\6\4\2J\5\3\2\2\2KL\5\b\5\2LN\5\n\6\2MO\5\f\7\2NM\3\2\2\2NO\3\2\2"+
		"\2O\7\3\2\2\2PQ\7\17\2\2QU\7\30\2\2RT\5\20\t\2SR\3\2\2\2TW\3\2\2\2US\3"+
		"\2\2\2UV\3\2\2\2VX\3\2\2\2WU\3\2\2\2XY\7\30\2\2YZ\7\25\2\2Z\t\3\2\2\2"+
		"[_\7\17\2\2\\^\5\4\3\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`e\3\2"+
		"\2\2a_\3\2\2\2bd\5\16\b\2cb\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2fh\3"+
		"\2\2\2ge\3\2\2\2hi\7\25\2\2i\13\3\2\2\2jk\7\b\2\2ko\7\17\2\2ln\5\16\b"+
		"\2ml\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2\2pr\3\2\2\2qo\3\2\2\2rs\7\25"+
		"\2\2s\r\3\2\2\2tx\7\37\2\2uw\5\26\f\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy"+
		"\3\2\2\2y\17\3\2\2\2zx\3\2\2\2{}\5\30\r\2|{\3\2\2\2|}\3\2\2\2}~\3\2\2"+
		"\2~\u0081\7\33\2\2\177\u0082\5\22\n\2\u0080\u0082\5\24\13\2\u0081\177"+
		"\3\2\2\2\u0081\u0080\3\2\2\2\u0081\u0082\3\2\2\2\u0082\21\3\2\2\2\u0083"+
		"\u0084\7\f\2\2\u0084\u0085\5\"\22\2\u0085\u0086\7\n\2\2\u0086\23\3\2\2"+
		"\2\u0087\u0088\7\13\2\2\u0088\u0089\5\"\22\2\u0089\u008a\7\n\2\2\u008a"+
		"\25\3\2\2\2\u008b\u008d\5\30\r\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2"+
		"\2\u008d\u008e\3\2\2\2\u008e\u008f\5\32\16\2\u008f\u0090\7\13\2\2\u0090"+
		"\u0099\7\17\2\2\u0091\u0095\7\30\2\2\u0092\u0094\5\20\t\2\u0093\u0092"+
		"\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u0098\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u009a\7\30\2\2\u0099\u0091\3"+
		"\2\2\2\u0099\u009a\3\2\2\2\u009a\u009c\3\2\2\2\u009b\u009d\5\34\17\2\u009c"+
		"\u009b\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\7\25"+
		"\2\2\u009f\27\3\2\2\2\u00a0\u00a1\t\2\2\2\u00a1\31\3\2\2\2\u00a2\u00ac"+
		"\7\33\2\2\u00a3\u00a4\7\32\2\2\u00a4\u00ac\7\33\2\2\u00a5\u00a6\7\34\2"+
		"\2\u00a6\u00a8\7\33\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab\u00a2\3\2"+
		"\2\2\u00ab\u00a3\3\2\2\2\u00ab\u00a7\3\2\2\2\u00ac\33\3\2\2\2\u00ad\u00b2"+
		"\5\36\20\2\u00ae\u00af\7\n\2\2\u00af\u00b1\5\36\20\2\u00b0\u00ae\3\2\2"+
		"\2\u00b1\u00b4\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b6"+
		"\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b5\u00b7\7\n\2\2\u00b6\u00b5\3\2\2\2\u00b6"+
		"\u00b7\3\2\2\2\u00b7\35\3\2\2\2\u00b8\u00bb\5\"\22\2\u00b9\u00bb\5 \21"+
		"\2\u00ba\u00b8\3\2\2\2\u00ba\u00b9\3\2\2\2\u00bb\37\3\2\2\2\u00bc\u00bd"+
		"\7\7\2\2\u00bd\u00be\5\"\22\2\u00be!\3\2\2\2\u00bf\u00c2\5*\26\2\u00c0"+
		"\u00c2\5$\23\2\u00c1\u00bf\3\2\2\2\u00c1\u00c0\3\2\2\2\u00c2#\3\2\2\2"+
		"\u00c3\u00c6\5&\24\2\u00c4\u00c6\5(\25\2\u00c5\u00c3\3\2\2\2\u00c5\u00c4"+
		"\3\2\2\2\u00c6%\3\2\2\2\u00c7\u00c8\5,\27\2\u00c8\'\3\2\2\2\u00c9\u00ca"+
		"\5*\26\2\u00ca\u00cb\5,\27\2\u00cb)\3\2\2\2\u00cc\u00d3\5\64\33\2\u00cd"+
		"\u00d3\5&\24\2\u00ce\u00cf\7\17\2\2\u00cf\u00d0\5\"\22\2\u00d0\u00d1\7"+
		"\25\2\2\u00d1\u00d3\3\2\2\2\u00d2\u00cc\3\2\2\2\u00d2\u00cd\3\2\2\2\u00d2"+
		"\u00ce\3\2\2\2\u00d3+\3\2\2\2\u00d4\u00d8\5.\30\2\u00d5\u00d8\5\60\31"+
		"\2\u00d6\u00d8\5\62\32\2\u00d7\u00d4\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7"+
		"\u00d6\3\2\2\2\u00d8-\3\2\2\2\u00d9\u00da\7\33\2\2\u00da/\3\2\2\2\u00db"+
		"\u00dc\7\32\2\2\u00dc\u00dd\5\"\22\2\u00dd\61\3\2\2\2\u00de\u00df\7\34"+
		"\2\2\u00df\u00e1\5\"\22\2\u00e0\u00de\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2"+
		"\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\63\3\2\2\2\u00e4\u00e8\5\66\34"+
		"\2\u00e5\u00e8\7 \2\2\u00e6\u00e8\7\37\2\2\u00e7\u00e4\3\2\2\2\u00e7\u00e5"+
		"\3\2\2\2\u00e7\u00e6\3\2\2\2\u00e8\65\3\2\2\2\u00e9\u00eb\7\r\2\2\u00ea"+
		"\u00ec\58\35\2\u00eb\u00ea\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ee\3\2"+
		"\2\2\u00ed\u00ef\5:\36\2\u00ee\u00ed\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef"+
		"\u00f0\3\2\2\2\u00f0\u00f1\5\34\17\2\u00f1\u00f2\7\23\2\2\u00f2\67\3\2"+
		"\2\2\u00f3\u00f5\7\36\2\2\u00f4\u00f3\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6"+
		"\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f9\3\2\2\2\u00f8\u00f6\3\2"+
		"\2\2\u00f9\u00fa\7\30\2\2\u00fa9\3\2\2\2\u00fb\u00ff\7\30\2\2\u00fc\u00fe"+
		"\5\20\t\2\u00fd\u00fc\3\2\2\2\u00fe\u0101\3\2\2\2\u00ff\u00fd\3\2\2\2"+
		"\u00ff\u0100\3\2\2\2\u0100\u0102\3\2\2\2\u0101\u00ff\3\2\2\2\u0102\u0103"+
		"\7\30\2\2\u0103;\3\2\2\2\37BGNU_eox|\u0081\u008c\u0095\u0099\u009c\u00a9"+
		"\u00ab\u00b2\u00b6\u00ba\u00c1\u00c5\u00d2\u00d7\u00e2\u00e7\u00eb\u00ee"+
		"\u00f6\u00ff";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}