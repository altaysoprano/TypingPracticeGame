package com.example.typingpractice.data

import kotlinx.coroutines.flow.Flow

class GetScores(
    private val repository: ScoreRepository
) {

    suspend operator fun invoke(): List<Score> {
        if(repository.getScores().isEmpty()) {
            return listOf(Score(0,0), Score(0,0), Score(0,0))
        }
        if(repository.getScores().size == 1) {
            return repository.getScores() + listOf(Score(0,0), Score(0,0), Score(0,0))
        }
        if(repository.getScores().size == 2) {
            return repository.getScores() + listOf(Score(0,0))
        }
        return repository.getScores()
    }
}