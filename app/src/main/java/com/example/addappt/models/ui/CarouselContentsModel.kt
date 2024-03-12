package com.example.addappt.models.ui

import androidx.compose.ui.graphics.Color

data class CarouselContentsModel(
    val title : String = "",
    val icon : Int = 0,
    val text : String = "",
    val optionalSecondaryText : String =  "",
    val color : Color = Color.LightGray
)
