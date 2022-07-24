package com.example.typingpractice.data

class TimeRepositoryImpl(
    private val dao: TimeDao
): TimeRepository {

    override suspend fun insertTime(time: Time) {
        dao.insertTime(time)
    }

    override suspend fun getTotalTime(): Int? {
        return dao.getTotalTime()
    }
}