package com.example.addappt.models.ui

import androidx.compose.ui.graphics.vector.ImageVector

data class MoodRowModel(
    val moodName : String,
    val moodIconUrl : ImageVector,
    val moodRating : Int
)
