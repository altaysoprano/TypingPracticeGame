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
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

class MainScreenViewModel : ViewModel() {

    private val _score: MutableState<Int> = mutableStateOf(0)
    val score = _score

    var extraPoints = 0

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

    private val _isPaused: MutableState<Boolean> = mutableStateOf(false)
    val isPaused = _isPaused

    private val _dialogState: MutableState<Boolean> = mutableStateOf(false)
    val dialogState = _dialogState

    val focusRequester = FocusRequester()

    private var timerJob: Job? = null

    fun increaseScore(number: Int) {
        _score.value += number
    }

    fun decreaseScore(number: Int) {
        if (_score.value >= number) _score.value -= number else _score.value = 0
    }

    fun onDialogDismiss() {
        _dialogState.value = false
        _score.value = 0
        extraPoints = 0
        resetTime()
        _letterGroup.clear()
    }

    fun increaseCharachterCount() {
        charachterCount += 1
    }

    private fun increaseTime(number: Int) {
        time += number
    }

    private fun resetTime() {
        time = 0
        _timeText.value = "00:00"
    }

    @ExperimentalTime
    fun onStart() {
        _isGameStarted.value = true
        changeSentence()
        timerJob = viewModelScope.launch { startTimer() }
    }

    @ExperimentalTime
    suspend fun startTimer() {
        while(true) {
            delay(100.milliseconds)
            increaseTime(100)
            // Log.d("Mesaj: ", "Time: $time")
            _timeText.value = stringForTime(time)
        }
    }

    fun onPaused() {
        _isPaused.value = true
        timerJob?.cancel()
    }

    @ExperimentalTime
    fun onResume() {
        _isPaused.value = false
        timerJob?.cancel()
        timerJob = viewModelScope.launch { startTimer() }
    }

    private fun stringForTime(timeMs: Int): String {
        val totalSeconds = timeMs / 1000
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
        when {
            _score.value == 0 -> { extraPoints = 0}
            time<5000 -> { extraPoints = 10 }
            time in 5001..5999 -> {extraPoints = 9}
            time in 6000..6999 -> { extraPoints = 8}
            time in 7000..7999 -> { extraPoints = 7}
            time in 8000..8999 -> { extraPoints = 6}
            time in 9000..9999 -> { extraPoints = 5}
            time in 10000..13999 -> {extraPoints = 4}
            time in 14000..19999 -> { extraPoints = 3}
            time in 20000..33999 -> {extraPoints = 2}
            else -> {extraPoints = 0}
        }
        increaseScore(extraPoints)
        changeSentenceToLetterGroup()
        resetTime()
    }

    fun onFinish() {
        _dialogState.value = true
        resetCharachterCount()
        _isGameStarted.value = false
        _isPaused.value = false
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