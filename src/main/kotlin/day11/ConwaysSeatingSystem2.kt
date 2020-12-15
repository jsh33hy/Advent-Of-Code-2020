package day11

import base.AocProblemSolver
import kotlin.text.StringBuilder

class ConwaysSeatingSystem2 : AocProblemSolver() {
    override fun getInputFileName() = "problem11Input.txt"

    override fun solve(): String {
        val game = ConwaysGameProblem2(getFileInputAsLinesArray())

        do{
            //  see board as it changes
            Thread.sleep(500)
            println("-----------------------------------------------------------------------------")
            game.printBoard()
        }while (game.cycle())

        return game.getBoardAsString().count { it == AbstractConwaysGame.OCCUPIED }.toString()
    }

    class ConwaysGameProblem2(input: List<String>) : AbstractConwaysGame(input){
        override fun getNextCharAtPos(x: Int, y: Int): Char{
            val adjacentSeatsBuilder = StringBuilder()
            for (i in 0..7){

                //  we are at position X
                // 0 1 2
                // 3 X 4
                // 5 6 7

                // define how we should move for each adjacent position
                var xinc = 0; var yinc = 0
                when(i){
                    0 -> { xinc = -1; yinc = -1 }
                    1 -> { xinc = 0; yinc = -1 }
                    2 -> { xinc = 1; yinc = -1 }
                    3 -> { xinc = -1; yinc = 0 }
                    4 -> { xinc = 1; yinc = 0 }
                    5 -> { xinc = -1; yinc = 1 }
                    6 -> { xinc = 0; yinc = 1 }
                    7 -> { xinc = 1; yinc = 1 }
                }

//                X#XX#X##

                adjacentSeatsBuilder.append(getFirstVisibleSeatType(x, y, xinc, yinc))
            }

            //  convert adjacent seats to a string
            // 0 1 2
            // 3 X 4
            // 5 6 7
            //  -> "01234567"
            val adjacentSeatsStr = adjacentSeatsBuilder.toString()

            return when{
                board[y][x] == OCCUPIED && adjacentSeatsStr.count { it == OCCUPIED } >= 5 -> EMPTY
                board[y][x] == EMPTY && adjacentSeatsStr.none { it == OCCUPIED } -> OCCUPIED
                else -> board[y][x]
            }
        }

        private fun getFirstVisibleSeatType(x: Int, y: Int, xinc: Int, yinc:Int): Char{
            //  check for first visible set in a direction
            //  if(x==0 && y==0 && xinc==1 && yinc1==1), we are looking down the southeast diaganol
            // 0 1 2
            // 3 X 4
            // 5 6 ?
            //       ?
            //         ?
            //           ?
            var localX = x; var localY = y
            var nextSeatType = FLOOR
            while(nextSeatType == FLOOR){
                val xpos = localX + xinc
                val ypos = localY + yinc

                if(xpos < 0 || xpos >= board[0].size || ypos < 0 || ypos >= board.size){
                    nextSeatType = INVALID
                }
                else{
                    nextSeatType = board[ypos][xpos]
                }
                localX = xpos
                localY = ypos
            }
            return nextSeatType
        }
    }
}