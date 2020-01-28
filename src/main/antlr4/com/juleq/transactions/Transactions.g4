/* simple grammar for parsing transaction from input file with ASS output */
grammar Transactions;

/* parser rules, set of supported operations */
start
    : name=literal COMMA partner=ID SLASH phone=NUMBER COMMA date=DATE time=TIME EOF
    ;

literal
    : '"' (ID|COMMA|DOT)+ '"'
    ;

/* lexer rules, atomic numbers */
ID           : LETTER (LETTER|DIGIT|WHITESPACE)+ (LETTER|DIGIT);
NUMBER       : DIGIT+;
DATE         : FOURDIGIT DASH TWODIGIT DASH TWODIGIT;
TIME         : TWODIGIT COLON TWODIGIT COLON TWODIGIT;

/* separators */
DOT          : '.';
COMMA        : ',';
SLASH        : '/';
QUOT         : '"';

/* whitespaces */
WS           : WHITESPACE+ -> skip;

/* fragments */
fragment DASH       : '-';
fragment COLON      : ':';
fragment DIGIT      : [0-9];
fragment LETTER     : [A-Za-z];
fragment TWODIGIT   : DIGIT DIGIT;
fragment FOURDIGIT  : DIGIT DIGIT DIGIT DIGIT;
fragment WHITESPACE : [ \r\n\t];