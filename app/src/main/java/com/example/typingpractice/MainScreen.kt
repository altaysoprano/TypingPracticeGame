package com.example.typingpractice

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.typingpractice.ui.MainScreenViewModel

@ExperimentalComposeUiApi
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
) {
    val number = viewModel.score.value
    val charachterCount = viewModel.charachterCount
    val isGameStarted = viewModel.isGameStarted.value
    var text = ""
    val sentence = viewModel.sentence.value
    val kc = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(12.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            Text(text = sentence)
            Spacer(modifier = Modifier.height(12.dp))
            if (!isGameStarted) {
                Button(
                    onClick = {
                        focusRequester.requestFocus()
                        kc?.show()
                        viewModel.startGame()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Start the game",
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                Button(
                    onClick = {
                        focusRequester.requestFocus()
                        kc?.hide()
                        viewModel.finishGame()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Finish the game",
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            OutlinedTextField(
                modifier = Modifier
                    .alpha(0f)
                    .focusRequester(focusRequester),
                value = text,
                onValueChange = {
                    text = it
                    if (text == sentence[charachterCount].toString()) {
                        viewModel.increaseScore(5)
                        if(charachterCount < sentence.length-1) viewModel.increaseCharachterCount()
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
                .fillMaxHeight(0.1f)
                .padding(12.dp)
                .align(TopCenter),
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.fillMaxWidth(0.5f)) {
                    if (isGameStarted) {
                        Text("Oyun başladı...")
                    } else {
                        Text("Oyun bitti")
                    }
                }
                ScoreBoard(number)
            }
        }
    }
}

@Composable
fun ScoreBoard(number: Int) {
    Box(modifier = Modifier.fillMaxWidth(1f)) {
        Text("Score: $number", modifier = Modifier.align(CenterEnd))
    }
}