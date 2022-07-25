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
    private val getLetters: GetLetters,
    private val insertLetter: InsertLetter,
    private val getTotalTime: GetTotalTime,
    private val insertTime: InsertTime
) : ViewModel() {

    var extraPoints = 0
    var charachterCount = 0
    private var time = 0
    private var totalTime = 0
    val focusRequester = FocusRequester()
    private var timerJob: Job? = null

    private val _score: MutableState<Int> = mutableStateOf(0)
    val score = _score

    private val _sentence: MutableState<String> = mutableStateOf("")
    val sentence = _sentence

    private val _text: MutableState<String> = mutableStateOf("")
    val text = _text

    private var _allScores: MutableState<BestScoresState> = mutableStateOf(BestScoresState())
    var allScores = _allScores

    private var _allLetters: MutableState<LetterState> = mutableStateOf(LetterState())
    var allLetters = _allLetters

    private val _allMistakenLetters = mutableStateListOf<CheckLetter>()
    val allMistakenLetters = _allMistakenLetters

    private val _letterGroup = mutableStateListOf<CheckLetter>()
    val letterGroup: MutableList<CheckLetter> = _letterGroup

    private val _timeText: MutableState<String> = mutableStateOf("00:00")
    val timeText = _timeText

    private val _totalTimeText: MutableState<String> = mutableStateOf("00:00")
    val totalTimeText = _totalTimeText

    private val _isGameStarted: MutableState<Boolean> = mutableStateOf(false)
    val isGameStarted = _isGameStarted

    private val _isPaused: MutableState<Boolean> = mutableStateOf(false)
    val isPaused = _isPaused

    private val _dialogState: MutableState<Boolean> = mutableStateOf(false)
    val dialogState = _dialogState

    init {
        getAllScores()
        getAllLetters()
        setTotalTime()
        Log.d("Mesaj: ", "initte total time: " + _totalTimeText.value)
    }

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
        getAllScores()
        getAllLetters()
        setTotalTime()
        Log.d("Mesaj: ", "dismissten sonra total time: " + _totalTimeText.value)
        resetTime()
        resetAllWrongLetters()
        _letterGroup.clear()
    }

    fun increaseCharachterCount() {
        charachterCount += 1
    }

    private fun increaseTime(number: Int) {
        time += number
    }

    fun onTextChanged(text: String) {
        _text.value = text
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
        allLetters.value.allLetters.forEach { Log.d("Mesaj: ", "${it.letter}: ${it.letterScore}") }
    }

    fun addLetter(letter: String, number: Int) {
        viewModelScope.launch {
            insertLetter(letter, number)
        }
    }

    private fun getAllLetters() {
        viewModelScope.launch {
            val allLetters = getLetters()
            _allLetters.value = _allLetters.value.copy(
                allLetters = allLetters
            )
        }
    }

    private fun setTotalTime() {
        viewModelScope.launch {
            val totalTime = getTotalTime()
            _totalTimeText.value = stringForTime(totalTime)
        }
    }

    @ExperimentalTime
    suspend fun startTimer() {
        while (true) {
            delay(100.milliseconds)
            increaseTime(100)
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
        viewModelScope.launch {
            insertScore(Score(0, score))
        }
    }

    private fun insertTime(time: Int) {
        viewModelScope.launch {
            insertTime(Time(0, time))
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
        addAllWrongLetters()
        addToTotalTime(time)
        Log.d("Mesaj: ", totalTime.toString())
        changeSentenceToLetterGroup()
        resetTime()
    }

    private fun addToTotalTime(time: Int) {
        totalTime += time
    }

    private fun resetTotalTime() {
        totalTime = 0
    }

    fun onFinish() {
        addAllWrongLetters()
        _dialogState.value = true
        resetCharachterCount()
        addScores(_score.value)
        addToTotalTime(time)
        insertTime(totalTime)
        Log.d("Mesaj: ", totalTime.toString())
        resetTotalTime()
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
                CheckLetter(
                    text = sentence.value[i].toString(),
                    isTrue = Check.NOTTRUEORFALSE
                )
            )
        }
    }

    private fun addAllWrongLetters() {
        letterGroup.forEach {
            if (it.isTrue == Check.FALSE && it.text != " ") {
                if(it !in _allMistakenLetters) {
                    _allMistakenLetters.add(it)
                }
                addLetter(it.text, 1)
            }
            if(it.isTrue == Check.TRUE && it.text != " ") {
                addLetter(it.text, -1)
            }
        }
    }

    private fun resetAllWrongLetters() {
        _allMistakenLetters.clear()
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