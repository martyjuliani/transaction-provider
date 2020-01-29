/* simple grammar for parsing transaction from input file with ASS output */
grammar Transactions;

/* parser rules */
start
    : name=text COMMA WS* partner=id WS* SLASH WS* phone=number WS* COMMA WS* date=DATE WS* time=TIME EOF
    ;

text
    : literal
    | (LETTER|DIGIT|DOT|WS)+
    ;

literal
    : '"' (LETTER|DIGIT|COMMA|DOT|WS)+ '"'
    ;

id  : (LETTER|DIGIT)+
    ;

number
    : DIGIT+
    ;

/* lexer rules */
DATE         : FOURDIGIT DASH TWODIGIT DASH TWODIGIT;
TIME         : TWODIGIT COLON TWODIGIT COLON TWODIGIT;

/* separators */
DOT          : '.';
COMMA        : ',';
SLASH        : '/';
QUOT         : '"';
DIGIT        : [0-9];
LETTER       : [A-Za-z];
WS           : [ \r\n\t];

/* fragments */
fragment DASH       : '-';
fragment COLON      : ':';
fragment TWODIGIT   : DIGIT DIGIT;
fragment FOURDIGIT  : DIGIT DIGIT DIGIT DIGIT;