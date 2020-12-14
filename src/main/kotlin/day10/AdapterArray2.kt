package day10

import base.AocProblemSolver
import java.math.BigInteger

class AdapterArray2 : AocProblemSolver() {
    override fun getInputFileName() = "problem10Input.txt"
    private val solvedMap = mutableMapOf<String,BigInteger>()

    override fun solve(): String {
        //  get sorted input and add our built in adapter
        val input = getFileInputAsLinesArray().map { Integer.parseInt(it) }
            .toMutableList()

        //  add the initial voltage and our device built in device adapter to the input
        input.addAll(listOf(0, input.maxOrNull()!! + 3))

        //  sort our adapters
        val sortedInput = input.sorted()

        logger.log(sortedInput.toString())

        return traverse(sortedInput, 3).toString()
    }

    private fun traverse(input: List<Int>, maxDifference: Int) : BigInteger{
        when {
            solvedMap.containsKey("${input.first()}::${input.last()}") -> {
                return solvedMap["${input.first()}::${input.last()}"]!!
            }
            input.size <= 1 -> {
                return 1.toBigInteger()
            }
            else -> {
                val listOfLists = mutableListOf<List<Int>>()
                val cur = input.first()
                var idx = 1
                while(idx < input.size && (input[idx] - cur <= maxDifference)){
                    listOfLists.add(input.subList(idx, input.size))
                    idx++
                }

                return listOfLists.sumOf {
                    val sum = traverse(it, maxDifference)
                    solvedMap["${it.first()}::${it.last()}"] = sum
                    sum
                }
            }
        }
    }
}