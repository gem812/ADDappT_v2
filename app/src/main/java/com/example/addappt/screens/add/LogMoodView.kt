package com.example.addappt.screens.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.addappt.models.ui.MoodRowModel
import com.example.addappt.R
@Composable
fun MoodRow(
    mood : MoodRowModel,
    onClick : () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = mood.moodIconUrl,
            contentDescription = "${mood.moodName} Icon",
            modifier = Modifier
                .padding(horizontal = 12.dp)
            )
        Text(
            text = mood.moodName,
            style = MaterialTheme.typography.titleMedium
        )
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

@Preview(showBackground = true)
@Composable
fun MoodRowPreview(){
    
    val moodRowModel = MoodRowModel(
        moodName = "Mood name",
        moodIconUrl = ImageVector.vectorResource(R.drawable.em_starstruck),
        moodRating = 0
    )
    
    MoodRow(mood = moodRowModel) {
        
    }
    
}
