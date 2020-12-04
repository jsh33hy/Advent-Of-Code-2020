package day1

import base.AocProblem
import utils.FileUtils
import utils.JLog

//  O(n)
class ReportRepair1 : AocProblem {
    private val logger = JLog.getLogger(this.javaClass.simpleName)

    override fun run(): String {
        val fileInput = FileUtils.readFile("problem1.txt")
        val linesArray = fileInput.split("\n").map { Integer.parseInt(it) }
        return solveTwoSum(2020, linesArray).toString()
    }

    private fun solveTwoSum(sumValue: Int, inputArr: List<Int>): Int {
        val hashSet = mutableSetOf<Int>()
        inputArr.forEach { curVal ->
            if (hashSet.contains(sumValue - curVal)) {
                logger.log("Found 2 numbers ${(sumValue - curVal)}, $curVal")
                return (sumValue - curVal) * curVal
            }
            hashSet.add(curVal)
        }
        logger.log("No results found")
        return -1
    }
}