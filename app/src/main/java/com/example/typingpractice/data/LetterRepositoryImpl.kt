package com.example.typingpractice.data

class LetterRepositoryImpl(
    private val dao: LetterDao
): LetterRepository {
    override suspend fun getLetters(): List<Letter> {
        return dao.getLetters()
    }

    override suspend fun insertLetter(letter: String, number: Int) {
        dao.insertLetter(letter, number)
    }
}