package le.cloud

import com.intellij.lang.*
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.tree.*
import le.cloud.parser.RockParser
import le.cloud.psi.*

class RockParserDefinition : ParserDefinition {
	companion object {
		val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
		val COMMENTS = TokenSet.create(RockTypes.COMMENT)

		val FILE = IFileElementType(RockLanguage.INSTANCE)
	}

	override fun createLexer(project: Project): Lexer {
		return RockLexerAdapter()
	}

	override fun getWhitespaceTokens(): TokenSet {
		return WHITE_SPACES
	}

	override fun getCommentTokens(): TokenSet {
		return COMMENTS
	}

	override fun getStringLiteralElements(): TokenSet {
		return TokenSet.EMPTY
	}

	override fun createParser(project: Project): PsiParser {
		return RockParser()
	}

	override fun getFileNodeType(): IFileElementType {
		return FILE
	}

	override fun createFile(viewProvider: FileViewProvider): PsiFile {
		return RockFile(viewProvider)
	}

	override fun spaceExistanceTypeBetweenTokens(left: ASTNode?, right: ASTNode?): ParserDefinition.SpaceRequirements {
		return ParserDefinition.SpaceRequirements.MAY
	}

	override fun createElement(node: ASTNode): PsiElement {
		return RockTypes.Factory.createElement(node)
	}
}
