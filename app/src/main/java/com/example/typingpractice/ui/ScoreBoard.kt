package com.example.typingpractice.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScoreBoard(number: Int, extraPoints: Int, backgroundColor: Color) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 10.dp,
        backgroundColor = backgroundColor,
        modifier = Modifier.padding(start = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp)
            ) {
                Text("Score: ")
                Text("$number", fontWeight = FontWeight.Bold)
                Text(text = "Gain: ", fontSize = 12.sp)
                Text(text = "+$extraPoints", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

    }
}