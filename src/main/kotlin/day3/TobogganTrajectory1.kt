package day3

import base.AocProblem
import utils.FileUtils
import utils.JLog

// O(n)
open class TobogganTrajectory1 : AocProblem {
    protected val logger = JLog.getLogger(this.javaClass.simpleName)

    override fun run(): String {
        val fileInput = FileUtils.readFile("problem3.txt")
        val linesArray = fileInput.split("\n").map { it.trim() }
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