package com.example.addappt.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.addappt.screens.add.AddScreen
import com.example.addappt.screens.home.HomeScreen
import com.example.addappt.screens.intro.IntroScreen
import com.example.addappt.screens.login.LoginScreen
import com.example.addappt.screens.motivation.MotivationScreen
import com.example.addappt.screens.profile.ProfileScreen
import com.example.addappt.screens.settings.SettingsScreen
import com.example.addappt.screens.splash.SplashScreen
import com.example.addappt.screens.stats.StatsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddapptNavigation(
    navController: NavHostController,
    navToEnableUsageStats: () -> Unit
){

    NavHost(
        navController = navController,
        startDestination = AddapptScreens.SplashScreen.name
    ){
        composable(AddapptScreens.SplashScreen.name){
            SplashScreen(navController = navController)
        }
        composable(AddapptScreens.IntroScreen.name){
            IntroScreen(navController = navController)
        }
        composable(AddapptScreens.LoginScreen.name){
            LoginScreen(navController = navController)
        }
        composable(AddapptScreens.HomeScreen.name){
            HomeScreen(navToEnableUsageStats = navToEnableUsageStats)
        }
        composable(AddapptScreens.MotivationScreen.name){
            MotivationScreen()
        }
        composable(AddapptScreens.AddScreen.name){
            AddScreen(navController = navController)
        }
        composable(AddapptScreens.StatsScreen.name){
            StatsScreen()
        }
        composable(AddapptScreens.SettingsScreen.name){
            SettingsScreen(navController = navController, navToUsagePermissions = navToEnableUsageStats)
        }
        composable(AddapptScreens.ProfileScreen.name){
            ProfileScreen(navController = navController)
        }
    }
}