package com.example.typingpractice.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.typingpractice.Check
import java.security.SecureRandom

class MainScreenViewModel: ViewModel() {

    private val _score: MutableState<Int> = mutableStateOf(0)
    val score = _score

    private val _sentence: MutableState<String> = mutableStateOf("")
    val sentence = _sentence

    private val _letterGroup = mutableStateListOf<Letter>()
    val letterGroup: MutableList<Letter> = _letterGroup

    var charachterCount = 0

    private val _isGameStarted: MutableState<Boolean> = mutableStateOf(false)
    val isGameStarted = _isGameStarted

    fun increaseScore(number: Int) {
        _score.value += number
    }

    fun decreaseScore(number: Int) {
        if(_score.value >= number) _score.value -= number else _score.value = 0
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
        changeSentenceToLetterGroup()
    }

    fun finishGame() {
        _score.value = 0
        resetCharachterCount()
        _isGameStarted.value = false
    }

    fun resetCharachterCount() {
        charachterCount = 0
    }

    fun changeSentenceToLetterGroup() {
        _letterGroup.clear()
        for(i in sentence.value.indices) {
            _letterGroup.add(Letter(text = sentence.value[i].toString(), isTrue = Check.NOTTRUEORFALSE))
        }
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