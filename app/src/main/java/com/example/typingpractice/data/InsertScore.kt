package com.example.typingpractice.data

class InsertScore(
    private val repository: ScoreRepository
) {
    suspend operator fun invoke(score: Score) {
        repository.insertScore(score)
    }
}