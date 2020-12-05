package base

import utils.FileUtils
import utils.JLog

abstract class AocProblemSolver {
    protected val logger = JLog.getLogger(this.javaClass.simpleName)

    abstract fun getInputFileName(): String
    abstract fun solve(): String

    protected fun getFileInputAsLinesArray(): List<String> {
        val fileInput = FileUtils.readFile(getInputFileName())
        return fileInput.split("\n").map { it.trim() }
    }

    protected fun getRawFileInput(): String{
        return FileUtils.readFile(getInputFileName())
    }
}