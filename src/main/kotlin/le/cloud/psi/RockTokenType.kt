package le.cloud.psi

import com.intellij.psi.tree.IElementType
import le.cloud.RockLanguage
import org.jetbrains.annotations.*

class RockTokenType(@NonNls debugName: String) : IElementType(debugName, RockLanguage.INSTANCE) {

	override fun toString(): String {
		return "RockTokenType." + super.toString()
	}
}
