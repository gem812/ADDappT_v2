package com.example.addappt

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.addappt.navigation.AddapptNavigation
import com.example.addappt.navigation.AddapptScreens
import com.example.addappt.ui.theme.ADDappTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ADDappTTheme {
                AddapptApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddapptApp() {

    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()
    val bottomNavVisibility = remember { mutableStateOf(true) }

    // Hides Bottom Nav on screens where it is not needed
    when (backstackEntry.value?.destination?.route) {
        AddapptScreens.SplashScreen.name -> bottomNavVisibility.value = false
        else -> bottomNavVisibility.value = true
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(visible = bottomNavVisibility.value) {
                NavigationBar {
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
                    NavigationBarItem(
                        selected = backstackEntry.value?.destination?.route.equals(AddapptScreens.AddScreen.name),
                        onClick = { navController.navigate(AddapptScreens.AddScreen.name) },
                        label = {
                            Text("Add")
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add),
                                contentDescription = "Add Icon"
                            )
                        }
                    )
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
            AddapptNavigation(navController = navController)
        }
    }
}
