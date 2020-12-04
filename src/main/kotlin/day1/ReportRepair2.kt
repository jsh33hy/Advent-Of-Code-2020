package day1

import base.AocProblem
import utils.FileUtils
import utils.JLog


//  O(n^2)
class ReportRepair2 : AocProblem {
    private val logger = JLog.getLogger(this.javaClass.simpleName)

    override fun run(): String {
        val fileInput = FileUtils.readFile("problem1.txt")
        val linesArray = fileInput.split("\n").map { Integer.parseInt(it) }
        return solveThreeSum(2020, linesArray).toString()
    }

    private fun solveThreeSum(sumValue: Int, inputArr: List<Int>): Int {
        val hashSet = mutableSetOf<Int>()
        for (i in inputArr.indices) {
            val targetNum = sumValue - inputArr[i]
            for (j in i + 1 until inputArr.size) {
                if (hashSet.contains(targetNum - inputArr[j])) {
                    logger.log("Found 3 numbers ${(targetNum - inputArr[j])}, ${inputArr[i]}, ${inputArr[j]}")
                    return (targetNum - inputArr[j]) * inputArr[i] * inputArr[j]
                }
                hashSet.add(inputArr[j])
            }
        }
        logger.log("Do results found")
        return -1
    }
}