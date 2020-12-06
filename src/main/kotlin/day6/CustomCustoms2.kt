package day6

import base.AocProblemSolver

class CustomCustoms2 : AocProblemSolver() {
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

        return parsedInput.map{groupQuestions ->
            //  group distinct questions answered by anyone in the group into a single string
            //  fbqjswm;qmbjwfs;fmsbjwq;smjbqwf;hwsqmbfj -> fbqjswmh
            val distinctGroupQuestionsAnswered = groupQuestions.replace(";","").toCharArray().distinct()

            //  split the group questions into individuals
            //  fbqjswm;qmbjwfs;fmsbjwq;smjbqwf;hwsqmbfj -> [fbqjswm, qmbjwfs, fmsbjwq, smjbqwf, hwsqmbfj]
            val individualQuestionsArr = groupQuestions.split(";")

            //  loop through the distinct questions and look for ones that were answered by EVERYONE
            // (f, fbqjswm, qmbjwfs, fmsbjwq, smjbqwf, hwsqmbfj) -> true -> 1
            distinctGroupQuestionsAnswered.map { currentQuestion ->
                //  split the group questions into individuals
                individualQuestionsArr.all { individualQuestions ->
                    individualQuestions.contains(currentQuestion)
                }

            }.map { if (it) 1 else 0}.sum()
        }
        .sum().toString()
    }
}