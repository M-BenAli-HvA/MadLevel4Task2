package com.example.madlevel4task2

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.adapters.GameAdapter
import com.example.madlevel4task2.models.Game
import com.example.madlevel4task2.models.GameMoves
import com.example.madlevel4task2.models.GameResult
import kotlinx.android.synthetic.main.fragment_game_history.*
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameHistory.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameHistory : Fragment() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val games: ArrayList<Game> = arrayListOf(Game(
        GameMoves.PAPER, GameMoves.ROCK, Date.from(Instant.now()),
        GameResult.WIN
    ),Game(
        GameMoves.PAPER, GameMoves.ROCK, Date.from(Instant.now()),
        GameResult.WIN
    ))
    private lateinit var gamesAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val decorationItem = DividerItemDecoration(requireContext(),
        DividerItemDecoration.VERTICAL)
        rv_games.layoutManager = LinearLayoutManager(
            requireContext(), RecyclerView.VERTICAL, false
        )

        rv_games.addItemDecoration(decorationItem)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            gamesAdapter = GameAdapter(games)
            rv_games.adapter = gamesAdapter
        }

    }


}