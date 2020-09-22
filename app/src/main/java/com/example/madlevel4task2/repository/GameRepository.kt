package com.example.madlevel4task2.repository

import android.content.Context
import com.example.madlevel4task2.dao.GameDao
import com.example.madlevel4task2.database.GameDatabase
import com.example.madlevel4task2.models.Game


class GameRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val gameDatabase = GameDatabase.getDatabase(context)
        gameDao = gameDatabase!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> {
        return gameDao.getAllGames()
    }

    suspend fun insertGame(game: Game) {
        return gameDao.insertGame(game)
    }

    suspend fun updateGame(game: Game) {
        return gameDao.updateGame(game)
    }

    suspend fun deleteAllGames() {
        return gameDao.deleteAllGames()
    }
}