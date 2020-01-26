/* simple grammar for parsing transaction from input file with ASS output */
grammar Transactions;

/* parser rules, set of supported operations */
start
    : name=text COMMA WS* partner=ID WS* SLASH WS* phone=NUMBER WS* COMMA WS* date=DATE WS* time=TIME eol
    ;

text
    : TEXT
    | QUOT TEXT QUOT
    ;

eol
    : EOL
    | EOF
    | WS*
    ;

/* lexer rules, atomic numbers */
ID     : (WORD | DIGIT)+;
TEXT   : (WORD | DIGIT | COMMA | DOT | WS)+;
NUMBER : DIGIT+;
DATE   : FOURDIGIT DASH TWODIGIT DASH TWODIGIT;
TIME   : TWODIGIT COLON TWODIGIT COLON TWODIGIT;

/* separators */
COMMA  : ',';
SLASH  : '/';
QUOT   : '"';

/* whitespaces */
WS      : [ \t];
EOL     : '\r'? '\n' | '\r';

/* fragments */
fragment DASH      : '-';
fragment COLON     : ':';
fragment DOT       : '.';
fragment DIGIT     : [0-9];
fragment WORD      : [A-Za-z];
fragment TWODIGIT  : DIGIT DIGIT;
fragment FOURDIGIT : DIGIT DIGIT DIGIT DIGIT;
