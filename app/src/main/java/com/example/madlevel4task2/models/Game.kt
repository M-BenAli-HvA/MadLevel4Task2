package com.example.madlevel4task2.models

import java.lang.StringBuilder
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

class Game(
    var playerMove: GameMoves, var computerMove: GameMoves,
    var date: Date, var result: GameResult? = null
) {

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