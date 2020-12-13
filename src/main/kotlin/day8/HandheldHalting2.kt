package day8

import base.AocProblemSolver

class HandheldHalting2 : AocProblemSolver() {
    val opRegexMatcher = """^(acc|nop|jmp) ([-+]?\d*)${'$'}""".toRegex()

    override fun getInputFileName() = "problem8Input.txt"

    override fun solve(): String {
        val lines = getFileInputAsLinesArray()
        var acc = 0
        val instructionsVisited = mutableSetOf<Int>()
        var instructionAddressPointer = 0
        while (!instructionsVisited.contains(instructionAddressPointer) && instructionAddressPointer != lines.size) {
            instructionsVisited.add(instructionAddressPointer)
            val (op, value) = opRegexMatcher.find(lines[instructionAddressPointer])!!.destructured

            //  try converting the current op, if possible, and then traversing the instruction set
            val resPair = when (op) {
                "nop" -> {
                    logger.debug("swapping $instructionAddressPointer to jmp and traversing")
                    //  convert to jmp and traverse
                    traverse(lines, instructionAddressPointer + value.toInt(), acc, instructionsVisited.toMutableSet())
                }
                "jmp" -> {
                    logger.debug("swapping $instructionAddressPointer to nop and traversing")
                    //  convert to nop and traverse
                    traverse(lines, instructionAddressPointer + 1, acc, instructionsVisited.toMutableSet())
                }
                else -> 0 to false
            }

            //  check for traversal success
            if (resPair.second) {
                return resPair.first.toString()
            }

            //  our swap and traverse was unsuccessful, continue to the next instruction as normal
            when (op) {
                "nop" -> instructionAddressPointer++
                "acc" -> {
                    acc += value.toInt()
                    instructionAddressPointer++
                }
                "jmp" -> instructionAddressPointer += value.toInt()
            }
        }

        //  this shouldn't happen
        return acc.toString()
    }

    //  return Pair<(accumulator value), (does finish)>
    fun traverse(
        allInstructions: List<String>,
        curAddress: Int,
        curAcc: Int,
        visitedInstructions: MutableSet<Int>
    ): Pair<Int, Boolean> {
        var localAddress = curAddress
        var localAcc = curAcc
        while (!visitedInstructions.contains(localAddress) && localAddress != allInstructions.size) {
            visitedInstructions.add(localAddress)
            val (op, value) = opRegexMatcher.find(allInstructions[localAddress])!!.destructured
            when (op) {
                "nop" -> localAddress++
                "acc" -> {
                    localAcc += value.toInt()
                    localAddress++
                }
                "jmp" -> localAddress += value.toInt()
            }
        }
        //  return Pair<(accumulator value), (does finish)>
        return localAcc to (localAddress == allInstructions.size)
    }
}