package utils

import java.io.File

class FileUtils {
    companion object {
        private const val resourcesDir = "src/main/resources/"

        fun readFile(fileName: String): String {
            return File(resourcesDir + fileName).readText()
        }
    }
}