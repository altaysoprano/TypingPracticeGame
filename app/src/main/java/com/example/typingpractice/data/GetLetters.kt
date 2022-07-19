package com.example.typingpractice.data

class GetLetters(
    private val repository: LetterRepository
) {

    suspend operator fun invoke(): List<Letter> {
        return repository.getLetters()
    }
}