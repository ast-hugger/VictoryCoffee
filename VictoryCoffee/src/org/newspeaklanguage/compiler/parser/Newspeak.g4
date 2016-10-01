/*
 * NLS: Newspeak Language Specification Version 0.096
 * http://bracha.org/newspeak-spec.pdf
 */

grammar Newspeak;

options {
	language = Java;
}

@header {package org.newspeaklanguage.compiler.parser;}

sourceUnit : classDecl EOF;

/*
 *  Class
 */

classDecl
	: 'class' IDENTIFIER messagePattern? '=' (IDENTIFIER messagePattern)? classBody ;

classBody
	: classHeader instanceSideDecl classSideDecl? ;

classHeader
	: LPAREN VBAR slotDecl* VBAR RPAREN;

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
	: accessModifier? messagePattern EQUAL_SIGN LPAREN codeBody? RPAREN ;

accessModifier
	: 'public' | 'protected' | 'private' ;

messagePattern
	: IDENTIFIER
	| BINARY_SELECTOR IDENTIFIER
	| (KEYWORD IDENTIFIER)+ ;


/*
 *  Code
 */

codeBody
	: statement (DOT statement)* DOT?;

statement
	: expression
	| returnStatement ;

returnStatement
	: CARET expression ;

expression
	: receiver
	| messageSend ;

// TODO fit setter sends into this somehow

messageSend
	: receiverlessSend
	| receiverfulSend ;

receiverlessSend
	: message ;

receiverfulSend
	: receiver message;

message
	: unaryMessage
	| binaryMessage
	| keywordMessage;

unaryMessage
	: IDENTIFIER;

binaryMessage
	: BINARY_SELECTOR expression;

keywordMessage
	: (KEYWORD expression)+ ;

receiver
	: IDENTIFIER
	| specialReceiver
	| literal;

specialReceiver
	: NIL
	| TRUE
	| FALSE
	| SELF
	| SUPER
	| OUTER IDENTIFIER;

literal
	: block
	| STRING;

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
LANGLE : '<';
POUND : '#';
RANGLE : '>';
RBRACKET : ']';
RCURLY : '}';
RPAREN : ')';
SEMICOLON : ';';
SLASH : '/';
VBAR : '|';

SPECIAL_CHAR : '+' | '/' | '*' | '~' ; // good enough for now

BINARY_SELECTOR : SPECIAL_CHAR | '-';

IDENTIFIER : [a-zA-Z_][a-zA-Z_0-9]*;
KEYWORD : IDENTIFIER ':';
SETTER_KEYWORD : KEYWORD ':';
BLOCK_ARG : ':' IDENTIFIER ;

STRING : '\'' ~[']* '\'' ;

COMMENT    : '(*' .*? '*)' -> skip;
WHITESPACE : [ \t\r\n]+ -> skip;

