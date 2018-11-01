package le.cloud

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.*
import org.jetbrains.annotations.*

import javax.swing.*

class RockColorSettingsPage : ColorSettingsPage {

	override fun getIcon(): Icon? {
		return RockIcons.FILE
	}

	override fun getHighlighter(): SyntaxHighlighter {
		return RockSyntaxHighlighter()
	}

	override fun getDemoText(): String {
		return "# You are reading the \".properties\" entry.\n" +
			"# The exclamation mark can also mark text as comments.\n" +
			"website = \"http://en.wikipedia.org/\"\n" +
			"language = 10"
	}

	override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? {
		return null
	}

	override fun getAttributeDescriptors(): Array<AttributesDescriptor> {
		return DESCRIPTORS
	}

	override fun getColorDescriptors(): Array<ColorDescriptor> {
		return ColorDescriptor.EMPTY_ARRAY
	}

	override fun getDisplayName(): String {
		return "Rock"
	}

	companion object {
		private val DESCRIPTORS = arrayOf(
				AttributesDescriptor("Key", RockSyntaxHighlighter.KEY),
				AttributesDescriptor("Separator", RockSyntaxHighlighter.SEPARATOR),
				AttributesDescriptor("Value", RockSyntaxHighlighter.VALUE),
				AttributesDescriptor("Number", RockSyntaxHighlighter.NUMBER),
				AttributesDescriptor("String", RockSyntaxHighlighter.STRING),
				AttributesDescriptor("Operator", RockSyntaxHighlighter.OPERATOR),
				AttributesDescriptor("Keyword", RockSyntaxHighlighter.KEYWORD),
				AttributesDescriptor("Braces", RockSyntaxHighlighter.BRACE),
				AttributesDescriptor("Parenthesis", RockSyntaxHighlighter.PARENTHESIS),
				AttributesDescriptor("Identifier", RockSyntaxHighlighter.IDENTIFIER))
	}
}
