package day9

import base.AocProblemSolver
import java.math.BigInteger

class EncodingError1 : AocProblemSolver() {
    override fun getInputFileName() = "problem9Input.txt"

    override fun solve(): String {
        val windowSize = 25
        val inputArr =  getFileInputAsLinesArray().map { it.toBigInteger() }
        //  for each index i, create a sub array of [i,i+windowSize) and solve two sum for the value at i+windowSize
        inputArr.forEachIndexed { index, _ ->
            if(index+windowSize >= inputArr.size){
                return (-1).toString()
            }
            val canSolve = solveTwoSum(inputArr.subList(index, index+windowSize), inputArr[index+windowSize])

            //  if two sum cannot be solved, our invalid value is at i+windowSize
            if(!canSolve){
                return inputArr[index+windowSize].toString()
            }
        }
        return "Cannot solve"
    }

    //  standard two sum solver
    private fun solveTwoSum(input: List<BigInteger>, sumValue: BigInteger): Boolean{
        val valueSet = mutableSetOf<BigInteger>()
        input.forEach {
            if(valueSet.contains(sumValue - it)){
                return true
            }
            valueSet.add(it)
        }
        return false
    }
}