package com.example.typingpractice.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.security.SecureRandom

class MainScreenViewModel: ViewModel() {

    private val _score: MutableState<Int> = mutableStateOf(0)
    val score = _score

    private val _sentence: MutableState<String> = mutableStateOf("")
    val sentence = _sentence

    var charachterCount = 0

    private val _isGameStarted: MutableState<Boolean> = mutableStateOf(false)
    val isGameStarted = _isGameStarted

    fun increaseScore(number: Int) {
        _score.value += number
    }

    fun increaseCharachterCount() {
        charachterCount += 1
    }

    fun startGame() {
        _isGameStarted.value = true
        changeSentence()
    }

    fun changeSentence() {
        _sentence.value = randomSentence()
    }

    fun finishGame() {
        _score.value = 0
        resetCharachterCount()
        _isGameStarted.value = false
    }

    fun resetCharachterCount() {
        charachterCount = 0
    }

    private fun randomString(): String {
        val random = SecureRandom()
        val len = (3..6).random()
        val chars = ("abcdefghijklmnopqrstuvwxyzaeiouabcdefghijklmnopqrstuvwxyzaeiou" +
                "abcdefghijklmnopqrstuvwxyzaeiouABCDEFGHIJKLMNOPQRSTUVWXYZAEIOU").toCharArray()
        return (1..len).map { chars[random.nextInt(chars.size)] }.joinToString("")
    }

    fun randomSentence(): String {
        return "${randomString()} ${randomString()} ${randomString()} ${randomString()}"
    }
}