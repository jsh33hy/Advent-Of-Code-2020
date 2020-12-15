package day11

abstract class AbstractConwaysGame(input: List<String>) {
    companion object {
        const val OCCUPIED = '#'
        const val EMPTY = 'L'
        const val FLOOR = '.'
        const val INVALID = 'X'
    }

    abstract fun getNextCharAtPos(x: Int, y: Int): Char

    //  board is a 2dimension array of board[y][x] positions
    //  borad[3][1] =
    //  - - - - - -
    //  - - - - - -
    //  - - - - - -
    //  - X - - - -
    //  - - - - - -

    //  I don't know about kotlin, but in some languages, just using the raw ascci int value of the
    //  underlying char would speed comparisons up
    protected var board = Array(input.size) { CharArray(input[0].length) }

    init {
        for (y in input.indices) {
            for (x in input[y].indices) {
                board[y][x] = input[y][x]
            }
        }
    }

    fun printBoard() {
        for (y in board.indices) {
            println(board[y])
        }
    }

    fun getBoardAsString(): String {
        val boardStr = StringBuilder()
        for (y in board.indices) {
            for (x in board[y].indices) {
                boardStr.append(board[y][x])
            }
        }
        return boardStr.toString()
    }

    private fun copyBoard(): Array<CharArray> {
        var newBoard = Array(board.size) { CharArray(board[0].size) }
        for (y in board.indices) {
            for (x in board[y].indices) {
                newBoard[y][x] = board[y][x]
            }
        }
        return newBoard
    }

    private fun internalCycle() {
        val boardCopy = copyBoard()
        for (y in boardCopy.indices) {
            for (x in boardCopy[0].indices) {
                boardCopy[y][x] = getNextCharAtPos(x, y)
            }
        }
        board = boardCopy
    }

    //  returns true if the board is still changing, false if steady state
    fun cycle(): Boolean {
        val currentBoard = getBoardAsString()
        internalCycle()
        val newBoard = getBoardAsString()
        return currentBoard != newBoard
    }

}