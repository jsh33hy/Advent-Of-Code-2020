package day7

import base.AocProblemSolver

class HandyHaversacks1 : AocProblemSolver() {
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
                                val bagContentsMatchResult = numBagsBagTypeRegexMatcher.find(curContentsString)
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
        //  1. remove search key for the shiny gold bag type
        //  2. for each remaining bagType, recursively see if there is some path that can contain
        //      this shiny gold bag
        return (bagTypeContentsMap.keys - listOf(searchString)).count {
            searchForBag(bagTypeContentsMap, it, searchString)
        }.toString()
    }

    private fun searchForBag(bagMap: Map<String, Set<BagContents>>, curColor: String, searchColor: String): Boolean {
        //  check if our curColor is the search color, or recursively see if any of our contained bags
        //  contain the search color
       return (searchColor == curColor) || bagMap[curColor]!!.any { color ->
            searchForBag(bagMap, color.bagType, searchColor)
        }
    }

    data class BagContents(
        val numBags : Int,
        val bagType: String
    )
}