package com.example.typingpractice

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.typingpractice.ScoreBoard
import com.example.typingpractice.ui.MainScreenViewModel
import kotlinx.coroutines.delay

@ExperimentalComposeUiApi
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
) {
    val number = viewModel.score.value

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            ScoreBoard(number)
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { viewModel.increaseScore(1) }) { Text(text = "Increase Number") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                viewModel.startGame()
            }) { Text(text = "Start the game") }
        }
    }
}

@Composable
fun ScoreBoard(number: Int) {
    Text("Score: $number")
}