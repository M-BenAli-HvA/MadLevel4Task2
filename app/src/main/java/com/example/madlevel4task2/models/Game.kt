package com.example.madlevel4task2.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.madlevel4task2.R
import java.util.*


enum class GameMoves {
    ROCK,
    PAPER,
    SCISSORS
}

enum class GameResult {
    WIN,
    DRAW,
    LOSE
}

@Entity(tableName = "game_table")
class Game(
    @ColumnInfo(name = "player_move")
    var playerMove: GameMoves,
    @ColumnInfo(name = "computer_move")
    var computerMove: GameMoves,
    @ColumnInfo()
    var date: Date,
    @ColumnInfo()
    var result: GameResult? = null,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) {

    companion object {

        fun getImageResource(move: GameMoves): Int {
            return when(move) {
                GameMoves.SCISSORS -> R.drawable.scissors
                GameMoves.PAPER -> R.drawable.paper
                GameMoves.ROCK -> R.drawable.rock
            }
        }

        fun getResultString(result: GameResult?): Int {
            return when(result) {
                GameResult.WIN -> R.string.win_label
                GameResult.DRAW-> R.string.draw_label
                GameResult.LOSE -> R.string.lose_label
                else -> return R.string.no_result
            }
        }

    }

    fun startGame() {
        determineResult()
    }

    private fun determineResult() {
        if (playerMove == computerMove) {
            result = GameResult.DRAW
        } else {
            if (playerMove == GameMoves.ROCK) {
                if (computerMove == GameMoves.PAPER) {
                    result = GameResult.LOSE
                } else if (computerMove == GameMoves.SCISSORS) {
                    result = GameResult.WIN
                }
            } else if (playerMove == GameMoves.PAPER) {
                if (computerMove == GameMoves.SCISSORS) {
                    result = GameResult.LOSE
                } else if (computerMove == GameMoves.ROCK) {
                    result = GameResult.WIN
                }
            } else if (playerMove == GameMoves.SCISSORS) {
                if (computerMove == GameMoves.ROCK) {
                    result = GameResult.LOSE
                } else if (computerMove == GameMoves.PAPER) {
                    result = GameResult.WIN
                }
            }
        }
    }


    override fun toString(): String {
        return StringBuilder("Player move: ").append(playerMove.name + "\n")
            .append("Computer move: ").append(computerMove.name + "\n")
            .append("Date: ").append(date).append("\n")
            .append("Result: ").append(result?.name).toString()
    }
}