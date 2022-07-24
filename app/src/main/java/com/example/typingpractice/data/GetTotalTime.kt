package com.example.typingpractice.data

class GetTotalTime(
    private val repository: TimeRepository
) {

    suspend operator fun invoke(): Int {
        return repository.getTotalTime() ?: 0
    }
}