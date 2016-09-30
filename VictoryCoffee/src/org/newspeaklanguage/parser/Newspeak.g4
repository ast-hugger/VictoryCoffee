/*
 * NLS: Newspeak Language Specification Version 0.096
 * http://bracha.org/newspeak-spec.pdf
 */

grammar Newspeak;

options {
	language = Java;
}

@header {package org.newspeaklanguage.parser; }

sourceUnit : classDecl;

classDecl :
	'class' IDENTIFIER '=' IDENTIFIER? classBody ;

classBody :
	classHeader instanceSideDecl classSideDecl? ;

classHeader :
	LPAREN slotDecl* RPAREN;

instanceSideDecl :
	LPAREN category* RPAREN;

classSideDecl :
	COLON LPAREN category* RPAREN;

category :
	STRING methodDecl* ;

slotDecl :
	accessModifier? IDENTIFIER (mutableSlotInitializer | immutableSlotInitializer)? ;

mutableSlotInitializer :
	CCE_SIGN expression DOT ;

immutableSlotInitializer :
	EQUAL_SIGN expression DOT ;

methodDecl :
	accessModifier? messagePattern EQUAL_SIGN LPAREN codeBody RPAREN ;

accessModifier :
	'public' | 'protected' | 'private' ;

messagePattern :
	IDENTIFIER
	| BINARY_SELECTOR IDENTIFIER
	| (KEYWORD IDENTIFIER)+ ;

codeBody :
	statement (DOT statement)* DOT?;

statement :
	expression |
	returnStatement ;

returnStatement :
	CARET expression ;

expression :
	block
	| messageSend ;

block :
	LBRACKET blockArgs? blockTemps? codeBody RBRACKET ;

blockArgs :
	BLOCK_ARG* VBAR ;

blockTemps :
	VBAR slotDecl* VBAR ;


messageSend :
	receiverlessSend
	| receiverfulSend ;

receiverlessSend :
	IDENTIFIER ;  // SUPER BOGUS

receiverfulSend :
	receiver IDENTIFIER; // SUPER BOGUS

receiver :
	specialReceiver
	| IDENTIFIER;

specialReceiver :
	NIL
	| TRUE
	| FALSE
	| SELF
	| SUPER
	| OUTER IDENTIFIER;



// NLS 4.1: Reserved words
NIL : 'nil';
TRUE : 'true';
FALSE : 'false';
SELF : 'self';
SUPER : 'super';
OUTER : 'outer';

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

COMMENT : '(*' (.)*? '*)' -> skip;
WS : [ \t\r\n]+ -> skip;
