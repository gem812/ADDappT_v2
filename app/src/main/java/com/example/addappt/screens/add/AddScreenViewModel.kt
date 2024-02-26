package com.example.addappt.screens.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.addappt.constants.DailyTrackingStage

class AddScreenViewModel : ViewModel() {

    var trackerStage by mutableStateOf(DailyTrackingStage.DAILY_MOOD_STAGE)

    var trackingStageTitle by mutableStateOf(
        "Current Mood"
    )

    fun updateStage(updatedStage: DailyTrackingStage) {
        trackerStage = updatedStage
        trackingStageTitle = when (trackerStage) {
            DailyTrackingStage.DAILY_MOOD_STAGE -> "Current Mood"
            DailyTrackingStage.DAILY_FOCUS_STAGE -> "Current Focus"
            DailyTrackingStage.SLEEP_DURATION_QUALITY_STAGE -> "Sleep Duration & Quality"
            DailyTrackingStage.WATER_INTAKE_STAGE -> "Water Intake"
            DailyTrackingStage.EXERCISE_STAGE -> "Daily Exercise"
        }
    }

}