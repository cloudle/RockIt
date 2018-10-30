package le.cloud.psi

import com.intellij.psi.tree.IElementType
import le.cloud.RockLanguage
import org.jetbrains.annotations.*

class RockElementType(@NonNls debugName: String) : IElementType(debugName, RockLanguage.INSTANCE)
