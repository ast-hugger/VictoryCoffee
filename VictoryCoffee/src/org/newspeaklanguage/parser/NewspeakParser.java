// Generated from /home/vassili/VictoryCoffee/VictoryCoffee/src/org/newspeaklanguage/parser/Newspeak.g4 by ANTLR 4.5.3
package org.newspeaklanguage.parser; 
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
		RCURLY=24, RPAREN=25, SEMICOLON=26, SLASH=27, VBAR=28, SPECIAL_CHAR=29, 
		BINARY_SELECTOR=30, IDENTIFIER=31, KEYWORD=32, SETTER_KEYWORD=33, BLOCK_ARG=34, 
		STRING=35, COMMENT=36, WS=37, T__4=1, T__5=2, T__6=3, T__7=4, T__8=5;
	public static final int
		RULE_sourceUnit = 0, RULE_classDecl = 1, RULE_classBody = 2, RULE_classHeader = 3, 
		RULE_instanceSideDecl = 4, RULE_classSideDecl = 5, RULE_category = 6, 
		RULE_slotDecl = 7, RULE_mutableSlotInitializer = 8, RULE_immutableSlotInitializer = 9, 
		RULE_methodDecl = 10, RULE_accessModifier = 11, RULE_messagePattern = 12, 
		RULE_codeBody = 13, RULE_statement = 14, RULE_returnStatement = 15, RULE_expression = 16, 
		RULE_block = 17, RULE_blockArgs = 18, RULE_blockTemps = 19, RULE_messageSend = 20, 
		RULE_receiverlessSend = 21, RULE_receiverfulSend = 22, RULE_receiver = 23, 
		RULE_specialReceiver = 24;
	public static final String[] ruleNames = {
		"sourceUnit", "classDecl", "classBody", "classHeader", "instanceSideDecl", 
		"classSideDecl", "category", "slotDecl", "mutableSlotInitializer", "immutableSlotInitializer", 
		"methodDecl", "accessModifier", "messagePattern", "codeBody", "statement", 
		"returnStatement", "expression", "block", "blockArgs", "blockTemps", "messageSend", 
		"receiverlessSend", "receiverfulSend", "receiver", "specialReceiver"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'class'", "'public'", "'protected'", "'private'", "'nil'", "'true'", 
		"'false'", "'self'", "'super'", "'outer'", "'^'", "':'", "','", "'.'", 
		"'='", "'::='", "'['", "'{'", "'('", "'<'", "'#'", "'>'", "']'", "'}'", 
		"')'", "';'", "'/'", "'|'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "TRUE", "FALSE", "SELF", "SUPER", 
		"OUTER", "CARET", "COLON", "COMMA", "DOT", "EQUAL_SIGN", "CCE_SIGN", "LBRACKET", 
		"LCURLY", "LPAREN", "LANGLE", "POUND", "RANGLE", "RBRACKET", "RCURLY", 
		"RPAREN", "SEMICOLON", "SLASH", "VBAR", "SPECIAL_CHAR", "BINARY_SELECTOR", 
		"IDENTIFIER", "KEYWORD", "SETTER_KEYWORD", "BLOCK_ARG", "STRING", "COMMENT", 
		"WS"
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
			setState(50);
			classDecl();
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
			setState(52);
			match(T__4);
			setState(53);
			match(IDENTIFIER);
			setState(54);
			match(EQUAL_SIGN);
			setState(56);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(55);
				match(IDENTIFIER);
				}
			}

			setState(58);
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
			setState(60);
			classHeader();
			setState(61);
			instanceSideDecl();
			setState(63);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(62);
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
			setState(65);
			match(LPAREN);
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(66);
				slotDecl();
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(72);
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
			setState(74);
			match(LPAREN);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(75);
				category();
				}
				}
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(81);
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
			setState(83);
			match(COLON);
			setState(84);
			match(LPAREN);
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(85);
				category();
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91);
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
			setState(93);
			match(STRING);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << BINARY_SELECTOR) | (1L << IDENTIFIER) | (1L << KEYWORD))) != 0)) {
				{
				{
				setState(94);
				methodDecl();
				}
				}
				setState(99);
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
			setState(101);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) {
				{
				setState(100);
				accessModifier();
				}
			}

			setState(103);
			match(IDENTIFIER);
			setState(106);
			switch (_input.LA(1)) {
			case CCE_SIGN:
				{
				setState(104);
				mutableSlotInitializer();
				}
				break;
			case EQUAL_SIGN:
				{
				setState(105);
				immutableSlotInitializer();
				}
				break;
			case T__5:
			case T__6:
			case T__7:
			case RPAREN:
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
			setState(108);
			match(CCE_SIGN);
			setState(109);
			expression();
			setState(110);
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
			setState(112);
			match(EQUAL_SIGN);
			setState(113);
			expression();
			setState(114);
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
			setState(117);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) {
				{
				setState(116);
				accessModifier();
				}
			}

			setState(119);
			messagePattern();
			setState(120);
			match(EQUAL_SIGN);
			setState(121);
			match(LPAREN);
			setState(122);
			codeBody();
			setState(123);
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
			setState(125);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) ) {
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
			setState(136);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				match(IDENTIFIER);
				}
				break;
			case BINARY_SELECTOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				match(BINARY_SELECTOR);
				setState(129);
				match(IDENTIFIER);
				}
				break;
			case KEYWORD:
				enterOuterAlt(_localctx, 3);
				{
				setState(132); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(130);
					match(KEYWORD);
					setState(131);
					match(IDENTIFIER);
					}
					}
					setState(134); 
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
			setState(138);
			statement();
			setState(143);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(139);
					match(DOT);
					setState(140);
					statement();
					}
					} 
				}
				setState(145);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(147);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(146);
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
			setState(151);
			switch (_input.LA(1)) {
			case T__8:
			case TRUE:
			case FALSE:
			case SELF:
			case SUPER:
			case OUTER:
			case LBRACKET:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				expression();
				}
				break;
			case CARET:
				enterOuterAlt(_localctx, 2);
				{
				setState(150);
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
			setState(153);
			match(CARET);
			setState(154);
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
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
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
			setState(158);
			switch (_input.LA(1)) {
			case LBRACKET:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				block();
				}
				break;
			case T__8:
			case TRUE:
			case FALSE:
			case SELF:
			case SUPER:
			case OUTER:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
				messageSend();
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
		enterRule(_localctx, 34, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(LBRACKET);
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(161);
				blockArgs();
				}
				break;
			}
			setState(165);
			_la = _input.LA(1);
			if (_la==VBAR) {
				{
				setState(164);
				blockTemps();
				}
			}

			setState(167);
			codeBody();
			setState(168);
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
		enterRule(_localctx, 36, RULE_blockArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BLOCK_ARG) {
				{
				{
				setState(170);
				match(BLOCK_ARG);
				}
				}
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(176);
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
		enterRule(_localctx, 38, RULE_blockTemps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(VBAR);
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(179);
				slotDecl();
				}
				}
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(185);
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
		enterRule(_localctx, 40, RULE_messageSend);
		try {
			setState(189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(187);
				receiverlessSend();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(188);
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
		public TerminalNode IDENTIFIER() { return getToken(NewspeakParser.IDENTIFIER, 0); }
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
		enterRule(_localctx, 42, RULE_receiverlessSend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
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

	public static class ReceiverfulSendContext extends ParserRuleContext {
		public ReceiverContext receiver() {
			return getRuleContext(ReceiverContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(NewspeakParser.IDENTIFIER, 0); }
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
		enterRule(_localctx, 44, RULE_receiverfulSend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			receiver();
			setState(194);
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

	public static class ReceiverContext extends ParserRuleContext {
		public SpecialReceiverContext specialReceiver() {
			return getRuleContext(SpecialReceiverContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(NewspeakParser.IDENTIFIER, 0); }
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
		enterRule(_localctx, 46, RULE_receiver);
		try {
			setState(198);
			switch (_input.LA(1)) {
			case T__8:
			case TRUE:
			case FALSE:
			case SELF:
			case SUPER:
			case OUTER:
				enterOuterAlt(_localctx, 1);
				{
				setState(196);
				specialReceiver();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(197);
				match(IDENTIFIER);
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

	public static class SpecialReceiverContext extends ParserRuleContext {
		public TerminalNode NIL() { return getToken(NewspeakParser.NIL, 0); }
		public TerminalNode TRUE() { return getToken(NewspeakParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(NewspeakParser.FALSE, 0); }
		public TerminalNode SELF() { return getToken(NewspeakParser.SELF, 0); }
		public TerminalNode SUPER() { return getToken(NewspeakParser.SUPER, 0); }
		public TerminalNode OUTER() { return getToken(NewspeakParser.OUTER, 0); }
		public TerminalNode IDENTIFIER() { return getToken(NewspeakParser.IDENTIFIER, 0); }
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
		enterRule(_localctx, 48, RULE_specialReceiver);
		try {
			setState(207);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				match(T__8);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(202);
				match(FALSE);
				}
				break;
			case SELF:
				enterOuterAlt(_localctx, 4);
				{
				setState(203);
				match(SELF);
				}
				break;
			case SUPER:
				enterOuterAlt(_localctx, 5);
				{
				setState(204);
				match(SUPER);
				}
				break;
			case OUTER:
				enterOuterAlt(_localctx, 6);
				{
				setState(205);
				match(OUTER);
				setState(206);
				match(IDENTIFIER);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\'\u00d4\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\3\3\3\3\3\3\3\5\3;\n\3\3\3\3\3\3\4\3\4\3\4\5\4B\n"+
		"\4\3\5\3\5\7\5F\n\5\f\5\16\5I\13\5\3\5\3\5\3\6\3\6\7\6O\n\6\f\6\16\6R"+
		"\13\6\3\6\3\6\3\7\3\7\3\7\7\7Y\n\7\f\7\16\7\\\13\7\3\7\3\7\3\b\3\b\7\b"+
		"b\n\b\f\b\16\be\13\b\3\t\5\th\n\t\3\t\3\t\3\t\5\tm\n\t\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\f\5\fx\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\6\16\u0087\n\16\r\16\16\16\u0088\5\16\u008b\n\16"+
		"\3\17\3\17\3\17\7\17\u0090\n\17\f\17\16\17\u0093\13\17\3\17\5\17\u0096"+
		"\n\17\3\20\3\20\5\20\u009a\n\20\3\21\3\21\3\21\3\22\3\22\5\22\u00a1\n"+
		"\22\3\23\3\23\5\23\u00a5\n\23\3\23\5\23\u00a8\n\23\3\23\3\23\3\23\3\24"+
		"\7\24\u00ae\n\24\f\24\16\24\u00b1\13\24\3\24\3\24\3\25\3\25\7\25\u00b7"+
		"\n\25\f\25\16\25\u00ba\13\25\3\25\3\25\3\26\3\26\5\26\u00c0\n\26\3\27"+
		"\3\27\3\30\3\30\3\30\3\31\3\31\5\31\u00c9\n\31\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\5\32\u00d2\n\32\3\32\2\2\33\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\62\2\3\3\2\4\6\u00d6\2\64\3\2\2\2\4\66\3\2\2\2\6>\3"+
		"\2\2\2\bC\3\2\2\2\nL\3\2\2\2\fU\3\2\2\2\16_\3\2\2\2\20g\3\2\2\2\22n\3"+
		"\2\2\2\24r\3\2\2\2\26w\3\2\2\2\30\177\3\2\2\2\32\u008a\3\2\2\2\34\u008c"+
		"\3\2\2\2\36\u0099\3\2\2\2 \u009b\3\2\2\2\"\u00a0\3\2\2\2$\u00a2\3\2\2"+
		"\2&\u00af\3\2\2\2(\u00b4\3\2\2\2*\u00bf\3\2\2\2,\u00c1\3\2\2\2.\u00c3"+
		"\3\2\2\2\60\u00c8\3\2\2\2\62\u00d1\3\2\2\2\64\65\5\4\3\2\65\3\3\2\2\2"+
		"\66\67\7\3\2\2\678\7!\2\28:\7\21\2\29;\7!\2\2:9\3\2\2\2:;\3\2\2\2;<\3"+
		"\2\2\2<=\5\6\4\2=\5\3\2\2\2>?\5\b\5\2?A\5\n\6\2@B\5\f\7\2A@\3\2\2\2AB"+
		"\3\2\2\2B\7\3\2\2\2CG\7\25\2\2DF\5\20\t\2ED\3\2\2\2FI\3\2\2\2GE\3\2\2"+
		"\2GH\3\2\2\2HJ\3\2\2\2IG\3\2\2\2JK\7\33\2\2K\t\3\2\2\2LP\7\25\2\2MO\5"+
		"\16\b\2NM\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RP\3\2\2\2ST"+
		"\7\33\2\2T\13\3\2\2\2UV\7\16\2\2VZ\7\25\2\2WY\5\16\b\2XW\3\2\2\2Y\\\3"+
		"\2\2\2ZX\3\2\2\2Z[\3\2\2\2[]\3\2\2\2\\Z\3\2\2\2]^\7\33\2\2^\r\3\2\2\2"+
		"_c\7%\2\2`b\5\26\f\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2d\17\3\2\2"+
		"\2ec\3\2\2\2fh\5\30\r\2gf\3\2\2\2gh\3\2\2\2hi\3\2\2\2il\7!\2\2jm\5\22"+
		"\n\2km\5\24\13\2lj\3\2\2\2lk\3\2\2\2lm\3\2\2\2m\21\3\2\2\2no\7\22\2\2"+
		"op\5\"\22\2pq\7\20\2\2q\23\3\2\2\2rs\7\21\2\2st\5\"\22\2tu\7\20\2\2u\25"+
		"\3\2\2\2vx\5\30\r\2wv\3\2\2\2wx\3\2\2\2xy\3\2\2\2yz\5\32\16\2z{\7\21\2"+
		"\2{|\7\25\2\2|}\5\34\17\2}~\7\33\2\2~\27\3\2\2\2\177\u0080\t\2\2\2\u0080"+
		"\31\3\2\2\2\u0081\u008b\7!\2\2\u0082\u0083\7 \2\2\u0083\u008b\7!\2\2\u0084"+
		"\u0085\7\"\2\2\u0085\u0087\7!\2\2\u0086\u0084\3\2\2\2\u0087\u0088\3\2"+
		"\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008b\3\2\2\2\u008a"+
		"\u0081\3\2\2\2\u008a\u0082\3\2\2\2\u008a\u0086\3\2\2\2\u008b\33\3\2\2"+
		"\2\u008c\u0091\5\36\20\2\u008d\u008e\7\20\2\2\u008e\u0090\5\36\20\2\u008f"+
		"\u008d\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2"+
		"\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0096\7\20\2\2\u0095"+
		"\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096\35\3\2\2\2\u0097\u009a\5\"\22"+
		"\2\u0098\u009a\5 \21\2\u0099\u0097\3\2\2\2\u0099\u0098\3\2\2\2\u009a\37"+
		"\3\2\2\2\u009b\u009c\7\r\2\2\u009c\u009d\5\"\22\2\u009d!\3\2\2\2\u009e"+
		"\u00a1\5$\23\2\u009f\u00a1\5*\26\2\u00a0\u009e\3\2\2\2\u00a0\u009f\3\2"+
		"\2\2\u00a1#\3\2\2\2\u00a2\u00a4\7\23\2\2\u00a3\u00a5\5&\24\2\u00a4\u00a3"+
		"\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a8\5(\25\2\u00a7"+
		"\u00a6\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\5\34"+
		"\17\2\u00aa\u00ab\7\31\2\2\u00ab%\3\2\2\2\u00ac\u00ae\7$\2\2\u00ad\u00ac"+
		"\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0"+
		"\u00b2\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\7\36\2\2\u00b3\'\3\2\2"+
		"\2\u00b4\u00b8\7\36\2\2\u00b5\u00b7\5\20\t\2\u00b6\u00b5\3\2\2\2\u00b7"+
		"\u00ba\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bb\3\2"+
		"\2\2\u00ba\u00b8\3\2\2\2\u00bb\u00bc\7\36\2\2\u00bc)\3\2\2\2\u00bd\u00c0"+
		"\5,\27\2\u00be\u00c0\5.\30\2\u00bf\u00bd\3\2\2\2\u00bf\u00be\3\2\2\2\u00c0"+
		"+\3\2\2\2\u00c1\u00c2\7!\2\2\u00c2-\3\2\2\2\u00c3\u00c4\5\60\31\2\u00c4"+
		"\u00c5\7!\2\2\u00c5/\3\2\2\2\u00c6\u00c9\5\62\32\2\u00c7\u00c9\7!\2\2"+
		"\u00c8\u00c6\3\2\2\2\u00c8\u00c7\3\2\2\2\u00c9\61\3\2\2\2\u00ca\u00d2"+
		"\7\7\2\2\u00cb\u00d2\7\b\2\2\u00cc\u00d2\7\t\2\2\u00cd\u00d2\7\n\2\2\u00ce"+
		"\u00d2\7\13\2\2\u00cf\u00d0\7\f\2\2\u00d0\u00d2\7!\2\2\u00d1\u00ca\3\2"+
		"\2\2\u00d1\u00cb\3\2\2\2\u00d1\u00cc\3\2\2\2\u00d1\u00cd\3\2\2\2\u00d1"+
		"\u00ce\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\63\3\2\2\2\30:AGPZcglw\u0088"+
		"\u008a\u0091\u0095\u0099\u00a0\u00a4\u00a7\u00af\u00b8\u00bf\u00c8\u00d1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}