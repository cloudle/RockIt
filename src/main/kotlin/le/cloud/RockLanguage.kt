package le.cloud

import com.intellij.lang.Language

class RockLanguage private constructor() : Language("Rock") {
	companion object {
		val INSTANCE = RockLanguage()
	}
}
