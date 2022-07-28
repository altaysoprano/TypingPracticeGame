package com.example.typingpractice.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.typingpractice.data.Letter

@Composable
fun LettersCard(
    letters: List<Letter>,
    backgroundColor: Color
) {
    Card(
        shape = RoundedCornerShape(5.dp),
        backgroundColor = backgroundColor,
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 24.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Most Misspelled", fontSize = 16.sp,
                fontWeight = Bold, color = MaterialTheme.colors.onPrimary
            )
            Row {
                Card(
                    elevation = 10.dp,
                    backgroundColor = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(4.dp)
                ) {
                    Text(
                        text = letters[0].letter,
                        style = MaterialTheme.typography.h1,
                        fontSize = 16.sp,
                        fontWeight = Bold,
                        color = Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Card(
                    elevation = 10.dp,
                    backgroundColor = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(4.dp)
                ) {
                    Text(
                        text = letters[1].letter,
                        style = MaterialTheme.typography.h1,
                        fontSize = 16.sp,
                        fontWeight = Bold,
                        color = Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Card(
                    elevation = 10.dp,
                    backgroundColor = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(4.dp)
                ) {
                    Text(
                        text = letters[2].letter,
                        style = MaterialTheme.typography.h1,
                        fontSize = 16.sp,
                        fontWeight = Bold,
                        color = Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

    }
}