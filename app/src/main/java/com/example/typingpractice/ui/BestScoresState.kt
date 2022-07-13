package com.example.typingpractice.ui

import com.example.typingpractice.data.Score

data class BestScoresState(
    val allScores: List<Score> = listOf(Score(0, 0), Score(0, 0), Score(0, 0)),
    val totalScore: Int = 0
)
