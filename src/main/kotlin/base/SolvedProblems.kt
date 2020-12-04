package base

import day1.ReportRepair1
import day1.ReportRepair2
import day2.PasswordPhilosophy1
import day2.PasswordPhilosophy2
import day3.TobogganTrajectory1
import day3.TobogganTrajectory2

class SolvedProblems {
    companion object {
        private val unsolvedProblem = object : AocProblem {
            override fun run(): String {
                return "Unsolved Problem"
            }
        }

        //  could be a spare array
        private val solvedProblems = mutableMapOf<Int, List<AocProblem>>()

        init {
            //  add day 1 problems
            solvedProblems[1] = listOf(ReportRepair1(), ReportRepair2())
            //  add day 2 problems
            solvedProblems[2] = listOf(PasswordPhilosophy1(), PasswordPhilosophy2())
            //  add day 3 problems
            solvedProblems[3] = listOf(TobogganTrajectory1(), TobogganTrajectory2())
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