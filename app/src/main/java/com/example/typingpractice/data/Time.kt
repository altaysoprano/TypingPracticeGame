package com.example.typingpractice.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_table")
data class Time(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val duration: Int
)
