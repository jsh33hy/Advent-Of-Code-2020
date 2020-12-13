package day8

import base.AocProblemSolver

class HandheldHalting1 : AocProblemSolver() {
    val opRegexMatcher = """^(acc|nop|jmp) ([-+]?\d*)${'$'}""".toRegex()

    override fun getInputFileName() = "problem8Input.txt"

    override fun solve(): String {
        val lines = getFileInputAsLinesArray()
        var acc = 0
        val instructionsVisited = mutableSetOf<Int>()
        var instructionAddressPointer = 0
        while(!instructionsVisited.contains(instructionAddressPointer)){
            instructionsVisited.add(instructionAddressPointer)
            logger.log(lines[instructionAddressPointer])
            val (op, value) = opRegexMatcher.find(lines[instructionAddressPointer])!!.destructured
            when(op){
                "nop" -> instructionAddressPointer++
                "acc" -> {
                    acc+=value.toInt()
                    instructionAddressPointer++
                }
                "jmp" -> instructionAddressPointer+=value.toInt()
            }
        }

        return acc.toString()
    }
}