package le.cloud

import com.intellij.openapi.project.Project
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiManager
import com.intellij.psi.search.*
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.indexing.FileBasedIndex
import le.cloud.psi.*

import java.util.*

object RockUtil {
	fun findProperties(project: Project, key: String): List<RockProperty> {
		var result: MutableList<RockProperty>? = null
		val virtualFiles = FileBasedIndex.getInstance().getContainingFiles<FileType, Void>(FileTypeIndex.NAME, RockFileType.INSTANCE,
				GlobalSearchScope.allScope(project))
		for (virtualFile in virtualFiles) {
			val rockFile = PsiManager.getInstance(project).findFile(virtualFile) as RockFile?
			if (rockFile != null) {
				val properties = PsiTreeUtil.getChildrenOfType(rockFile, RockProperty::class.java)
				if (properties != null) {
					for (property in properties) {
						if (key == property.getKey()) {
							if (result == null) {
								result = ArrayList<RockProperty>()
							}
							result.add(property)
						}
					}
				}
			}
		}
		return result ?: emptyList<RockProperty>()
	}

	fun findProperties(project: Project): List<RockProperty> {
		val result = ArrayList<RockProperty>()
		val virtualFiles = FileBasedIndex.getInstance().getContainingFiles<FileType, Void>(FileTypeIndex.NAME, RockFileType.INSTANCE,
				GlobalSearchScope.allScope(project))
		for (virtualFile in virtualFiles) {
			val rockFile = PsiManager.getInstance(project).findFile(virtualFile) as RockFile?
			if (rockFile != null) {
				val properties = PsiTreeUtil.getChildrenOfType(rockFile, RockProperty::class.java)
				if (properties != null) {
//					Collections.addAll(result, properties)
					result.addAll(properties)
				}
			}
		}
		return result
	}
}
