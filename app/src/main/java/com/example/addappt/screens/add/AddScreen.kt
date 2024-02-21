package com.example.addappt.screens.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.addappt.constants.DailyTrackingStage
import com.example.addappt.navigation.AddapptScreens
import com.example.addappt.R
import com.example.addappt.models.ui.MoodRowModel

@Composable
fun AddScreen(navController: NavController, viewModel: AddScreenViewModel = viewModel()) {

    val dailyTrackerStage = viewModel.trackerStage

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (viewModel.trackerStage) {
            DailyTrackingStage.DAILY_MOOD_STAGE -> LogMoodScreen(navController = navController, viewModel = viewModel)
            DailyTrackingStage.DAILY_FOCUS_STAGE -> LogFocusScreen(viewModel = viewModel)
            DailyTrackingStage.SLEEP_DURATION_QUALITY_STAGE -> LogSleepScreen(viewModel = viewModel)
            DailyTrackingStage.WATER_INTAKE_STAGE -> LogWaterScreen(viewModel = viewModel)
            DailyTrackingStage.EXERCISE_STAGE -> LogExerciseScreen(navController = navController, viewModel = viewModel)
        }
    }

}

@Composable
private fun LogMoodScreen(navController: NavController, viewModel: AddScreenViewModel) {

    val moodList = listOf<MoodRowModel>(
        MoodRowModel(moodName = "Energised", moodIconUrl = ImageVector.vectorResource(R.drawable.em_starstruck), moodRating = 9),
        MoodRowModel(moodName = "Very Good", moodIconUrl = ImageVector.vectorResource(R.drawable.em_starstruck), moodRating = 8),
        MoodRowModel(moodName = "Good", moodIconUrl = ImageVector.vectorResource(R.drawable.em_starstruck), moodRating = 7),
        MoodRowModel(moodName = "Ok", moodIconUrl = ImageVector.vectorResource(R.drawable.em_starstruck), moodRating = 6),
        MoodRowModel(moodName = "Meh", moodIconUrl = ImageVector.vectorResource(R.drawable.em_starstruck), moodRating = 5),
        MoodRowModel(moodName = "Not Ok", moodIconUrl = ImageVector.vectorResource(R.drawable.em_starstruck), moodRating = 4),
        MoodRowModel(moodName = "Bad", moodIconUrl = ImageVector.vectorResource(R.drawable.em_starstruck), moodRating = 3),
        MoodRowModel(moodName = "Very Bad", moodIconUrl = ImageVector.vectorResource(R.drawable.em_starstruck), moodRating = 2),
        MoodRowModel(moodName = "No Energy", moodIconUrl = ImageVector.vectorResource(R.drawable.em_starstruck), moodRating = 1)
    )

    Column {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            MoodsColumn(moodList = moodList, onClick = {})
        }
        Column(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { navController.navigate(AddapptScreens.HomeScreen.name) }
                ) {
                    Text("Back")
                }
                Button(
                    onClick = { viewModel.updateStage(DailyTrackingStage.DAILY_FOCUS_STAGE) }
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
private fun LogFocusScreen(viewModel: AddScreenViewModel) {
    Column {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Log Focus Screen")
        }
        Column(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { viewModel.updateStage(DailyTrackingStage.DAILY_MOOD_STAGE) }
                ) {
                    Text("Back")
                }
                Button(
                    onClick = { viewModel.updateStage(DailyTrackingStage.SLEEP_DURATION_QUALITY_STAGE) }
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
private fun LogSleepScreen(viewModel: AddScreenViewModel) {

    Column {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            var hoursOfSleep = remember { mutableStateOf(8) }
            var sliderPositionState = remember { mutableStateOf(0f) }

            Text("How many hours of sleep did you get last night?")

            Row() {
                Box(
                    modifier = Modifier
                        .weight(0.25f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = { hoursOfSleep.value-- }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_subtract),
                            contentDescription = "Subtract Icon"
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(0.5f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(hoursOfSleep.value.toString())
                }
                Box(
                    modifier = Modifier
                        .weight(0.25f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = { hoursOfSleep.value++ }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = "Add icon"
                        )
                    }
                }
            }

            Text("How would you rate the quality of your sleep?")

            Row {
                Box(
                    modifier = Modifier
                        .weight(0.2f),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_insomnia),
                        contentDescription = "Insomnia icon",
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(0.6f),
                    contentAlignment = Alignment.Center
                ) {
                    Slider(
                        value = sliderPositionState.value,
                        onValueChange = { newVal ->
                            sliderPositionState.value = newVal
                        },
                        modifier = Modifier.padding(4.dp),
                        steps = 9
                    )

                }
                Box(
                    modifier = Modifier
                        .weight(0.2f),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sleep),
                        contentDescription = "Sleep icon",
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { viewModel.updateStage(DailyTrackingStage.DAILY_FOCUS_STAGE) }
                ) {
                    Text("Back")
                }
                Button(
                    onClick = { viewModel.updateStage(DailyTrackingStage.WATER_INTAKE_STAGE) }
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
private fun LogWaterScreen(viewModel: AddScreenViewModel) {

    Column {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            WaterGrid()
        }
        Column(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { viewModel.updateStage(DailyTrackingStage.SLEEP_DURATION_QUALITY_STAGE) }
                ) {
                    Text("Back")
                }
                Button(
                    onClick = { viewModel.updateStage(DailyTrackingStage.EXERCISE_STAGE) }
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
private fun LogExerciseScreen(navController: NavController, viewModel: AddScreenViewModel) {

    Column {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            var minsOfExercise = remember { mutableStateOf(0) }
            var displayMins = minsOfExercise.value * 15
            var sliderPositionState = remember { mutableStateOf(0f) }

            Text("How many minutes of exercise did you achieve today?")

            Row() {
                Box(
                    modifier = Modifier
                        .weight(0.25f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = { minsOfExercise.value-- }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_subtract),
                            contentDescription = "Subtract Icon"
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(0.5f),
                    contentAlignment = Alignment.Center
                ) {
                    Text("$displayMins mins")
                }
                Box(
                    modifier = Modifier
                        .weight(0.25f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = { minsOfExercise.value++ }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = "Add icon"
                        )
                    }
                }
            }

            Text("How would you rate the intensity of your exercise?")

            Row {
                Box(
                    modifier = Modifier
                        .weight(0.2f),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sitting),
                        contentDescription = "Insomnia icon",
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(0.6f),
                    contentAlignment = Alignment.Center
                ) {
                    Slider(
                        value = sliderPositionState.value,
                        onValueChange = { newVal ->
                            sliderPositionState.value = newVal
                        },
                        modifier = Modifier.padding(4.dp),
                        steps = 3
                    )

                }
                Box(
                    modifier = Modifier
                        .weight(0.2f),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_running),
                        contentDescription = "Sleep icon",
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text("Low Intensity")
                Text("High Intensity")
            }
        }
        Column(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { viewModel.updateStage(DailyTrackingStage.WATER_INTAKE_STAGE) }
                ) {
                    Text("Back")
                }
                Button(
                    onClick = { /**TODO*/ }
                ) {
                    Text("Finish")
                }
            }
        }
    }
}