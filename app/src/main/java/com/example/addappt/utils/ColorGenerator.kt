package com.example.addappt.utils

import androidx.compose.ui.graphics.Color

fun screenTimeColorGenerator(hours : Long) : Color {
    val color = if (hours < 2L) {
        Color.Green.copy(alpha = 0.5f)
    } else if (hours in 2L..4L) {
        Color.Yellow.copy(alpha = 0.5f)
    } else {
        Color.Red.copy(alpha = 0.5f)
    }
    return color
}