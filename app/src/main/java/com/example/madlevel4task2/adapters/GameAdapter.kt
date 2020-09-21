package com.example.madlevel4task2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.models.Game
import com.example.madlevel4task2.models.GameMoves
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val games: ArrayList<Game>): RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun databind(game: Game) {
            itemView.tv_game_result.text = game.result.toString()
            itemView.tv_game_date.text = game.date.toString()

            when(game.playerMove) {
                GameMoves.SCISSORS -> itemView.img_view_player_move.setImageResource(R.drawable.scissors)
                GameMoves.PAPER -> itemView.img_view_player_move.setImageResource(R.drawable.paper)
                GameMoves.ROCK -> itemView.img_view_player_move.setImageResource(R.drawable.rock)
            }

            when(game.computerMove) {
                GameMoves.SCISSORS -> itemView.img_view_computer_move.setImageResource(R.drawable.scissors)
                GameMoves.PAPER -> itemView.img_view_computer_move.setImageResource(R.drawable.paper)
                GameMoves.ROCK -> itemView.img_view_computer_move.setImageResource(R.drawable.rock)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game, parent, false))
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }
}