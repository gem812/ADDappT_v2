package com.example.addappt.screens.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.addappt.models.ui.MoodRowModel
@Composable
fun MoodRow(
    mood : MoodRowModel,
    onClick : () -> Unit
) {
    Row (
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Card {

        }
        Image(imageVector = mood.moodIconUrl, contentDescription = "${mood.moodName} Icon")
        Text(mood.moodName)
    }
}

@Composable
fun MoodsColumn(
    moodList: List<MoodRowModel>,
    onClick: (Int) -> Unit
) {
    LazyColumn{
        moodList.forEach { mood ->
            item {
                MoodRow(mood = mood, onClick = {
                    onClick(mood.moodRating)
                })
            }
        }
    }
}
