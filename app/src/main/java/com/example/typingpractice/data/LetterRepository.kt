package com.example.typingpractice.data

interface LetterRepository {

    suspend fun getLetters(): List<Letter>

    suspend fun insertLetter(letter: String, number: Int)
}