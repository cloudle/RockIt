package le.cloud

import com.intellij.lexer.FlexAdapter

import java.io.Reader

class RockLexerAdapter : FlexAdapter(RockLexer(null as Reader?))
