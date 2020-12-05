package day2

import base.AocProblemSolver

// O(n)
class PasswordPhilosophy2 : AocProblemSolver() {
    //  See: https://regex101.com/r/rx6NPr/2
    private val regexMatcher = """(\d+)-(\d+) (.): (.*)${'$'}""".toRegex()

    override fun getInputFileName(): String {
        return "problem2Input.txt"
    }

    override fun solve(): String {
        val linesArray = getFileInputAsLinesArray()
        return countValidPasswords(linesArray)
    }

    private fun countValidPasswords(inputArr: List<String>): String {
        return inputArr.filter { passwordEntry ->
            val matchResult = regexMatcher.find(passwordEntry)
            //  should have a null check here, but we have a fixed input set
            val (firstPosition, secondPosition, charToMatch, password) = matchResult!!.destructured
            //  check for positional matches, NOTE: positions are not 0 indexed, need to -1
            val matchAtFirstPosition = password[firstPosition.toInt() - 1].toString() == charToMatch
            val matchAtSecondPosition = password[secondPosition.toInt() - 1].toString() == charToMatch
            matchAtFirstPosition.xor(matchAtSecondPosition)

        }.count().toString()
    }
}