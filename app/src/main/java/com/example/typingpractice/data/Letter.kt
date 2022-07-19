package com.example.typingpractice.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "letter_table")
data class Letter(
    @PrimaryKey
    val letter: String,
    val letterScore: Int
)
