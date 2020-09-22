package com.example.madlevel4task2

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.adapters.GameAdapter
import com.example.madlevel4task2.models.Game
import com.example.madlevel4task2.repository.GameRepository
import kotlinx.android.synthetic.main.fragment_game_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private lateinit var gameRepository: GameRepository

    @RequiresApi(Build.VERSION_CODES.O)
    private var games: ArrayList<Game> = arrayListOf()
    private lateinit var gamesAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_game_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val decorationItem = DividerItemDecoration(
            requireContext(),
            DividerItemDecoration.VERTICAL
        )
        rv_games.layoutManager = LinearLayoutManager(
            requireContext(), RecyclerView.VERTICAL, false
        )

        rv_games.addItemDecoration(decorationItem)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            gamesAdapter = GameAdapter(games)
            rv_games.adapter = gamesAdapter
        }

        gameRepository = GameRepository(requireContext())
        getGamesFromDatabase()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.clear_game_history -> {
                Log.i("GameHistory", "Clicking on clearing game history")
                deleteAllGames()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun getGamesFromDatabase() {
        mainScope.launch {
            val games = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                this@GameHistory.games.clear()
                this@GameHistory.games.addAll(games)
                gamesAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun deleteAllGames() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteAllGames()
            }
            getGamesFromDatabase()
        }


    }


}