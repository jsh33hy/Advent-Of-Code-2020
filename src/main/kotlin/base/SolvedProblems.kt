package base

import day1.ReportRepair1
import day1.ReportRepair2

class SolvedProblems {
    companion object {
        private val unsolvedProblem = object : AocProblem {
            override fun run(): String {
                return "Unsolved Problem"
            }
        }

        private val solvedProblems = mutableMapOf<Int, List<AocProblem>>()

        init {
            //  add day 1 problems
            solvedProblems[1] = listOf(ReportRepair1(), ReportRepair2())
            //  add day 2 problems

            //  add day 3 problems
        }

        fun getProblem(day: Int, problemNumber: Int): AocProblem {
            return if (solvedProblems.containsKey(day)) {
                solvedProblems[day]?.elementAtOrNull(problemNumber - 1) ?: unsolvedProblem
            } else {
                unsolvedProblem
            }
        }
    }

}