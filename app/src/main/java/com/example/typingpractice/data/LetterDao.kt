package com.example.typingpractice.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface LetterDao {

    @Query("INSERT OR REPLACE INTO letter_table(letter, letterScore) VALUES (:letter, COALESCE((SELECT letterScore + 1 FROM letter_table WHERE letter=:letter), 0))")
    suspend fun insertLetter(letter: String)

    @Query("SELECT * FROM letter_table ORDER BY letterScore DESC LIMIT 3")
    suspend fun getLetters(): List<Letter>
}