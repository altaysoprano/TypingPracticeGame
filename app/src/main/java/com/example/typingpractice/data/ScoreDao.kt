package com.example.typingpractice.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertScore(score: Score)

    @Query("SELECT * FROM score_table ORDER BY score DESC")
    suspend fun getScores(): List<Score>

    @Delete
    suspend fun deleteScore(score: Score)

    @Query("DELETE FROM score_table")
    suspend fun deleteAllScores()

}