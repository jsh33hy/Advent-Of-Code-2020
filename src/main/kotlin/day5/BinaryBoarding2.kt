package day5

class BinaryBoarding2 : BinaryBoarding1() {
    override fun getInputFileName(): String {
        return "problem5Input.txt"
    }

    override fun solve(): String {
        val linesArray = getFileInputAsLinesArray()
        val sortedSeats = linesArray.map {
            //  reverse input to put into the form 2^i*(1 || 0)
            //  where i is the char index and on/off depends on the char at position
            val revInput = it.reversed()
            // BBFFBBFRLL -> LLRFBBFFBB
            val column = convertBoardingStrToInt(revInput.substring(3), 'B', 'F')
            //  FBBFFBB -> 1100110 = 102
            val row = convertBoardingStrToInt(revInput.substring(0, 3), 'R', 'L')
            //  LLR -> 100 = 4
            (column * 8) + row
            //  102 * 8 + 4 = 820
        }
        .sorted()

        //  see sorted List
        logger.debug(sortedSeats.toString())

        //  get first seatId
        val firstSeatId = sortedSeats.first()

        //  Make the seatIds 0 indexed and look for the first mismatch, our seat would be the one
        //  right before the first mismatch
        val ourSeatId = sortedSeats.filterIndexed { index, curSeatId ->
            (curSeatId - firstSeatId) != index
        }
        .first() - 1

        return ourSeatId.toString()
    }
}