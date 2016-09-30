// Generated from /home/vassili/VictoryCoffee/VictoryCoffee/src/org/newspeaklanguage/parser/Newspeak.g4 by ANTLR 4.5.3
package org.newspeaklanguage.parser; 
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NewspeakLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__4=1, T__5=2, T__6=3, T__7=4, T__8=5;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__4", "T__5", "T__6", "T__7", "T__8"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'class'", "'='", "'public'", "'protected'", "'private'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
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


	public NewspeakLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Newspeak.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\7.\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\2\2\7\3\3\5\4\7\5\t\6\13\7\3\2\2-\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\3\r\3\2\2\2\5\23\3\2\2"+
		"\2\7\25\3\2\2\2\t\34\3\2\2\2\13&\3\2\2\2\r\16\7e\2\2\16\17\7n\2\2\17\20"+
		"\7c\2\2\20\21\7u\2\2\21\22\7u\2\2\22\4\3\2\2\2\23\24\7?\2\2\24\6\3\2\2"+
		"\2\25\26\7r\2\2\26\27\7w\2\2\27\30\7d\2\2\30\31\7n\2\2\31\32\7k\2\2\32"+
		"\33\7e\2\2\33\b\3\2\2\2\34\35\7r\2\2\35\36\7t\2\2\36\37\7q\2\2\37 \7v"+
		"\2\2 !\7g\2\2!\"\7e\2\2\"#\7v\2\2#$\7g\2\2$%\7f\2\2%\n\3\2\2\2&\'\7r\2"+
		"\2\'(\7t\2\2()\7k\2\2)*\7x\2\2*+\7c\2\2+,\7v\2\2,-\7g\2\2-\f\3\2\2\2\3"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}