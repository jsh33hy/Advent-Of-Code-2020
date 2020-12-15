package day11

import base.AocProblemSolver
import day11.AbstractConwaysGame.Companion.OCCUPIED

class ConwaysSeatingSystem1 : AocProblemSolver() {
    override fun getInputFileName() = "problem11Input.txt"

    override fun solve(): String {
        val game = ConwaysGameProblem1(getFileInputAsLinesArray())

        do{
            //  see board as it changes
//            Thread.sleep(500)
//            println("-----------------------------------------------------------------------------")
//            game.printBoard()
        }while (game.cycle())

        return game.getBoardAsString().count { it == OCCUPIED }.toString()
    }

    class ConwaysGameProblem1(input: List<String>) : AbstractConwaysGame(input){
        override fun getNextCharAtPos(x: Int, y: Int): Char{
            return when{
                board[y][x] == OCCUPIED && fourOrMoreAdjacentSetsOccupied(x, y) -> EMPTY
                board[y][x] == EMPTY && allAdjacentSeatsAreEmpty(x,y) -> OCCUPIED
                else -> board[y][x]
            }
        }

        private fun allAdjacentSeatsAreEmpty(x: Int, y:Int): Boolean{
            var allEmpty = true
            for(j in -1..1){
                for(k in -1..1){
                    val xpos = x + k
                    val ypos = y + j
                    if(!(xpos == x && ypos == y) && ypos >= 0 && xpos >= 0 && ypos < board.size && xpos < board[0].size){
                        if(board[ypos][xpos] == OCCUPIED){
                            allEmpty = false
                            break
                        }
                    }
                }
            }
            return allEmpty
        }

        private fun fourOrMoreAdjacentSetsOccupied(x: Int, y:Int): Boolean{
            var numOccupied = 0

            for(j in -1..1){
                for(k in -1..1){
                    val xpos = x + k
                    val ypos = y + j
                    if(!(xpos == x && ypos == y) && ypos >= 0 && xpos >= 0 && ypos < board.size &&  xpos < board[0].size){
                        if(board[ypos][xpos] == OCCUPIED){
                            numOccupied++
                        }
                    }
                }
            }
            return numOccupied >= 4
        }
    }
}