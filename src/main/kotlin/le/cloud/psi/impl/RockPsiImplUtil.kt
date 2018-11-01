package le.cloud.psi.impl

import le.cloud.psi.*

object RockPsiImplUtil {
	@JvmStatic
	fun getKey(element: RockProperty): String? {
		val keyNode = element.node.findChildByType(RockTypes.KEY)
		return keyNode?.text?.replace("\\\\ ".toRegex(), " ")
	}

	@JvmStatic
	fun getValue(element: RockProperty): String? {
		val valueNode = element.node.findChildByType(RockTypes.VALUE)
		return valueNode?.text
	}
}
