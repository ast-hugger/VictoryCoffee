/*
 * NLS: Newspeak Language Specification Version 0.096
 * http://bracha.org/newspeak-spec.pdf
 */

grammar Newspeak;

options {
	language = Java;
}

// @header {package org.newspeaklanguage.compiler.parser;}

sourceUnit : classDecl EOF;

/*
 *  Class
 */

classDecl
	: 'class' IDENTIFIER messagePattern? '=' (IDENTIFIER messagePattern)? classBody ;

classBody
	: classHeader instanceSideDecl classSideDecl? ;

classHeader
	: LPAREN (VBAR slotDecl* VBAR)? RPAREN;

instanceSideDecl
	: LPAREN classDecl* category* RPAREN;

classSideDecl
	: COLON LPAREN category* RPAREN;

/*
 *  Slots and methods
 */

category
	: STRING methodDecl* ;

slotDecl
	: accessModifier? IDENTIFIER (mutableSlotInitializer | immutableSlotInitializer)? ;

mutableSlotInitializer
	: CCE_SIGN expression DOT ;

immutableSlotInitializer
	: EQUAL_SIGN expression DOT ;

methodDecl
	: accessModifier? messagePattern EQUAL_SIGN LPAREN (VBAR slotDecl* VBAR)? codeBody RPAREN ;

accessModifier
	: 'public' | 'protected' | 'private' ;

messagePattern
	: IDENTIFIER # unaryPattern
	| BINARY_SELECTOR IDENTIFIER # binaryPattern
	| (KEYWORD IDENTIFIER)+ # keywordPattern
	;

/*
 *  Code
 */

codeBody
	: /* empty */
	| statement (DOT statement)* DOT?;

statement
	: expression
	| returnStatement ;

returnStatement
	: CARET expression ;

// TODO no cascades yet

expression
    : receiver
    | messageSend
  	| setterSend
    | keywordMessage
    | LPAREN expression RPAREN
    ;

messageSend
		: unarySend
		| binarySend
		| keywordSend
 		;

receiver
    : IDENTIFIER
    | specialReceiver
    | literal
    | LPAREN expression RPAREN
    ;

binaryObject
	  : receiver
	  | unarySend;

keywordObject
    : receiver
    | unarySend
    | binarySend;


setterSend
    : SETTER_KEYWORD expression;

unarySend
		: receiver unaryMessage;

unaryMessage
    : IDENTIFIER unaryMessage?;

binarySend
		: binaryObject binaryMessage;

binaryMessage
		: BINARY_SELECTOR binaryObject binaryMessage?;

keywordSend
		: keywordObject keywordMessage;

keywordMessage
		: (KEYWORD keywordObject)+ ;

specialReceiver
	: nilReceiver
	| trueReceiver
	| falseReceiver
	| selfReceiver
	| superReceiver
	| outerReceiver;

nilReceiver : NIL;

trueReceiver : TRUE;

falseReceiver : FALSE;

selfReceiver : SELF;

superReceiver : SUPER;

outerReceiver : OUTER IDENTIFIER;

literal
	: block    # blockLiteral
	| INTEGER  # integerLiteral
	| STRING   # stringLiteral
	;

block :
	LBRACKET blockArgs? blockTemps? codeBody RBRACKET ;

blockArgs :
	BLOCK_ARG* VBAR ;

blockTemps :
	VBAR slotDecl* VBAR ;

/*
 * Lexer
 */

// NLS 4.1
NIL   : 'nil';
TRUE  : 'true';
FALSE : 'false';
SELF  : 'self';
SUPER : 'super';
OUTER : 'outer';

// NLS 4.2
CARET : '^';
COLON : ':';
COMMA : ',';
DOT : '.';
EQUAL_SIGN : '=';
CCE_SIGN : '::=';
LBRACKET : '[';
LCURLY : '{';
LPAREN : '(';
//LANGLE : '<';
POUND : '#';
//RANGLE : '>';
RBRACKET : ']';
RCURLY : '}';
RPAREN : ')';
SEMICOLON : ';';
// SLASH : '/';
VBAR : '|';

BINARY_SELECTOR : '+' | '/' | '*' | '-' | '<' | '>'; // good enough for now

IDENTIFIER : [a-zA-Z_][a-zA-Z_0-9]*;
KEYWORD : IDENTIFIER ':';
SETTER_KEYWORD : KEYWORD ':';
BLOCK_ARG : ':' IDENTIFIER ;

STRING : '\'' ~[']* '\'' ;
INTEGER : [0-9]+;

COMMENT    : '(*' .*? '*)' -> skip;
WHITESPACE : [ \t\r\n]+ -> skip;

