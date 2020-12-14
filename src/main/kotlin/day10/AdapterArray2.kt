package day10

import base.AocProblemSolver
import java.math.BigInteger

class AdapterArray2 : AocProblemSolver() {
    override fun getInputFileName() = "problem10Input.txt"

    override fun solve(): String {
        //  get sorted input and add our built in adapter
        val input = getFileInputAsLinesArray().map { Integer.parseInt(it) }
            .toMutableList()

        //  add the initial voltage and our device built in device adapter to the input
        input.addAll(listOf(0, input.maxOrNull()!! + 3))

        //  sort our adapters
        val sortedInput = input.sorted()

        logger.debug(sortedInput.toString())

        return traverse(sortedInput, 3, mutableMapOf()).toString()
    }

    //  this function written WITHOUT a lookup table becomes O(n!)
    private fun traverse(
        input: List<Int>,
        maxDifference: Int,
        solutionsLookupTable: MutableMap<String, BigInteger>
    ) : BigInteger{
        when {
            input.size <= 1 -> {
                return 1.toBigInteger()
            }
            else -> {
                // break into sublists
                //  ex: [..., 4, 5, 6, 7] would break into a list of 3 sublists
                //      [..., 4] + [5 6 7]
                //      [..., 4] + [6 7]
                //      [..., 4] + [7]
                val listOfLists = mutableListOf<List<Int>>()
                val cur = input.first()
                var idx = 1
                while(idx < input.size && (input[idx] - cur <= maxDifference)){
                    listOfLists.add(input.subList(idx, input.size))
                    idx++
                }
                return listOfLists.sumOf {
                    val listKey = "${it.first()}::${it.last()}"
                    if(!solutionsLookupTable.containsKey(listKey)){
                        val sum = traverse(it, maxDifference, solutionsLookupTable)
                        solutionsLookupTable[listKey] = sum
                    }
                    solutionsLookupTable[listKey]!!
                }
            }
        }
    }
}