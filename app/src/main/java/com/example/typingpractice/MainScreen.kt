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
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.typingpractice.ui.BestScoresCard
import com.example.typingpractice.ui.MainScreenViewModel
import com.example.typingpractice.ui.ScoreAlertDialog
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalComposeUiApi
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
) {
    val number = viewModel.score.value
    val extraPoints = viewModel.extraPoints
    val charachterCount = viewModel.charachterCount
    val isGameStarted = viewModel.isGameStarted.value
    val isPaused = viewModel.isPaused.value
    val dialogState = viewModel.dialogState.value
    var text = ""
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
            .fillMaxSize(),
        contentAlignment = Center
    ) {
        Column(
            modifier = Modifier
                .align(Center)
                .padding(12.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            if (!isGameStarted) {
                Button(
                    onClick = {
                        focusRequester.requestFocus()
                        kc?.show()
                        viewModel.onStart()
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0XFF0FC00F),
                        contentColor = Color.White
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
                        .background(MaterialTheme.colors.onSecondary)
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
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Pause",
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    Button(
                        onClick = {
                            focusRequester.requestFocus()
                            kc?.show()
                            viewModel.onResume()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Resume",
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontWeight = FontWeight.Bold
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
                value = text,
                onValueChange = {
                    text = it
                    Log.d("Mesaj: ", "Text: $text, it: $it")
                    if (text == sentence[charachterCount].toString()) {
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
                        viewModel.decreaseScore(3)
                        letterGroup[charachterCount].isTrue = Check.FALSE
                        if (letterGroup.count { it.isTrue == Check.FALSE } > 3) {
                            focusRequester.requestFocus()
                            kc?.hide()
                            viewModel.onFinish()
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { }
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f)
                .padding(12.dp)
                .align(TopCenter),
            horizontalAlignment = CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.30f)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    TimeCounter(timeText)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                    ) {
                        Column() {
                            Text(
                                modifier = Modifier.align(CenterHorizontally),
                                text = "Mistakes"
                            )
                            Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
                                Text(
                                    modifier = Modifier.padding(end = 8.dp),
                                    color = if (mistakeCount > 0) Color.Red else Color.White,
                                    text = "X",
                                    fontSize = 28.sp,
                                    style = TextStyle(
                                        shadow = Shadow(
                                            color = Color.Black,
                                            offset = Offset(2F, 2F),
                                            blurRadius = 2F
                                        )
                                    )
                                )
                                Text(
                                    modifier = Modifier.padding(end = 8.dp),
                                    color = if (mistakeCount > 1) Color.Red else Color.White,
                                    text = "X",
                                    fontSize = 28.sp,
                                    style = TextStyle(
                                        shadow = Shadow(
                                            color = Color.Black,
                                            offset = Offset(2F, 2F),
                                            blurRadius = 2F
                                        )
                                    )
                                )
                                Text(
                                    color = if (mistakeCount > 2) Color.Red else Color.White,
                                    text = "X",
                                    fontSize = 28.sp,
                                    style = TextStyle(
                                        shadow = Shadow(
                                            color = Color.Black,
                                            offset = Offset(2F, 2F),
                                            blurRadius = 2F
                                        )
                                    )
                                )
                            }
                        }
                    }
                    ScoreBoard(number, extraPoints)
                    ScoreAlertDialog(
                        score = number,
                        dialogState = dialogState
                    ) { viewModel.onDialogDismiss() }
                }
            }
            BestScoresCard(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp),
                backgroundColor = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
fun TimeCounter(timeText: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.4f)
    ) {
        Column(
            modifier = Modifier
                .align(CenterStart)
                .padding(start = 12.dp)
        ) {
            Text("Time: ")
            Text(timeText, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ScoreBoard(number: Int, extraPoints: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
    ) {
        Column(
            modifier = Modifier
                .align(CenterEnd)
                .padding(end = 12.dp)
        ) {
            Text("Score: ")
            Text("$number", fontWeight = FontWeight.Bold)
            Text(text = "Gain: ", fontSize = 12.sp)
            Text(text = "+$extraPoints", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}