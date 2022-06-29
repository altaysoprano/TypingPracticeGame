package com.example.typingpractice.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.typingpractice.Check
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.SecureRandom
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

class MainScreenViewModel : ViewModel() {

    private val _score: MutableState<Int> = mutableStateOf(0)
    val score = _score

    private val _sentence: MutableState<String> = mutableStateOf("")
    val sentence = _sentence

    private val _letterGroup = mutableStateListOf<Letter>()
    val letterGroup: MutableList<Letter> = _letterGroup

    var charachterCount = 0

    private var time = 0

    private val _timeText: MutableState<String> = mutableStateOf("00:00")
    val timeText = _timeText

    private val _isGameStarted: MutableState<Boolean> = mutableStateOf(false)
    val isGameStarted = _isGameStarted

    val focusRequester = FocusRequester()

    private var timerJob: Job? = null

    fun increaseScore(number: Int) {
        _score.value += number
    }

    fun decreaseScore(number: Int) {
        if (_score.value >= number) _score.value -= number else _score.value = 0
    }

    fun increaseCharachterCount() {
        charachterCount += 1
    }

    private fun increaseTime(number: Int) {
        time += number
    }

    @ExperimentalTime
    fun startGame() {
        _isGameStarted.value = true
        changeSentence()
        timerJob = viewModelScope.launch { startTimer() }
    }

    @ExperimentalTime
    suspend fun startTimer() {
        while(true) {
            delay(1.seconds)
            increaseTime(1)
            _timeText.value = stringForTime(time)
        }
    }

    private fun stringForTime(totalSeconds: Int): String {
        val seconds = totalSeconds % 60
        val minutes = totalSeconds / 60 % 60
        val hours = totalSeconds / 3600
        return if (hours > 0) {
            "%d:%02d:%02d".format(hours, minutes, seconds)
        } else {
            "%02d:%02d".format(minutes, seconds)
        }
    }

    fun changeSentence() {
        _sentence.value = randomSentence()
        changeSentenceToLetterGroup()
    }

    fun finishGame() {
        _score.value = 0
        resetCharachterCount()
        _isGameStarted.value = false
        timerJob?.cancel()
    }

    fun resetCharachterCount() {
        charachterCount = 0
    }

    private fun changeSentenceToLetterGroup() {
        _letterGroup.clear()
        for (i in sentence.value.indices) {
            _letterGroup.add(
                Letter(
                    text = sentence.value[i].toString(),
                    isTrue = Check.NOTTRUEORFALSE
                )
            )
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