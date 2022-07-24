package com.example.typingpractice.data

class InsertTime(
    private val repository: TimeRepository
) {

    suspend operator fun invoke(time: Time) {
        repository.insertTime(time)
    }
}