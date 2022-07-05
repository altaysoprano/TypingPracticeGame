package com.example.typingpractice.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TimeCounter(timeText: String, backgroundColor: Color) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 10.dp,
        backgroundColor = backgroundColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .fillMaxHeight(),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Text("Time: ")
                Text(timeText, fontWeight = FontWeight.Bold)
            }
        }
    }
}
