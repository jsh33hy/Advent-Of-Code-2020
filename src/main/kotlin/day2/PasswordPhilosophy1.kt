package day2

import base.AocProblem
import utils.FileUtils
import utils.JLog

class PasswordPhilosophy1 : AocProblem {
    private val logger = JLog.getLogger(this.javaClass.simpleName)

    //  See: https://regex101.com/r/rx6NPr/2
    private val regexMatcher = """(\d+)-(\d+) (.): (.*)${'$'}""".toRegex()

    override fun run(): String {
        val fileInput = FileUtils.readFile("problem2.txt")
        val linesArray = fileInput.split("\n").map { it.trim() }
        return countValidPasswords(linesArray)
    }

    private fun countValidPasswords(inputArr: List<String>): String {
        return inputArr.filter { passwordEntry ->
            val matchResult = regexMatcher.find(passwordEntry)
            //  should have a null check here, but we have a fixed input set
            val (minOccurrences, maxOccurrences, charToMatch, password) = matchResult!!.destructured
            //  this is likely faster than creating a regex for each password entry
            val charCount = password.filter { it.toString() == charToMatch }.count()
            //  comparse char counts to min/max occurrences
            (charCount >= minOccurrences.toInt() && charCount <= maxOccurrences.toInt())
        }.count().toString()
    }
}