package le.cloud

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.*

class RockFileType private constructor() : LanguageFileType(RockLanguage.INSTANCE) {
	companion object {
		val INSTANCE = RockFileType()
	}

	override fun getName(): String {
		return "Rock file"
	}

	override fun getDescription(): String {
		return "Rock language file"
	}

	override fun getDefaultExtension(): String {
		return "rock"
	}

	override fun getIcon(): Icon? {
		return RockIcons.FILE
	}
}
