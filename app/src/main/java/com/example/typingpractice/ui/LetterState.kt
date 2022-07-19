package com.example.typingpractice.ui

import com.example.typingpractice.data.Letter

data class LetterState(
    val allLetters: List<com.example.typingpractice.data.Letter> = listOf(
        Letter("-", 0), Letter("-", 0), Letter("-", 0)
    )
)
