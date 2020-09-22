package com.example.madlevel4task2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.madlevel4task2.Converters
import com.example.madlevel4task2.dao.GameDao
import com.example.madlevel4task2.models.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameDatabase: RoomDatabase()  {

    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var gameRoomDatabase: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase? {
            if(gameRoomDatabase == null) {}
            synchronized(GameDatabase::class.java) {
                gameRoomDatabase = Room.databaseBuilder(context.applicationContext,
                GameDatabase::class.java, DATABASE_NAME).build()
            }
            return gameRoomDatabase
        }
    }

}