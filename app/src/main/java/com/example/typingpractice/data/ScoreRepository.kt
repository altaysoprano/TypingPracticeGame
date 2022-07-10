package com.example.typingpractice.data

import kotlinx.coroutines.flow.Flow

interface ScoreRepository {

    suspend fun getScores(): List<Score>

    suspend fun insertScore(score: Score)

    suspend fun deleteScore(score: Score)

    suspend fun deleteAllScores()
}