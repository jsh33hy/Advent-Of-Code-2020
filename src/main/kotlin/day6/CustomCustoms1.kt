package day6

import base.AocProblemSolver

class CustomCustoms1 : AocProblemSolver() {
    override fun getInputFileName(): String {
        return "problem6Input.txt"
    }

    override fun solve(): String {
        val fileInput = getRawFileInput()
        //  group questions for each group together into 1 string, separate personal answers by ;
        //  answers for one group with 5 people would be one item in the array and look like
        //  fbqjswm;qmbjwfs;fmsbjwq;smjbqwf;hwsqmbfj
        val parsedInput = fileInput.split("\n\n").map {
            //  replace spaces and new lines with ;
            it.replace("\\s+".toRegex(), ";")
        }

        //  view output
        logger.debug(parsedInput.toString())

        return parsedInput.map{
            //  group all questions together and count the number of unique questions answered
            //  fbqjswm;qmbjwfs;fmsbjwq;smjbqwf;hwsqmbfj -> fbqjswmh -> 8
            it.replace(";","").toCharArray().distinct().size
        }
        .sum().toString()
    }
}