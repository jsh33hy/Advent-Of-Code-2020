import base.AocProblem
import base.SolvedProblems
import day1.ReportRepair2
import utils.JLog


/**
 * Main Solver
 *
 * Expects arguments in the form of [day#] [problem#]
 */
fun main(args: Array<String>) {
    val logger = JLog.getLogger("Main")
    logger.log("Starting with arguments ${args.joinToString()}")

    //  get the problem to solve
    val aocProblem: AocProblem =
            // parse command line args
            if (args.size >= 2 && args[0].toIntOrNull() != null && args[1].toIntOrNull() != null) {
                logger.log("Getting solution for day ${args[0]}, problem ${args[1]}")
                SolvedProblems.getProblem(args[0].toInt(), args[1].toInt())
            } else {
                logger.log("Arguments not given or invalid. Solving for current problem.")
                ReportRepair2()
            }

    logger.log("Solving for ${aocProblem.javaClass.simpleName}")

    val solution = aocProblem.run()
    logger.log(solution)
}