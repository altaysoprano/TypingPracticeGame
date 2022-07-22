package com.example.typingpractice.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScoreAlertDialog(
    score: Int,
    letters: List<CheckLetter>,
    dialogState: Boolean,
    onDismiss: () -> Unit
) {

    if (dialogState) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Your score: ", fontWeight = FontWeight.Bold,
                        fontSize = 28.sp, color = MaterialTheme.colors.onPrimary
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = score.toString(),
                        fontSize = 24.sp,
                        color = MaterialTheme.colors.onSecondary
                    )
                    Row() {
                        letters.forEach {
                            Text(
                                text = it.text,
                                fontSize = 24.sp,
                                color = MaterialTheme.colors.onSecondary
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        onClick = { onDismiss() },
                        border = BorderStroke(1.dp, color = MaterialTheme.colors.onPrimary)
                    ) {
                        Text(text = "OK", color = MaterialTheme.colors.onPrimary)
                    }
                }
            }
        )
    }
}