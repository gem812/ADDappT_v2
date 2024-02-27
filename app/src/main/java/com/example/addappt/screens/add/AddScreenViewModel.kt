package com.example.addappt.screens.add

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.addappt.constants.DailyTrackingStage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.Instant
import java.util.Date


data class AddScreenFlowState @RequiresApi(Build.VERSION_CODES.O) constructor(
    val currentMoodName : String = "",
    val currentMoodRating : Int = 0,
    val minsOfFocus : Int = 0,
    val focusLevelPercentage : Int = 0,
    val hoursOfSleep : Int = 8,
    val sleepQualityRating : Int = 0,
    val waterIntake : Int = 0,
    val minsOfExercise : Int = 0,
    val exerciseIntensity : String = "",
    val dateOfLog : Date = Date.from(Instant.now())
)

class AddScreenViewModel : ViewModel() {

    // Add mood tracker flow stage control

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
            DailyTrackingStage.TEST_SCREEN -> "TEST SCREEN" //TODO: DELETE WHEN FINISHED WITH IT
        }
    }

    // Add mood tracker flow state control

    @RequiresApi(Build.VERSION_CODES.O)
    private val _trackerFlowState = MutableStateFlow(
        AddScreenFlowState()
    )

    @RequiresApi(Build.VERSION_CODES.O)
    val trackerFlowState : StateFlow<AddScreenFlowState> = _trackerFlowState.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun setCurrentMood(moodName : String, moodRating : Int) {
        _trackerFlowState.update {state ->
            state.copy(
                currentMoodName = moodName,
                currentMoodRating = moodRating
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setMinsOfFocus(mins : Int) {
        _trackerFlowState.update { state ->
            state.copy(
                minsOfFocus = mins
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setFocusLevelPercentage(focusLevel : Int){
        _trackerFlowState.update { state->
            state.copy(
                focusLevelPercentage = focusLevel
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setHoursOfSleep(hours : Int) {
        _trackerFlowState.update { state ->
            state.copy(
                hoursOfSleep = hours
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setSleepQualityRating(quality : Int){
        _trackerFlowState.update { state ->
            state.copy(
                sleepQualityRating = quality
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setWaterIntake(intake : Int){
        _trackerFlowState.update { state ->
            state.copy(
                waterIntake = intake
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setMinsOfExercise(mins : Int) {
        _trackerFlowState.update { state ->
            state.copy(
                minsOfExercise = mins
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setExerciseIntensity(intensity : String) {
        _trackerFlowState.update { state ->
            state.copy(
                exerciseIntensity = intensity
            )
        }
    }

}