package com.example.typingpractice.data

import kotlinx.coroutines.flow.Flow

class ScoreRepositoryImpl(
    private val dao: ScoreDao
): ScoreRepository {
    override suspend fun getScores(): List<Score> {
        return dao.getScores()
    }

    override suspend fun insertScore(score: Score) {
        dao.insertScore(score)
    }

    override suspend fun deleteScore(score: Score) {
        dao.deleteScore(score)
    }

    override suspend fun deleteAllScores() {
        dao.deleteAllScores()
    }
}