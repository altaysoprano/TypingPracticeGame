package com.example.typingpractice.data

class DeleteScore(
    private val repository: ScoreRepository
) {

    suspend operator fun invoke(score: Score) {
        repository.deleteScore(score)
    }
}