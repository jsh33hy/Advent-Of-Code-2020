package day7

import base.AocProblemSolver

class HandyHaversacks2 : AocProblemSolver() {
    private val bagKeySetRegexMatcher = """(.*) contain (.*)${'$'}""".toRegex()
    private val numBagsBagTypeRegexMatcher = """(\d) (.*)${'$'}""".toRegex()


    override fun getInputFileName(): String {
        return "problem7Input.txt"
    }


    // TODO: 12/7/20 Memoize
    override fun solve(): String {
        val linesArr = getFileInputAsLinesArray()

        //  This is a hideous chain
        val bagTypeContentsMap = linesArr.map { fullLineInput ->
            // muted tomato bags contain 1 bright brown bag, 1 dotted gold bag, 2 faded gray bags, 1 posh yellow bag.

            //  split by bagType / contents
            val bagsStringMatchResult = bagKeySetRegexMatcher.find(fullLineInput)
            val (bagTypeString, bagContentsString) = bagsStringMatchResult!!.destructured

            //  at this point (bagTypeString, BagContentsString) =
            // (muted tomato bag, (1 bright brown bag, 1 dotted gold bag, 2 faded gray bags, 1 posh yellow bag.))

            //  create a map (K,V) of (BagType (String), BagContents (Set<BagContents>))
            //  replace bags with bag because of grammar for 1 vs many
            bagTypeString.replace("bags", "bag") to
                    //  remove periods and replace bags with bag because of grammar for 1 vs many
                    bagContentsString.replace(".", "").replace("bags", "bag")
                        // split into sub bag contents
                        // (1 bright brown bag, 1 dotted gold bag, 2 faded gray bag, 1 posh yellow bag) -> 1 bright brown bag
                        .split(",").map { curContentsString ->
                            if (curContentsString.contains("no other bag")) {
                                //  handle the case with no contents
                                null
                            } else {
                                val bagContentsMatchResult =
                                    numBagsBagTypeRegexMatcher.find(curContentsString)
                                val (numBags, bagType) = bagContentsMatchResult!!.destructured
                                BagContents(numBags.toInt(), bagType)
                            }
                        }
                        //  keep empty bags out of the set
                        .filterNotNull()
                        .toSet()
        }
        .toMap()
        //  map contains (K,V) = (each bag type (String), their contents (Set<BagContents>) in the form of
        //  {muted tomato bag=[BagContents(numBags=1, bagType=bright brown bag), BagContents(numBags=1, bagType=dotted gold bag), BagContents(numBags=2, bagType=faded gray bag), BagContents(numBags=1, bagType=posh yellow bag)]

        val searchString = "shiny gold bag"
        //  recursively count the number of contained bags, subtract one to remove our initial shiny gold bag
        return (countContainedBags(bagTypeContentsMap, searchString) - 1).toString()
    }

    private fun countContainedBags(
        bagMap: Map<String, Set<BagContents>>,
        searchColor: String
    ): Int {
        //  recursively count the number of containing bags for the given searchColor (bag type)
        return 1 + bagMap[searchColor]!!.sumBy {
            it.numBags * countContainedBags(bagMap, it.bagType)
        }
    }

    data class BagContents(
        val numBags: Int,
        val bagType: String
    )
}