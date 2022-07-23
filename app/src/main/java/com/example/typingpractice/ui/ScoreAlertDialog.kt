package com.example.typingpractice.ui

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.typingpractice.R

@Composable
fun ScoreAlertDialog(
    score: Int,
    letters: List<CheckLetter>,
    dialogState: Boolean,
    onDismiss: () -> Unit,
) {

    val scrollState = rememberScrollState()

    if (dialogState) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Your score", fontWeight = FontWeight.Bold,
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
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Your mistakes", fontWeight = FontWeight.Bold,
                        fontSize = 20.sp, color = MaterialTheme.colors.secondaryVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    if (letters.isEmpty()) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_no_mistake),
                            contentDescription = "No Letter Found",
                            modifier = Modifier.size(32.dp)
                        )
                    } else {
                        Box(
                            modifier = if(letters.size > 8) {
                                Modifier
                                    .graphicsLayer { alpha = 0.99F }
                                    .drawWithContent {
                                        val colors = listOf(
                                            Color.Transparent,
                                            Color.Black,
                                            Color.Transparent
                                        )
                                        drawContent()
                                        drawRect(
                                            brush = Brush.horizontalGradient(colors, -50f, 800f),
                                            blendMode = BlendMode.DstIn
                                        )
                                    }
                            } else Modifier
                        ) {
                            Row(modifier = Modifier.horizontalScroll(scrollState)) {
                                letters.forEach {
                                    Card(
                                        elevation = 10.dp,
                                        backgroundColor = MaterialTheme.colors.onBackground,
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .padding(4.dp)
                                    ) {
                                        Text(
                                            text = it.text,
                                            style = MaterialTheme.typography.h1,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Red,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                    }
                                }
                            }
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
                        border = BorderStroke(1.dp, color = MaterialTheme.colors.onPrimary),
                    ) {
                        Text(text = "OK", color = MaterialTheme.colors.onPrimary)
                    }
                }
            }
        )
    }
}