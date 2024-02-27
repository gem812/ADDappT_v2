package com.example.addappt.screens.add

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.addappt.constants.DailyTrackingStage
import com.example.addappt.navigation.AddapptScreens
import com.example.addappt.R
import com.example.addappt.models.ui.MoodRowModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavController, viewModel: AddScreenViewModel = viewModel()) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = viewModel.trackingStageTitle,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    actions = {
                    },
                    navigationIcon = {
                        if(viewModel.trackerStage == DailyTrackingStage.DAILY_MOOD_STAGE) {
                            IconButton(
                                onClick = { navController.navigate(AddapptScreens.HomeScreen.name) }
                            ) {
                                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                            }
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
            ) {
                when (viewModel.trackerStage) {
                    DailyTrackingStage.DAILY_MOOD_STAGE -> LogMoodScreen(navController = navController, viewModel = viewModel)
                    DailyTrackingStage.DAILY_FOCUS_STAGE -> LogFocusScreen(viewModel = viewModel)
                    DailyTrackingStage.SLEEP_DURATION_QUALITY_STAGE -> LogSleepScreen(viewModel = viewModel)
                    DailyTrackingStage.WATER_INTAKE_STAGE -> LogWaterScreen(viewModel = viewModel)
                    DailyTrackingStage.EXERCISE_STAGE -> LogExerciseScreen(navController = navController, viewModel = viewModel)
                    DailyTrackingStage.TEST_SCREEN -> TestScreen(navController = navController, viewModel = viewModel)
                }
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TestScreen(viewModel: AddScreenViewModel, navController: NavController) {

    val state by viewModel.trackerFlowState.collectAsState()

    Column {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                text = "Current State Before save to DB",
                fontSize = 24.sp
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                thickness = 2.dp
            )

            Text(text = "Current Mood : ${state.currentMoodName}", modifier = Modifier.padding(horizontal = 12.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "Current Mood Rating : ${state.currentMoodRating}", modifier = Modifier.padding(horizontal = 12.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "Focus mins : ${state.minsOfFocus} mins", modifier = Modifier.padding(horizontal = 12.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "Focus level : ${state.focusLevelPercentage} %", modifier = Modifier.padding(horizontal = 12.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "Sleep hours : ${state.hoursOfSleep} hrs", modifier = Modifier.padding(horizontal = 12.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "Sleep quality : ${state.sleepQualityRating}", modifier = Modifier.padding(horizontal = 12.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "Water Intake : ${state.waterIntake} mls", modifier = Modifier.padding(horizontal = 12.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "Exercise mins : ${state.minsOfExercise} mins", modifier = Modifier.padding(horizontal = 12.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "Exercise intensity : ${state.exerciseIntensity}", modifier = Modifier.padding(horizontal = 12.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "Date : ${state.dateOfLog}", modifier = Modifier.padding(horizontal = 12.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)

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
                    onClick = { viewModel.updateStage(DailyTrackingStage.EXERCISE_STAGE) }
                ) {
                    Text("Back")
                }
                Button(
                    onClick = {
                        navController.navigate(AddapptScreens.HomeScreen.name)
                    }
                ) {
                    Text("Finish")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun LogMoodScreen(navController: NavController, viewModel: AddScreenViewModel) {

    val moodList = listOf<MoodRowModel>(
        MoodRowModel(
            moodName = "Energised",
            moodIconUrl = ImageVector.vectorResource(R.drawable.em_starstruck),
            moodRating = 9
        ),
        MoodRowModel(
            moodName = "Very Good",
            moodIconUrl = ImageVector.vectorResource(R.drawable.em_very_good),
            moodRating = 8
        ),
        MoodRowModel(
            moodName = "Good",
            moodIconUrl = ImageVector.vectorResource(R.drawable.em_good),
            moodRating = 7
        ),
        MoodRowModel(
            moodName = "Ok",
            moodIconUrl = ImageVector.vectorResource(R.drawable.em_ok),
            moodRating = 6
        ),
        MoodRowModel(
            moodName = "Meh",
            moodIconUrl = ImageVector.vectorResource(R.drawable.em_meh),
            moodRating = 5
        ),
        MoodRowModel(
            moodName = "Not Ok",
            moodIconUrl = ImageVector.vectorResource(R.drawable.em_not_ok),
            moodRating = 4
        ),
        MoodRowModel(
            moodName = "Bad",
            moodIconUrl = ImageVector.vectorResource(R.drawable.em_bad),
            moodRating = 3
        ),
        MoodRowModel(
            moodName = "Very Bad",
            moodIconUrl = ImageVector.vectorResource(R.drawable.em_very_bad),
            moodRating = 2
        ),
        MoodRowModel(
            moodName = "No Energy",
            moodIconUrl = ImageVector.vectorResource(R.drawable.em_no_energy),
            moodRating = 1
        )
    )

    Column {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            MoodsColumn(moodList = moodList, onClick = { name, rating ->
                viewModel.setCurrentMood(moodName = name, moodRating = rating)
                viewModel.updateStage(DailyTrackingStage.DAILY_FOCUS_STAGE)
            })
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun LogFocusScreen(viewModel: AddScreenViewModel) {

    val state by viewModel.trackerFlowState.collectAsState()

    val minsOfFocus = remember { mutableStateOf(state.minsOfFocus/15) }
    val displayMins = minsOfFocus.value * 15
    val sliderPositionState = remember { mutableStateOf(0f) }
    val focusPercentage = (sliderPositionState.value * 100).toInt()

    Column {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Text("How many minutes of focus were you able to apply today?")

            Row() {
                Box(
                    modifier = Modifier
                        .weight(0.25f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = { minsOfFocus.value-- }
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
                    Text(
                        text = "$displayMins mins",
                        modifier = Modifier.padding(horizontal = 12.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier = Modifier
                        .weight(0.25f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = { minsOfFocus.value++ }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = "Add icon"
                        )
                    }
                }
            }

            Text("As a %, what level of focus were you able to apply")

            Row {
                Box(
                    modifier = Modifier
                        .weight(0.2f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_no_focus),
                            contentDescription = "Insomnia icon",
                            modifier = Modifier
                                .height(60.dp)
                        )
                        Text("0 %")
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(0.6f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Slider(
                            value = sliderPositionState.value,
                            onValueChange = { newVal ->
                                sliderPositionState.value = newVal
                            },
                            modifier = Modifier.padding(4.dp),
                            steps = 9
                        )
                        Text(text = "${focusPercentage.toString()} %")
                    }

                }
                Box(
                    modifier = Modifier
                        .weight(0.2f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_focussed),
                            contentDescription = "Sleep icon",
                            modifier = Modifier
                                .height(60.dp)
                        )
                        Text("100%")
                    }
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
                    onClick = { viewModel.updateStage(DailyTrackingStage.DAILY_MOOD_STAGE) }
                ) {
                    Text("Back")
                }
                Button(
                    onClick = {
                        viewModel.setMinsOfFocus(mins = displayMins)
                        viewModel.setFocusLevelPercentage(focusLevel = focusPercentage)
                        viewModel.updateStage(DailyTrackingStage.SLEEP_DURATION_QUALITY_STAGE)
                    }
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun LogSleepScreen(viewModel: AddScreenViewModel) {

    val state by viewModel.trackerFlowState.collectAsState()

    val hoursOfSleep = remember { mutableStateOf(state.hoursOfSleep) }
    val sliderPositionState = remember { mutableStateOf(0f) }

    Column {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

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
                    Text(
                        text = "${hoursOfSleep.value} hrs",
                        modifier = Modifier.padding(horizontal = 12.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold)
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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_insomnia),
                            contentDescription = "Insomnia icon",
                            modifier = Modifier
                                .size(40.dp)
                        )
                        Text("Restless")
                    }
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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sleep),
                            contentDescription = "Sleep icon",
                            modifier = Modifier
                                .size(40.dp)
                        )
                        Text(text = "Deep Sleep")
                    }
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
                    onClick = {
                        viewModel.setHoursOfSleep(hours = hoursOfSleep.value)
                        viewModel.setSleepQualityRating(quality = (sliderPositionState.value * 10).toInt())
                        viewModel.updateStage(DailyTrackingStage.WATER_INTAKE_STAGE)
                    }
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun LogWaterScreen(viewModel: AddScreenViewModel) {

    val state by viewModel.trackerFlowState.collectAsState()

    val noOfGlassesClicked = remember { mutableStateOf(state.waterIntake/250) }
    val amountInMilliliters = remember { mutableStateOf(0) }

    amountInMilliliters.value = noOfGlassesClicked.value * 250
    
    Column {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "${amountInMilliliters.value} mls",
                    modifier = Modifier.padding(horizontal = 12.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_water_drop),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp),
                    tint = Color.Blue
                )
            }
            WaterGrid(
                indexOfLastClicked = noOfGlassesClicked.value - 1
            ) { indexOfClicked ->
                noOfGlassesClicked.value = indexOfClicked + 1
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
                    onClick = { viewModel.updateStage(DailyTrackingStage.SLEEP_DURATION_QUALITY_STAGE) }
                ) {
                    Text("Back")
                }
                Button(
                    onClick = { viewModel.updateStage(DailyTrackingStage.EXERCISE_STAGE) }
                ) {
                    viewModel.setWaterIntake(intake = amountInMilliliters.value)
                    Text("Next")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun LogExerciseScreen(navController: NavController, viewModel: AddScreenViewModel) {

    val state by viewModel.trackerFlowState.collectAsState()

    val minsOfExercise = remember { mutableStateOf(state.minsOfExercise / 15) }
    val displayMins = minsOfExercise.value * 15
    val sliderPositionState = remember { mutableStateOf(0f) }

    Column {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

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
                    Text(
                        text = "$displayMins mins",
                        modifier = Modifier.padding(horizontal = 12.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold)
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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sitting),
                            contentDescription = "Sitting icon",
                            modifier = Modifier
                                .size(40.dp)
                        )
                        Text("Low")
                    }

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
                    Column(

                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_running),
                            contentDescription = "Sprint icon",
                            modifier = Modifier
                                .size(40.dp)
                        )
                        Text("High")
                    }
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
                    onClick = { viewModel.updateStage(DailyTrackingStage.WATER_INTAKE_STAGE) }
                ) {
                    Text("Back")
                }
                Button(
                    onClick = {
                        viewModel.setMinsOfExercise(mins = displayMins)
                        viewModel.setExerciseIntensity(intensity = sliderPositionState.value.toString())
                        viewModel.updateStage(DailyTrackingStage.TEST_SCREEN)
                    }
                ) {
                    Text("To Test Screen")
                }
            }
        }
    }
}