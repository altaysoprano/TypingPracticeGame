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
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Your statistics:", fontSize = 30.sp, fontWeight = Bold)
            Column(
                modifier = Modifier
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    shape = RoundedCornerShape(5.dp),
                    backgroundColor = backgroundColor,
                    modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Best Scores:", fontSize = 20.sp,
                            fontWeight = Bold, color = MaterialTheme.colors.onPrimary
                        )
                        Row(modifier = Modifier.padding(vertical = 8.dp)) {
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
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(bottom = 12.dp)
                        ) {
                            Card(
                                shape = RoundedCornerShape(5.dp),
                                elevation = 10.dp,
                                backgroundColor = MaterialTheme.colors.onPrimary
                            ) {
                                Column(
                                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 24.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Total Score",
                                        fontSize = 14.sp,
                                        fontWeight = Bold,
                                        color = MaterialTheme.colors.surface
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "$totalScore",
                                        style = MaterialTheme.typography.h1,
                                        fontSize = 24.sp,
                                        color = MaterialTheme.colors.secondaryVariant
                                    )
                                }
                            }
                        }
                    }
                }
                LettersCard(backgroundColor = backgroundColor, letters = letters)
            }
        }
    }
}