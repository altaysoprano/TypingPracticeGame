package com.example.typingpractice

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.typingpractice.ui.MainScreenViewModel

@ExperimentalComposeUiApi
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
) {
    val number = viewModel.score.value
    val kc = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            ScoreBoard(number)
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { viewModel.increaseScore(1) }) { Text(text = "Increase Number") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                focusRequester.requestFocus()
                kc?.show()
                viewModel.startGame()
            }) { Text(text = "Start the game") }
            OutlinedTextField(
                modifier = Modifier
                    .alpha(0f)
                    .focusRequester(focusRequester),
                value = "",
                onValueChange = {}
            )
        }
    }
}

@Composable
fun ScoreBoard(number: Int) {
    Text("Score: $number")
}