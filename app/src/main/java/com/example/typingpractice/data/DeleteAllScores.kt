package com.example.typingpractice.data

class DeleteAllScores(
    private val repository: ScoreRepository
){
    suspend operator fun invoke() {
        repository.deleteAllScores()
    }
}