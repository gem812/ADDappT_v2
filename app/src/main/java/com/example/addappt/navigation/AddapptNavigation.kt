package com.example.addappt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.addappt.screens.add.AddScreen
import com.example.addappt.screens.home.HomeScreen
import com.example.addappt.screens.motivation.MotivationScreen
import com.example.addappt.screens.profile.ProfileScreen
import com.example.addappt.screens.settings.SettingsScreen
import com.example.addappt.screens.stats.StatsScreen
import com.google.common.math.Stats

@Composable
fun AddapptNavigation(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = AddapptScreens.HomeScreen.name
    ){
        composable(AddapptScreens.SplashScreen.name){

        }
        composable(AddapptScreens.IntroScreen.name){

        }
        composable(AddapptScreens.HomeScreen.name){
            HomeScreen()
        }
        composable(AddapptScreens.MotivationScreen.name){
            MotivationScreen()
        }
        composable(AddapptScreens.AddScreen.name){
            AddScreen()
        }
        composable(AddapptScreens.StatsScreen.name){
            StatsScreen()
        }
        composable(AddapptScreens.SettingsScreen.name){
            SettingsScreen(navController = navController)
        }
        composable(AddapptScreens.ProfileScreen.name){
            ProfileScreen(navController = navController)
        }
    }
}