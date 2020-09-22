package com.example.madlevel4task2.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.madlevel4task2.models.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM game_table")
    suspend fun getAllGames(): List<Game>

    @Insert
    suspend fun insertGame(game: Game)

    @Update
    suspend fun updateGame(game: Game)

    @Query("DELETE FROM game_table")
    suspend fun deleteAllGames()


}