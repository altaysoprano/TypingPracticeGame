package com.example.typingpractice.data

interface TimeRepository {

    suspend fun insertTime(time: Time)
    suspend fun getTotalTime(): Int?
}