package base

import day1.ReportRepair1
import day1.ReportRepair2
import day2.PasswordPhilosophy1
import day2.PasswordPhilosophy2
import day3.TobogganTrajectory1
import day3.TobogganTrajectory2

class AocProblemSolverFactory {
    companion object {
        private val unsolvedProblem = object : AocProblemSolver() {
            override fun getInputFileName(): String {
                return "NoFile.txt"
            }
            override fun solve(): String {
                return "Unsolved Problem"
            }
        }

        //  could be a spare array
        private val solvedProblems = mutableMapOf<Int, List<AocProblemSolver>>()

        init {
            //  add day 1 problems
            solvedProblems[1] = listOf(ReportRepair1(), ReportRepair2())
            //  add day 2 problems
            solvedProblems[2] = listOf(PasswordPhilosophy1(), PasswordPhilosophy2())
            //  add day 3 problems
            solvedProblems[3] = listOf(TobogganTrajectory1(), TobogganTrajectory2())
        }

        fun getSolver(day: Int, problemNumber: Int): AocProblemSolver {
            return if (solvedProblems.containsKey(day)) {
                solvedProblems[day]?.elementAtOrNull(problemNumber - 1) ?: unsolvedProblem
            } else {
                unsolvedProblem
            }
        }
    }

}