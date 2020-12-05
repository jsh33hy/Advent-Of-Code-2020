package day1

import base.AocProblemSolver

//  O(n)
class ReportRepair1 : AocProblemSolver() {
    override fun getInputFileName(): String {
        return "problem1Input.txt"
    }

    override fun solve(): String {
        val integerArray = getFileInputAsLinesArray().map { Integer.parseInt(it) }
        return solveTwoSum(2020, integerArray).toString()
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