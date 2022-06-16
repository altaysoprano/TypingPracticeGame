package com.example.typingpractice.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainScreenViewModel: ViewModel() {

    private val _score: MutableState<Int> = mutableStateOf(0)
    val score = _score

    fun increaseScore(number: Int) {
        _score.value += number
    }

    fun startGame() {

    }
}