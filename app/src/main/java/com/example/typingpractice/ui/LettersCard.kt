package com.example.typingpractice.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.typingpractice.data.Letter

@Composable
fun LettersCard(
    letters: List<Letter>,
    backgroundColor: Color
) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 10.dp,
        backgroundColor = backgroundColor
    ) {
        Column() {
            Text(
                text = "Most Misspelled Letters", fontSize = 8.sp,
                fontWeight = FontWeight.Bold, color = MaterialTheme.colors.surface
            )
            Row() {
                Text(
                    text = letters[0].letter,
                    style = MaterialTheme.typography.h1,
                    fontSize = 16.sp,
                    color = Color.Red
                )
                Text(
                    text = letters[1].letter,
                    style = MaterialTheme.typography.h1,
                    fontSize = 16.sp,
                    color = Color.Red
                )
                Text(
                    text = letters[2].letter,
                    style = MaterialTheme.typography.h1,
                    fontSize = 16.sp,
                    color = Color.Red
                )

            }
        }

    }
}