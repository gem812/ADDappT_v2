package com.example.addappt.screens.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.addappt.constants.DailyTrackingStage

class AddScreenViewModel : ViewModel() {

    var trackerStage by mutableStateOf(DailyTrackingStage.DAILY_MOOD_STAGE)

    fun updateStage(updatedStage : DailyTrackingStage){
        trackerStage = updatedStage
    }

}