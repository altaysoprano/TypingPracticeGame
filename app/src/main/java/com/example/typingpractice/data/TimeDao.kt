package com.example.typingpractice.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query

@Dao
interface TimeDao {

    @Insert(onConflict = IGNORE)
    suspend fun insertTime(time: Time)

    @Query("SELECT SUM(duration) FROM time_table")
    suspend fun getTotalTime(): Int?
}