package com.example.typingpractice.ui

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.typingpractice.Check
import com.example.typingpractice.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.SecureRandom
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.ExperimentalTime

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getScores: GetScores,
    private val insertScore: InsertScore,
) : ViewModel() {

    var extraPoints = 0
    var charachterCount = 0
    private var time = 0
    val focusRequester = FocusRequester()
    private var timerJob: Job? = null

    private val _score: MutableState<Int> = mutableStateOf(0)
    val score = _score

    private val _sentence: MutableState<String> = mutableStateOf("")
    val sentence = _sentence

    private var _allScores: MutableState<BestScoresState> = mutableStateOf(BestScoresState())
    var allScores = _allScores

    private val _letterGroup = mutableStateListOf<Letter>()
    val letterGroup: MutableList<Letter> = _letterGroup

    private val _timeText: MutableState<String> = mutableStateOf("00:00")
    val timeText = _timeText

    private val _isGameStarted: MutableState<Boolean> = mutableStateOf(false)
    val isGameStarted = _isGameStarted

    private val _isPaused: MutableState<Boolean> = mutableStateOf(false)
    val isPaused = _isPaused

    private val _dialogState: MutableState<Boolean> = mutableStateOf(false)
    val dialogState = _dialogState

    init {
        getAllScores()
    }

    fun increaseScore(number: Int) {
        _score.value += number
    }

    fun decreaseScore(number: Int) {
        if (_score.value >= number) _score.value -= number else _score.value = 0
        Log.d("Mesaj: ", "Decrease score çalıştı")
    }

    fun onDialogDismiss() {
        _dialogState.value = false
        _score.value = 0
        extraPoints = 0
        getAllScores()
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
        while (true) {
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

    private fun getAllScores() {
        viewModelScope.launch {
            val allScores = getScores()
            var totalScore = 0
            allScores.forEach { totalScore += it.score }
            _allScores.value = _allScores.value.copy(
                allScores = allScores,
                totalScore = totalScore
            )
        }
    }

    private fun addScores(score: Int) {
        Log.d("Mesaj:", "addscores: " + score.toString())
        viewModelScope.launch {
            Log.d("Mesaj: ", "addscores viewModellauncH: " + score.toString())
            insertScore(Score(0, score))
        }
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
            _score.value == 0 -> {
                extraPoints = 0
            }
            time < 5000 -> {
                extraPoints = 10
            }
            time in 5001..5999 -> {
                extraPoints = 9
            }
            time in 6000..6999 -> {
                extraPoints = 8
            }
            time in 7000..7999 -> {
                extraPoints = 7
            }
            time in 8000..8999 -> {
                extraPoints = 6
            }
            time in 9000..9999 -> {
                extraPoints = 5
            }
            time in 10000..13999 -> {
                extraPoints = 4
            }
            time in 14000..19999 -> {
                extraPoints = 3
            }
            time in 20000..33999 -> {
                extraPoints = 2
            }
            else -> {
                extraPoints = 0
            }
        }
        increaseScore(extraPoints)
        changeSentenceToLetterGroup()
        resetTime()
    }

    fun onFinish() {
        _dialogState.value = true
        Log.d("Mesaj: ", "alertdialog true")
        resetCharachterCount()
        Log.d("Mesaj: ", "Score value: ${score.value}")
        addScores(_score.value)
        for (i in 0..2) {
            Log.d("Mesaj: ", allScores.value.allScores[i].toString())
        }
        _isGameStarted.value = false
        Log.d("Mesaj: " ,"_isGameStarted değişti: ${_isGameStarted.value}")
        _isPaused.value = false
        Log.d("Mesaj: " ,"_isPaused değişti")
        timerJob?.cancel()
        Log.d("Mesaj: " ,"timejob cancel")
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