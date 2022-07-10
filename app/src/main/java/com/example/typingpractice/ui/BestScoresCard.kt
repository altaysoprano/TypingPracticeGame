package com.example.typingpractice.ui

import com.example.typingpractice.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.typingpractice.data.Score

@Composable
fun BestScoresCard(modifier: Modifier, backgroundColor: Color, bestScores: List<Score>) {

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
            Text("Your best scores:", fontSize = 30.sp, fontWeight = Bold)
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.silver_medal),
                        contentDescription = "Silver Medal",
                        modifier = Modifier.size(48.dp)
                    )
                    Text(bestScores[1].score.toString())
                }
                Spacer(modifier = Modifier.width(32.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gold_medal),
                        contentDescription = "Gold Medal",
                        modifier = Modifier.size(48.dp)
                    )
                    Text(bestScores[0].score.toString())
                }
                Spacer(modifier = Modifier.width(32.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bronze_medal),
                        contentDescription = "Bronze Medal",
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = bestScores[2].score.toString())
                }
            }
        }
    }
}