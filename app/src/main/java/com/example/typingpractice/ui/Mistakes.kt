package com.example.typingpractice.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Mistakes(mistakeCount: Int, backgroundColor: Color) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 10.dp,
        backgroundColor = backgroundColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .fillMaxHeight()
        ) {
            Column() {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Mistakes"
                )
                Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
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