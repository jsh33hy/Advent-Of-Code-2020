package day3

import base.AocProblemSolver

// O(n)
open class TobogganTrajectory1 : AocProblemSolver() {
    override fun getInputFileName(): String {
        return "problem3Input.txt"
    }

    override fun solve(): String {
        val linesArray = getInputAsLineList()
        return countTreeHits(linesArray, 3, 1).toString()
    }

    protected fun countTreeHits(inputArr: List<String>, xDrift: Int, yDrift: Int): Int {
        var treeHits = 0
        var xPos = 0
        var yPos = 0
        while(yPos < inputArr.size){
            //  size of line 31 and sequence repeats
            val adjustedXPos = xPos%31
            if(inputArr[yPos][adjustedXPos] == '#'){
                treeHits+=1
            }
            xPos+=xDrift
            yPos+=yDrift
        }
        return treeHits
    }
}