package com.example.typingpractice.data

class InsertLetter(
    private val repository: LetterRepository
) {

    suspend operator fun invoke(letter: String) {
        repository.insertLetter(letter)
    }
}