package com.example.addappt

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.addappt.navigation.AddapptNavigation
import com.example.addappt.navigation.AddapptScreens
import com.example.addappt.ui.theme.ADDappTTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ADDappTTheme {
                AddapptApp(
                    navToEnableUsageStats = {
                        startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddapptApp(
    navToEnableUsageStats : () -> Unit
) {

    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()
    val bottomNavVisibility = remember { mutableStateOf(true) }

    // Hides Bottom Nav on screens where it is not needed
    when (backstackEntry.value?.destination?.route) {
        AddapptScreens.SplashScreen.name -> bottomNavVisibility.value = false
        AddapptScreens.IntroScreen.name -> bottomNavVisibility.value = false
        AddapptScreens.LoginScreen.name -> bottomNavVisibility.value = false
        AddapptScreens.AddScreen.name -> bottomNavVisibility.value = false
        else -> bottomNavVisibility.value = true
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(visible = bottomNavVisibility.value) {
                NavigationBar(

                ) {
                    NavigationBarItem(
                        selected = backstackEntry.value?.destination?.route.equals(AddapptScreens.HomeScreen.name),
                        onClick = { navController.navigate(AddapptScreens.HomeScreen.name) },
                        label = {
                            Text("Home")
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_home),
                                contentDescription = "Home Icon"
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = backstackEntry.value?.destination?.route.equals(AddapptScreens.MotivationScreen.name),
                        onClick = { navController.navigate(AddapptScreens.MotivationScreen.name) },
                        label = {
                            Text("Motivation")
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_calendar),
                                contentDescription = "Calendar Icon"
                            )
                        }
                    )
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(AddapptScreens.AddScreen.name)
                        },
                        modifier = Modifier
                            .padding(top = 6.dp),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add floating action button icon")
                    }
                    NavigationBarItem(
                        selected = backstackEntry.value?.destination?.route.equals(AddapptScreens.StatsScreen.name),
                        onClick = { navController.navigate(AddapptScreens.StatsScreen.name) },
                        label = {
                            Text("Stats")
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_graph),
                                contentDescription = "Graph Icon"
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = backstackEntry.value?.destination?.route.equals(AddapptScreens.SettingsScreen.name),
                        onClick = { navController.navigate(AddapptScreens.SettingsScreen.name) },
                        label = {
                            Text("Settings")
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_home),
                                contentDescription = "Settings Icon"
                            )
                        }
                    )
                }
            }
        }
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            AddapptNavigation(navController = navController, navToEnableUsageStats = navToEnableUsageStats)
        }
    }
}
