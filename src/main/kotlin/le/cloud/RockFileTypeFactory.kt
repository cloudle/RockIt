package le.cloud

import com.intellij.openapi.fileTypes.*

class RockFileTypeFactory : FileTypeFactory() {
	override fun createFileTypes(fileTypeConsumer: FileTypeConsumer) {
		fileTypeConsumer.consume(RockFileType.INSTANCE)
	}
}
