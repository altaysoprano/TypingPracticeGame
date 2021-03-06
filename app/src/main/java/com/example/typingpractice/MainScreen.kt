package com.example.typingpractice

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.typingpractice.ui.*
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalComposeUiApi
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
) {
    val number = viewModel.score.value
    val extraPoints = viewModel.extraPoints
    val gainedTime = viewModel.gainedTime
    val bestScores = viewModel.allScores
    val letters = viewModel.allLetters
    val totalTime = viewModel.totalTimeText
    val charachterCount = viewModel.charachterCount
    val isGameStarted = viewModel.isGameStarted.value
    val isPaused = viewModel.isPaused.value
    val dialogState = viewModel.dialogState.value
    var remainingTimeText = viewModel.remainingTimeText.value
    val text1 = viewModel.text.value
    var finishCount = 0
    val allMistakenLetters = viewModel.allMistakenLetters
    val timeText = viewModel.timeText.value
    val sentence = viewModel.sentence.value
    val letterGroup = viewModel.letterGroup
    val mistakeCount = letterGroup.count { it.isTrue == Check.FALSE }
    val kc = LocalSoftwareKeyboardController.current
    val focusRequester = viewModel.focusRequester

    LaunchedEffect(true) {
        if (isGameStarted) {
            focusRequester.requestFocus()
            kc?.show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(12.dp)
                .align(TopCenter),
            horizontalAlignment = CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    TimeCounter(
                        timeText = remainingTimeText,
                        backgroundColor = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .align(CenterStart)
                            .fillMaxWidth(0.3f),
                        gainedTime = gainedTime
                    )
                    Mistakes(
                        mistakeCount = mistakeCount,
                        backgroundColor = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .align(Center)
                            .fillMaxWidth(0.3f)
                    )
                    ScoreBoard(
                        number = number,
                        extraPoints = extraPoints,
                        backgroundColor = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .align(CenterEnd)
                            .fillMaxWidth(0.3f)
                    )
                    ScoreAlertDialog(
                        score = number,
                        letters = allMistakenLetters,
                        dialogState = dialogState
                    ) { viewModel.onDialogDismiss() }
                }
            }
            Column(
                modifier= Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                if (!isGameStarted) {
                    BestScoresCard(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth(),
                        backgroundColor = MaterialTheme.colors.onBackground,
                        bestScores = bestScores.value.allScores,
                        totalScore = bestScores.value.totalScore,
                        letters = letters.value.allLetters,
                        totalTime = totalTime.value
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(12.dp),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (!isGameStarted) {
                        Button(
                            onClick = {
                                focusRequester.requestFocus()
                                kc?.show()
                                viewModel.onStart()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0XFF0FC00F),
                                contentColor = Color.White
                            ),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 18.dp
                            )
                        ) {
                            Text(
                                text = "Start",
                                modifier = Modifier.padding(vertical = 8.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.error)
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            for (i in sentence.indices) {
                                if (i == charachterCount) {
                                    Text(
                                        color = Color.White,
                                        text = sentence[i].toString(),
                                        style = MaterialTheme.typography.h2
                                    )
                                } else {
                                    Text(
                                        color = when {
                                            letterGroup[i].isTrue == Check.FALSE -> Color.Red
                                            letterGroup[i].isTrue == Check.TRUE -> Color.Green
                                            else -> Color.White
                                        },
                                        text = sentence[i].toString(),
                                        style = MaterialTheme.typography.h1
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        if (!isPaused) {
                            Button(
                                onClick = {
                                    focusRequester.requestFocus()
                                    kc?.hide()
                                    viewModel.onPaused()
                                },
                                modifier = Modifier.fillMaxWidth(),
                                elevation = ButtonDefaults.elevation(
                                    defaultElevation = 18.dp
                                )
                            ) {
                                Text(
                                    text = "Pause",
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_pause),
                                    contentDescription = "Resume"
                                )
                            }
                        } else {
                            Button(
                                onClick = {
                                    focusRequester.requestFocus()
                                    kc?.show()
                                    viewModel.onResume()
                                },
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Text(
                                    text = "Resume",
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_resume),
                                    contentDescription = "Resume"
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = {
                                focusRequester.requestFocus()
                                kc?.hide()
                                viewModel.onFinish()
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0XFFD61809),
                                contentColor = Color.White
                            ),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 18.dp
                            )
                        ) {
                            Text(
                                text = "Finish",
                                modifier = Modifier.padding(vertical = 8.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                        if (!isPaused) {
                            IconButton(onClick = {
                                focusRequester.requestFocus()
                                kc?.show()
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_keyboard),
                                    contentDescription = "keyboard",
                                    modifier = Modifier.size(48.dp)
                                )
                            }
                        }
                    }
                    OutlinedTextField(
                        modifier = Modifier
                            .alpha(0f)
                            .fillMaxSize(0f)
                            .focusRequester(focusRequester),
                        value = text1,
                        onValueChange = {
                            viewModel.onTextChanged(it)
                            if (it[it.length-1].toString() == sentence[charachterCount].toString()) {
                                viewModel.increaseScore(5)
                                if (letterGroup[charachterCount].isTrue != Check.FALSE) {
                                    letterGroup[charachterCount].isTrue = Check.TRUE
                                } else {
                                    viewModel.decreaseScore(5)
                                }
                                if (charachterCount < sentence.length - 1) {
                                    viewModel.increaseCharachterCount()
                                } else {
                                    viewModel.changeSentence()
                                    viewModel.resetCharachterCount()
                                }
                            } else {
                                if (finishCount < 1) {
                                    viewModel.decreaseScore(3)
                                    letterGroup[charachterCount].isTrue = Check.FALSE
                                    if (letterGroup.count { it.isTrue == Check.FALSE } > 3) {
                                        allMistakenLetters.forEach { Log.d("Mesaj: ", it.text) }
                                        focusRequester.requestFocus()
                                        kc?.hide()
                                        viewModel.onFinish()
                                        finishCount++
                                    }
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        )
                    )
                }
            }
        }
    }
}