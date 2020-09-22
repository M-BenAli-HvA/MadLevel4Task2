package com.example.madlevel4task2

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.madlevel4task2.models.Game
import com.example.madlevel4task2.models.GameMoves
import com.example.madlevel4task2.repository.GameRepository
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.*

class GameFragment : Fragment() {

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val TAG = "GameFragment"

    private lateinit var gameRepository: GameRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameRepository = GameRepository(requireContext())
        initViews()
    }

    private fun initViews() {
        img_btn_paper.setOnClickListener {
            Log.i(TAG, "Clicked on paper image button")
            createGame(GameMoves.PAPER)
        }

        img_btn_rock.setOnClickListener {
            Log.i(TAG, "Clicked on rock image button")
            createGame(GameMoves.ROCK)
        }

        img_btn_scissors.setOnClickListener {
            Log.i(TAG, "Clicked on scissors image button")
            createGame(GameMoves.SCISSORS)
        }

    }

    private fun createGame(playerMove: GameMoves) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val computerMove = GameMoves.values()[(0..2).random()]
            val game = Game(
                playerMove, computerMove,
                Date.from(Instant.now())
            )
            game.startGame()
            Log.i(TAG, game.toString())
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.insertGame(game)
                }
            }
            displayResult(game)
        }
    }


    private fun displayResult(game: Game) {
        tv_result_message.setText(Game.getResultString(game.result))
        img_view_player_move.setImageResource(Game.getImageResource(game.playerMove))
        img_view_computer_move.setImageResource(Game.getImageResource(game.computerMove))
    }

}