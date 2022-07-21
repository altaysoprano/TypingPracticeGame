package com.example.typingpractice.data

class GetLetters(
    private val repository: LetterRepository
) {

    suspend operator fun invoke(): List<Letter> {
        if(repository.getLetters().isEmpty()) {
            return listOf(Letter("-", 0), Letter("-", 0), Letter("-", 0))
        }
        if(repository.getLetters().size == 1) {
            return repository.getLetters() + listOf(Letter("-", 0), Letter("-", 0))
        }
        if(repository.getLetters().size == 2) {
            return repository.getLetters() + listOf(Letter("-", 0))
        }
        return repository.getLetters()
    }
}