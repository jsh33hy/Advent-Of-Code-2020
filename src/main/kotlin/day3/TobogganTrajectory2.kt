package day3

import java.math.BigInteger

// O(n)
class TobogganTrajectory2 : TobogganTrajectory1() {

    override fun solve(): String {
        val linesArray = getInputAsLineList()

        //  list of Pair(xDrift, yDrift)
        val tobogganDriftAmounts = listOf(
                1 to 1,
                3 to 1,
                5 to 1,
                7 to 1,
                1 to 2
        )

        //  compute the tree hits for each toboggan run with the specified drift amounts
        return tobogganDriftAmounts.map { driftPair ->
            //  need to use BigInts here
            val treeHits = countTreeHits(linesArray, driftPair.first, driftPair.second).toBigInteger()
            logger.log("Tree hits = $treeHits for drift values (${driftPair.first}, ${driftPair.second})")
            treeHits
        }.reduce(BigInteger::multiply).toString()
    }
}