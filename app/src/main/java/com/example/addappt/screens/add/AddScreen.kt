package com.example.addappt.screens.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.addappt.constants.DailyTrackingStage
import com.example.addappt.navigation.AddapptScreens

@Composable
fun AddScreen(navController: NavController, viewModel : AddScreenViewModel = viewModel()){

    val dailyTrackerStage = viewModel.trackerStage

    when (viewModel.trackerStage){
        DailyTrackingStage.DAILY_MOOD_STAGE -> LogMoodScreen(navController = navController, viewModel = viewModel)
        DailyTrackingStage.DAILY_FOCUS_STAGE -> LogFocusScreen(viewModel = viewModel)
        DailyTrackingStage.SLEEP_DURATION_QUALITY_STAGE -> LogSleepScreen(viewModel = viewModel)
        DailyTrackingStage.WATER_INTAKE_STAGE -> LogWaterScreen(viewModel = viewModel)
        DailyTrackingStage.EXERCISE_STAGE -> LogExerciseScreen(navController = navController, viewModel = viewModel)
    }

}

@Composable
private fun LogMoodScreen(navController: NavController, viewModel : AddScreenViewModel){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Log Mood Screen")
        Button(
            onClick = { viewModel.updateStage(DailyTrackingStage.DAILY_FOCUS_STAGE) }
        ) {
            Text("Next")
        }
        Button(
            onClick = { navController.navigate(AddapptScreens.HomeScreen.name) }
        ) {
            Text("Back")
        }
    }
}

@Composable
private fun LogFocusScreen(viewModel : AddScreenViewModel){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Log Focus Screen")
        Button(
            onClick = { viewModel.updateStage(DailyTrackingStage.SLEEP_DURATION_QUALITY_STAGE) }
        ) {
            Text("Next")
        }
        Button(
            onClick = { viewModel.updateStage(DailyTrackingStage.DAILY_MOOD_STAGE) }
        ) {
            Text("Back")
        }
    }
}

@Composable
private fun LogSleepScreen(viewModel : AddScreenViewModel){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Log Sleep Screen")
        Button(
            onClick = { viewModel.updateStage(DailyTrackingStage.WATER_INTAKE_STAGE) }
        ) {
            Text("Next")
        }
        Button(
            onClick = { viewModel.updateStage(DailyTrackingStage.DAILY_MOOD_STAGE) }
        ) {
            Text("Back")
        }
    }
}

@Composable
private fun LogWaterScreen(viewModel : AddScreenViewModel){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Log Water Screen")
        Button(
            onClick = { viewModel.updateStage(DailyTrackingStage.EXERCISE_STAGE) }
        ) {
            Text("Next")
        }
        Button(
            onClick = { viewModel.updateStage(DailyTrackingStage.SLEEP_DURATION_QUALITY_STAGE) }
        ) {
            Text("Back")
        }
    }
}

@Composable
private fun LogExerciseScreen(navController: NavController, viewModel : AddScreenViewModel){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Log Exercise Screen")
        Button(
            onClick = { navController.navigate(AddapptScreens.HomeScreen.name) }
        ) {
            Text("Finish")
        }
        Button(
            onClick = { viewModel.updateStage(DailyTrackingStage.WATER_INTAKE_STAGE) }
        ) {
            Text("Back")
        }
    }
}