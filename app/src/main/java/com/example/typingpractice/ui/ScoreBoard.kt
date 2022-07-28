package com.example.typingpractice.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScoreBoard(number: Int, extraPoints: Int, backgroundColor: Color, modifier: Modifier) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 10.dp,
        backgroundColor = backgroundColor,
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxHeight()) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Text("Score ", color = MaterialTheme.colors.onSecondary)
                Text(
                    "$number",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(CenterHorizontally),
                    color = MaterialTheme.colors.onSecondary
                )
                Text(
                    text = "Gain ",
                    fontSize = 12.sp,
                    modifier = Modifier.align(CenterHorizontally),
                    color = MaterialTheme.colors.onSecondary
                )
                Text(
                    text = "+$extraPoints", fontSize = 12.sp,
                    fontWeight = FontWeight.Bold, modifier = Modifier.align(CenterHorizontally),
                    color = Color(0XFF0FC00F)
                )
            }
        }

    }
}