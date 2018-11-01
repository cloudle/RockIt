package le.cloud

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.*
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import le.cloud.psi.RockTypes

import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey

class RockSyntaxHighlighter : SyntaxHighlighterBase() {

	override fun getHighlightingLexer(): Lexer {
		return RockLexerAdapter()
	}

	override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
		return when (tokenType) {
			RockTypes.IDENTIFIER			-> IDENTIFIER_KEYS
			RockTypes.OPERATOR				-> OPERATOR_KEYS
			RockTypes.KEYWORD					-> KEYWORD_KEYS
			RockTypes.NUMBER					-> NUMBER_KEYS
			RockTypes.STRING					-> STRING_KEYS
			RockTypes.BOOLEAN					-> KEYWORD_KEYS
			RockTypes.SEPARATOR				-> SEPARATOR_KEYS
			RockTypes.BRACE						-> BRACE_KEYS
			RockTypes.PARENTHESIS			-> PARENTHESIS_KEYS
			RockTypes.KEY							-> KEY_KEYS
			RockTypes.VALUE						-> VALUE_KEYS
			RockTypes.COMMENT					-> COMMENT_KEYS
			TokenType.BAD_CHARACTER		-> BAD_CHAR_KEYS
			else											-> EMPTY_KEYS
		}
	}

	companion object {
		val IDENTIFIER = createTextAttributesKey("ROCK_IDENTIFIER", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE)
		val OPERATOR = createTextAttributesKey("ROCK_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
		val KEYWORD = createTextAttributesKey("ROCK_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
		val NUMBER = createTextAttributesKey("ROCK_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
		val STRING = createTextAttributesKey("ROCK_STRING", DefaultLanguageHighlighterColors.STRING)
		val SEPARATOR = createTextAttributesKey("ROCK_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
		val BRACE = createTextAttributesKey("ROCK_BRACE", DefaultLanguageHighlighterColors.BRACES)
		val PARENTHESIS = createTextAttributesKey("ROCK_PARENTHESIS", DefaultLanguageHighlighterColors.PARENTHESES)
		val KEY = createTextAttributesKey("ROCK_KEY", DefaultLanguageHighlighterColors.KEYWORD)
		val VALUE = createTextAttributesKey("ROCK_VALUE", DefaultLanguageHighlighterColors.STRING)
		val COMMENT = createTextAttributesKey("ROCK_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
		val BAD_CHARACTER = createTextAttributesKey("ROCK_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

		private val IDENTIFIER_KEYS = arrayOf(IDENTIFIER)
		private val OPERATOR_KEYS = arrayOf(OPERATOR)
		private val KEYWORD_KEYS = arrayOf(KEYWORD)
		private val NUMBER_KEYS = arrayOf(NUMBER)
		private val STRING_KEYS = arrayOf(STRING)
		private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
		private val SEPARATOR_KEYS = arrayOf(SEPARATOR)
		private val BRACE_KEYS = arrayOf(BRACE)
		private val PARENTHESIS_KEYS = arrayOf(PARENTHESIS)
		private val KEY_KEYS = arrayOf(KEY)
		private val VALUE_KEYS = arrayOf(VALUE)
		private val COMMENT_KEYS = arrayOf(COMMENT)
		private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
	}
}
