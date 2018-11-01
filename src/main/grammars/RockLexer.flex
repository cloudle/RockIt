package le.cloud;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import le.cloud.psi.RockTypes;
import com.intellij.psi.TokenType;

%%

%class RockLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

WS 															= [\ \t]+
IDENTIFIER 											= [a-zA-Z\$_]([a-zA-Z_0-9$])*
NUMBER													= (0(x|X)[0-9a-fA-F]+)|([0-9]+(\.[0-9]+)?(e[+\-]?[0-9]+)?)
OPERATOR												= ([+\*&|\/\-%=<>:!?][=+])
WHITESPACE											= ([ \t]+)
COMMENT													= (((\n?[ \t]*)?#[^\n]*)+)
CODE														= ((-|=)>)
MULTI_DENT											= ((\n([ \t]*))+)(\.)?
LAST_DENT												= \n([ \t]*)
ASSIGNMENT											= (:|=)

CHARACTERS_IN_DOUBLE_QUOTES			= ([^\"\r\n\\]+)
CHARACTERS_IN_SINGLE_QUOTES			= ([^\'\r\n\\]+)
LINE_TERMINATOR									= [\n\r]

%state NOUN, DOUBLE_QUOTE_STRING, SINGLE_QUOTE_STRING, REGULAR_EXPRESSION, VERB, REGULAR_EXPRESSION_FLAG, NOUN_OR_VERB, JAVASCRIPT, HEREDOCS

%%

<YYINITIAL> {
	"case"												|
	"default"											|
	"do"													|
	"function"										|
	"var"													|
	"void"												|
	"with"												|
	"const"												|
	"let"													|
	"enum"												|
	"export"											|
	"import"											|
	"native"											|
	"__extends"										|
	"__hasProp"										{ return RockTypes.RESERVED_WORD; }
	{LINE_TERMINATOR}							{ return RockTypes.LINE_TERMINATOR; }
}

<VERB, NOUN_OR_VERB> {
	"+"														|
	"-"														|
	"*"														|
	"%"														|
	"&&"													|
	"||"													|
	"?"														|
	"/"														|
	"++"													|
	"&"														|
	"|"														|
	"--"													|
	"<"														|
	">"														|
	"^"														|
	"~"														|
	"<<"													|
	">>"													|
	">>>"													|
	"::"													|
	"!"														|
	"!!"													|
	"!="													|
	"=="													|
	"<="													|
	">="													|
	".."													|
	"..."													|
	"<-"													|
  "|>"													{ yybegin(NOUN); return RockTypes.OPERATOR; }
	")"														{ return RockTypes.PARENTHESIS; }
	"="														|
	":"														{ yybegin(NOUN); return RockTypes.ASSIGNMENT; }
	"."														{ yybegin(NOUN); return RockTypes.DOT; }
	","														{ yybegin(NOUN); return RockTypes.COMMA; }
	"then"												|
	"in"													{ yybegin(NOUN); return RockTypes.KEYWORD; }
	";"														{ yybegin(NOUN); return RockTypes.SEMI_COLON; }
}

<YYINITIAL, NOUN, VERB, NOUN_OR_VERB> {
	"@"														{ yybegin(NOUN); return RockTypes.ACCESSOR; }
	"if"													|
	"else"												|
	"unless"											|
	"and"													|
	"or"													|
	"is"													|
	"isnt"												|
	"while"												|
	"not"													{ yybegin(NOUN); return RockTypes.KEYWORD; }
	"for"													{ yybegin(NOUN); return RockTypes.KEYWORD; }
	"("														{ yybegin(NOUN); return RockTypes.PARENTHESIS; }
	"["														{ yybegin(NOUN); return RockTypes.BRACKET; }
	{WS}													{ return RockTypes.WHITESPACE; }
	{LINE_TERMINATOR}							{ yybegin(NOUN_OR_VERB); return RockTypes.LINE_TERMINATOR; }
	{COMMENT}											{ return RockTypes.COMMENT; }
	"->"													|
	"=>"													{ yybegin(NOUN); return RockTypes.FUNCTION; }
	"]"														{ yybegin(VERB); return RockTypes.BRACKET; }
	"}"														{ yybegin(VERB); return RockTypes.BRACE; }
}

<YYINITIAL, NOUN, NOUN_OR_VERB> {
	"new"													|
	"return"											|
	"try"													|
	"catch"												|
	"finally"											|
	"throw"												|
	"break"												|
	"continue"										|
	"delete"											|
	"instanceof"									|
	"typeof"											|
	"switch"											|
	"super"												|
	"extends"											|
	"class"												|
	"of"													|
	"by"													|
	"where"												|
	"when"												{ yybegin(NOUN); return RockTypes.KEYWORD; }
	"this"												|
	"null"												{ yybegin(VERB); return RockTypes.KEYWORD; }
	"true"												|
	"false"												|
	"yes"													|
	"no"													|
	"on"													|
	"off"													{ yybegin(VERB); return RockTypes.BOOLEAN; }
	{IDENTIFIER}									{ yybegin(NOUN_OR_VERB); return RockTypes.IDENTIFIER; }
	{NUMBER}											{ yybegin(VERB); return RockTypes.NUMBER; }
	"{"														{ yybegin(NOUN); return RockTypes.BRACE; }
	")"														{ yybegin(VERB); return RockTypes.PARENTHESIS; }
	\"														{ yybegin(DOUBLE_QUOTE_STRING); return RockTypes.STRING; }
	\'														{ yybegin(SINGLE_QUOTE_STRING); return RockTypes.STRING; }
//	"'''"													{ yybegin(HEREDOCS); return RockTypes.HEREDOCS; }
//	"`"														{ yybegin(JAVASCRIPT); return RockTypes.JAVASCRIPT; }
}

<NOUN> {
	"="														{ return RockTypes.ASSIGNMENT; }
//	{REGULAR_EXPRESSION_START}  { yypushback(1); yybegin(REGULAR_EXPRESSION); return RockTypes.REGULAR_EXPRESSION; }
}

<DOUBLE_QUOTE_STRING> {
	\"														{ yybegin(VERB); return RockTypes.STRING; }
	{CHARACTERS_IN_DOUBLE_QUOTES}	{ return RockTypes.STRING; }
}

<SINGLE_QUOTE_STRING> {
	\'														{ yybegin(VERB); return RockTypes.STRING; }
	{CHARACTERS_IN_SINGLE_QUOTES}	{ return RockTypes.STRING; }
}

//CRLF=\R
//WHITE_SPACE=[\ \n\t\f]
//FIRST_VALUE_CHARACTER=[^ \n\f\\] | "\\"{CRLF} | "\\".
//VALUE_CHARACTER=[^\n\f\\] | "\\"{CRLF} | "\\".
//END_OF_LINE_COMMENT=("#"|"!")[^\r\n]*
//SEPARATOR=[:=]
//KEY_CHARACTER=[^:=\ \n\t\f\\] | "\\ "
//
//%state WAITING_VALUE
//
//%%
//
//<YYINITIAL> {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return RockTypes.COMMENT; }
//
//<YYINITIAL> {KEY_CHARACTER}+                                { yybegin(YYINITIAL); return RockTypes.KEY; }
//
//<YYINITIAL> {SEPARATOR}                                     { yybegin(WAITING_VALUE); return RockTypes.SEPARATOR; }
//
//<WAITING_VALUE> {CRLF}({CRLF}|{WHITE_SPACE})+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
//
//<WAITING_VALUE> {WHITE_SPACE}+                              { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }
//
//<WAITING_VALUE> {FIRST_VALUE_CHARACTER}{VALUE_CHARACTER}*   { yybegin(YYINITIAL); return RockTypes.VALUE; }
//
//({CRLF}|{WHITE_SPACE})+                                     { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
//
//[^]                                                         { return TokenType.BAD_CHARACTER; }
