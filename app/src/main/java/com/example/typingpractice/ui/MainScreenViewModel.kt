package com.example.typingpractice.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainScreenViewModel: ViewModel() {

    private val _score: MutableState<Int> = mutableStateOf(0)
    val score = _score

    private val _isGameStarted: MutableState<Boolean> = mutableStateOf(false)
    val isGameStarted = _isGameStarted

    fun increaseScore(number: Int) {
        _score.value += number
    }

    fun startGame() {
        _isGameStarted.value = true
    }

    fun finishGame() {
        _isGameStarted.value = false
    }
}