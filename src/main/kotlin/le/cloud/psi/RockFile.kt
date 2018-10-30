package le.cloud.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import le.cloud.*

import javax.swing.*

class RockFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, RockLanguage.INSTANCE) {

	override fun getFileType(): FileType {
		return RockFileType.INSTANCE
	}

	override fun toString(): String {
		return "Rock File"
	}

	override fun getIcon(flags: Int): Icon? {
		return super.getIcon(flags)
	}
}
