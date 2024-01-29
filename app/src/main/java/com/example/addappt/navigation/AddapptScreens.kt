package com.example.addappt.navigation

enum class AddapptScreens {
    SplashScreen,
    IntroScreen,
    HomeScreen,
    MotivationScreen,
    StatsScreen,
    AddScreen,
    SettingsScreen,
    ProfileScreen;

    companion object{
        fun fromRoute(route : String?) : AddapptScreens
        = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            IntroScreen.name -> IntroScreen
            HomeScreen.name -> HomeScreen
            MotivationScreen.name -> MotivationScreen
            StatsScreen.name -> StatsScreen
            AddScreen.name -> AddScreen
            SettingsScreen.name -> SettingsScreen
            ProfileScreen.name -> ProfileScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route : $route is not recognised")
        }
    }
}