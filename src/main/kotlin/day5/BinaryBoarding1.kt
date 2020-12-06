package day5

import base.AocProblemSolver
import kotlin.math.pow

open class BinaryBoarding1 : AocProblemSolver() {
    override fun getInputFileName(): String {
        return "problem5Input.txt"
    }

    override fun solve(): String {
        val linesArray = getFileInputAsLinesArray()
        return linesArray.map {
            //  reverse input to put into the form 2^i*(1 || 0)
            //  where i is the char index and on/off depends on the char at position
            val revInput = it.reversed()
            // BBFFBBFRLL -> LLRFBBFFBB
            val column = convertBoardingStrToInt(revInput.substring(3), 'B', 'F')
            //  FBBFFBB -> 1100110 = 102
            val row = convertBoardingStrToInt(revInput.substring(0, 3), 'R', 'L')
            //  LLR -> 100 = 4
            (column * 8) + row
            //  102 * 8 + 4 = 820
        }
        .maxOrNull()!!.toString()
    }

    protected fun convertBoardingStrToInt(inputStr: String, onChar: Char, offChar: Char): Int{
        return inputStr.mapIndexed { index, c ->
            // 2^index * 1 or 0 depending on B or F
            2.0.pow(index) * when(c){
                onChar -> 1
                offChar -> 0
                else -> 0
            }
        }
        .sum().toInt()
    }
}