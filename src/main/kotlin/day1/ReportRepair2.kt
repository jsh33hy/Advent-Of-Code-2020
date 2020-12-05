package day1

import base.AocProblemSolver

//  O(n^2)
class ReportRepair2 : AocProblemSolver() {

    override fun getInputFileName(): String {
        return "problem1Input.txt"
    }

    override fun solve(): String {
        val integerArray = getFileInputAsLinesArray().map { Integer.parseInt(it) }
        return solveThreeSum(2020, integerArray).toString()
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
        logger.log("No results found")
        return -1
    }
}