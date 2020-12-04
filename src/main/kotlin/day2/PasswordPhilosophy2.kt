package day2

import base.AocProblem
import utils.FileUtils
import utils.JLog

class PasswordPhilosophy2 : AocProblem {
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
            val (firstPosition, secondPosition, charToMatch, password) = matchResult!!.destructured
            //  check for positional matches, NOTE: positions are not 0 indexed, need to -1
            val matchAtFirstPosition = password[firstPosition.toInt()-1].toString() == charToMatch
            val matchAtSecondPosition = password[secondPosition.toInt()-1].toString() == charToMatch
            matchAtFirstPosition.xor(matchAtSecondPosition)

        }.count().toString()
    }
}