package day2

import base.AocProblemSolver

// O(n)
class PasswordPhilosophy1 : AocProblemSolver() {
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
            val (minOccurrences, maxOccurrences, charToMatch, password) = matchResult!!.destructured
            //  this is likely faster than creating a regex for each password entry
            val charCount = password.filter { it.toString() == charToMatch }.count()
            //  comparse char counts to min/max occurrences
            (charCount >= minOccurrences.toInt() && charCount <= maxOccurrences.toInt())
        }.count().toString()
    }
}