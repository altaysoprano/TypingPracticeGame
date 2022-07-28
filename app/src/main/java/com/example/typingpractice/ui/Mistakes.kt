package com.example.typingpractice.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Mistakes(mistakeCount: Int, backgroundColor: Color, modifier: Modifier) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 10.dp,
        backgroundColor = backgroundColor,
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxHeight()) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    modifier = Modifier.align(CenterHorizontally),
                    text = "Mistakes",
                    style = MaterialTheme.typography.h1,
                    fontSize = 16.sp,
                    color= MaterialTheme.colors.onSecondary
                )
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.align(CenterHorizontally)) {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        color = if (mistakeCount > 0) Color.Red else Color.White,
                        text = "X",
                        fontSize = 28.sp,
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(2F, 2F),
                                blurRadius = 2F
                            )
                        )
                    )
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        color = if (mistakeCount > 1) Color.Red else Color.White,
                        text = "X",
                        fontSize = 28.sp,
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(2F, 2F),
                                blurRadius = 2F
                            )
                        )
                    )
                    Text(
                        color = if (mistakeCount > 2) Color.Red else Color.White,
                        text = "X",
                        fontSize = 28.sp,
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(2F, 2F),
                                blurRadius = 2F
                            )
                        )
                    )
                }
            }
        }
    }
}