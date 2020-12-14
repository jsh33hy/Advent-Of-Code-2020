package day10

import base.AocProblemSolver

class AdapterArray1 : AocProblemSolver() {
    override fun getInputFileName() = "problem10Input.txt"

    override fun solve(): String {
        //  get sorted input and add our built in adapter
        val input = getFileInputAsLinesArray().map { Integer.parseInt(it) }
            .toMutableList()

        //  add the initial voltage and our device built in device adapter to the input
        input.addAll(listOf(0, input.maxOrNull()!! + 3))

        //  sort our adapters
        val sortedInput = input.sorted()

        val mappedOutput = sortedInput
        //  map voltage jump
        .mapIndexed { index, curVal ->
            if(index == 0){
                0
            }
            else{
                curVal - sortedInput[index-1]
            }
        }
        .groupBy { it }
        .map { it.key to it.value.size }
        //  Pair<(jolt difference), (number of differences)>
        .toMap()
        //  Map<(jolt difference val), (number of differences)>


        logger.debug(mappedOutput.toString())

        //  add 1 to 3 jolt different to account for our built in device
        return ((mappedOutput[1]!!) * (mappedOutput[3]!!)).toString()
    }
}