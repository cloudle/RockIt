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
		return if (tokenType == RockTypes.SEPARATOR) {
			SEPARATOR_KEYS
		} else if (tokenType == RockTypes.KEY) {
			KEY_KEYS
		} else if (tokenType == RockTypes.VALUE) {
			VALUE_KEYS
		} else if (tokenType == RockTypes.COMMENT) {
			COMMENT_KEYS
		} else if (tokenType == TokenType.BAD_CHARACTER) {
			BAD_CHAR_KEYS
		} else {
			EMPTY_KEYS
		}
	}

	companion object {
		val SEPARATOR = createTextAttributesKey("SIMPLE_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
		val KEY = createTextAttributesKey("SIMPLE_KEY", DefaultLanguageHighlighterColors.KEYWORD)
		val VALUE = createTextAttributesKey("SIMPLE_VALUE", DefaultLanguageHighlighterColors.STRING)
		val COMMENT = createTextAttributesKey("SIMPLE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
		val BAD_CHARACTER = createTextAttributesKey("SIMPLE_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

		private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
		private val SEPARATOR_KEYS = arrayOf(SEPARATOR)
		private val KEY_KEYS = arrayOf(KEY)
		private val VALUE_KEYS = arrayOf(VALUE)
		private val COMMENT_KEYS = arrayOf(COMMENT)
		private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
	}
}
