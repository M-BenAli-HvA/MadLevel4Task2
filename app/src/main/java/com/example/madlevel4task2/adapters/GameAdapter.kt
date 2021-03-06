package com.example.madlevel4task2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.models.Game
import com.example.madlevel4task2.models.GameResult
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val games: List<Game>): RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun databind(game: Game) {
            itemView.tv_game_result.text = game.result?.name
            itemView.tv_game_date.text = game.date.toString()

            itemView.img_view_player_move.setImageResource(Game.getImageResource(game.playerMove))
            itemView.img_view_computer_move.setImageResource(Game.getImageResource(game.computerMove))

            when(game.result) {
                GameResult.WIN -> itemView.tv_game_result.setText(R.string.player_win)
                GameResult.DRAW -> itemView.tv_game_result.setText(R.string.game_draw)
                GameResult.LOSE -> itemView.tv_game_result.setText(R.string.computer_win)
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