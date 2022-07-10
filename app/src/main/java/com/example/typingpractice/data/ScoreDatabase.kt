package com.example.typingpractice.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Score::class],
    version = 1
)
abstract class ScoreDatabase: RoomDatabase() {

    abstract val scoreDao: ScoreDao

    companion object {
        const val DATABASE_NAME = "scores_db"
    }
}