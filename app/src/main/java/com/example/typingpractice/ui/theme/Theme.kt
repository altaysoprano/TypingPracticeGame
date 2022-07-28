package com.example.typingpractice.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = white800,
    background = white,
    onBackground = white900,
    surface = white600,
    error = white900,
    onSurface = white900,
    secondary = white200,
    onSecondary = white200,
    onPrimary = white100,
    secondaryVariant = white500
)

private val LightColorPalette = lightColors(
    primary = Color.LightGray,
    background = white900,
    onBackground = white50,
    surface = white200,
    onSurface = white50,
    secondary = white700,
    error = white900,
    onSecondary = white850,
    onPrimary = white800,
    secondaryVariant = white300
)

@Composable
fun TypingPracticeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    if(darkTheme){
        systemUiController.setSystemBarsColor(
            color = Color.Black
        )
    }else{
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
    }

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}