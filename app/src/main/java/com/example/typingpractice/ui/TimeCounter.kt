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
import androidx.compose.ui.unit.sp

@Composable
fun TimeCounter(timeText: String, backgroundColor: Color, modifier: Modifier, gainedTime: Int) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 10.dp,
        backgroundColor = backgroundColor,
        modifier = modifier
    ) {
        val gainedTimeText = (gainedTime.toDouble()/1000)

        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Text("Time", modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(timeText, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(
                    "Gain",
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    "+$gainedTimeText", fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color(0XFF0FC00F),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
