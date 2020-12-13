package day9

import base.AocProblemSolver
import java.math.BigInteger

class EncodingError2 : AocProblemSolver() {
    override fun getInputFileName()= "problem9Input.txt"

    override fun solve(): String {
        //  get the invalid value from part1
        val invalidValue = solvePart1().toBigInteger()

        //  get the index range Pair<(subarr start index), (subarr end index)> of values that sum to the invalid value
        val inputArr =  getFileInputAsLinesArray().map { it.toBigInteger() }
        val indicesPair = solveSubArraySum(inputArr, invalidValue)
        logger.debug(indicesPair.toString())

        //  get the sub array that sums to the invalid value
        val subsetArr = inputArr.subList(indicesPair.first, indicesPair.second+1)

        //  return the sum of the min + max value in that sub array
        return (subsetArr.minOrNull()!!.add(subsetArr.maxOrNull()!!)).toString()
    }

    //  brute force, too lazy for dynamic programming, returns Pair<(subarr start index), (subarr end index)>
    private fun solveSubArraySum(input: List<BigInteger>, sumValue: BigInteger) : Pair<Int, Int> {
        var cur : BigInteger
        for(i in input.indices){
            cur = input[i]
            for (k in i+1 until input.size -1){
                cur += input[k]
                if(cur == sumValue){
                    return i to k
                }
            }
        }
        return -1 to - 1
    }

    private fun solvePart1(): String {
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