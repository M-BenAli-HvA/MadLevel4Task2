package com.example.madlevel4task2

import androidx.room.TypeConverter
import com.example.madlevel4task2.models.GameMoves
import com.example.madlevel4task2.models.GameResult
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?) : Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromGameMove(value: String?): GameMoves? {
        return value?.let { GameMoves.valueOf(it) }
    }

    @TypeConverter
    fun gameMoveToString(gameMove: GameMoves?): String? {
        return gameMove?.toString()
    }

    @TypeConverter
    fun fromGameResult(value: String?): GameResult? {
        return value?.let { GameResult.valueOf(it) }
    }

    @TypeConverter
    fun gameResultToString(gameResult: GameResult?): String? {
        return gameResult?.toString()
    }




}