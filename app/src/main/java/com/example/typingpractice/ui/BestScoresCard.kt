package com.example.typingpractice.ui

import android.widget.Space
import com.example.typingpractice.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.typingpractice.data.Letter
import com.example.typingpractice.data.Score

@Composable
fun BestScoresCard(
    modifier: Modifier,
    backgroundColor: Color,
    letters: List<Letter>,
    bestScores: List<Score>,
    totalScore: Int
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
        elevation = 10.dp,
        backgroundColor = backgroundColor
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Your statistics:", fontSize = 30.sp, fontWeight = Bold)
            Row(
                modifier = Modifier
                    .padding(12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                LettersCard(backgroundColor = backgroundColor, letters = letters)
                Card(
                    shape = RoundedCornerShape(5.dp),
                    elevation = 10.dp,
                    backgroundColor = backgroundColor
                ) {
                    Column(
                        modifier = Modifier
                            .padding(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Your best scores:", fontSize = 24.sp,
                            fontWeight = Bold, color = MaterialTheme.colors.surface
                        )
                        Row {
                            Column(
                                modifier = Modifier
                                    .padding(top = 12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.silver_medal),
                                    contentDescription = "Silver Medal",
                                    modifier = Modifier.size(32.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    bestScores[1].score.toString(),
                                    style = MaterialTheme.typography.h1,
                                    fontSize = 16.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(32.dp))
                            Column(
                                modifier = Modifier,
                                horizontalAlignment = Alignment.CenterHorizontally

                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.gold_medal),
                                    contentDescription = "Gold Medal",
                                    modifier = Modifier.size(32.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    bestScores[0].score.toString(),
                                    style = MaterialTheme.typography.h1,
                                    fontSize = 16.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(32.dp))
                            Column(
                                modifier = Modifier
                                    .padding(top = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.bronze_medal),
                                    contentDescription = "Bronze Medal",
                                    modifier = Modifier.size(32.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = bestScores[2].score.toString(),
                                    style = MaterialTheme.typography.h1,
                                    fontSize = 16.sp
                                )
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(bottom = 12.dp)
                        ) {
                            Card(
                                shape = RoundedCornerShape(5.dp),
                                elevation = 10.dp,
                                backgroundColor = MaterialTheme.colors.secondaryVariant
                            ) {
                                Row(modifier = Modifier.padding(4.dp)) {
                                    Text(
                                        text = "Your total score: ",
                                        fontWeight = Bold
                                    )
                                    Text(
                                        text = "$totalScore",
                                        style = MaterialTheme.typography.h1,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}