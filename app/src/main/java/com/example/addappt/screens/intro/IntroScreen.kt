package com.example.addappt.screens.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.addappt.navigation.AddapptScreens

@Composable
fun IntroScreen(navController: NavController){
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color(0xFFE5C5F0)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .padding(36.dp)
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .height(360.dp)
                        .clip(shape = RoundedCornerShape(24.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Image",
                        fontSize = 60.sp
                    )
                }
                Text(
                    text = "Hi!",
                    modifier = Modifier
                        .padding(horizontal = 24.dp),
                    fontSize = 60.sp
                )
                Text(
                    text = "Using daily habitual behaviour tracking will help you find your blocks. By knowing and understanding each block you can use them to build a strong positive and productive you ",
                    modifier = Modifier
                        .padding(horizontal = 12.dp),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            navController.navigate(AddapptScreens.HomeScreen.name)
                        },
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.DarkGray
                        )
                    ){
                        Text(
                            text = "You've Got This!!!!",
                            modifier = Modifier
                                .padding(horizontal = 24.dp),
                            fontSize = 24.sp
                        )
                    }
                }

            }

        }
    }
}