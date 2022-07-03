package com.example.typingpractice.ui

import android.util.Log
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ScoreAlertDialog(
    score: Int,
    dialogState: Boolean,
    onDismiss: () -> Unit
) {

    Log.d("Mesaj: ", "Score (AlertDialog): " + score.toString())
    if (dialogState) {
        AlertDialog(
            onDismissRequest = { onDismiss()
            },
            title = { Text(text = "Your score: ", fontWeight = FontWeight.Bold)},
            text = { Text(text = score.toString()) },
            confirmButton = {
                TextButton(onClick = { onDismiss()
                 }) {
                    Text(text = "OK")
                }
            }
        )
    }
}