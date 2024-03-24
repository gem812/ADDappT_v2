package com.example.addappt.screens.splash

import android.annotation.SuppressLint
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.addappt.R
import com.example.addappt.data.DataOrException
import com.example.addappt.models.data.quotes.Quotes
import com.example.addappt.navigation.AddapptScreens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel = hiltViewModel()
) {

    val quoteData = produceState<DataOrException<Quotes, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = viewModel.getQuotes()
    }.value

    val scale = remember { Animatable(0f) }

    LaunchedEffect(
        key1 = true,
        block = {
            scale.animateTo(
                targetValue = 0.9f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = {
                        OvershootInterpolator(8f)
                            .getInterpolation(it)
                    }
                )
            )
            delay(2000)
            if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
                navController.navigate(AddapptScreens.IntroScreen.name)
            } else {
                navController.navigate(AddapptScreens.HomeScreen.name)
            }
        }
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.img_splash_background),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if(quoteData.loading == true) {
                    CircularProgressIndicator()
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.img_splash_title),
                        contentDescription = "Splash Title Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(300.dp)
                            .scale(scale.value)
                    )
                    quoteData.data?.quotes?.get(0)?.let {
                        Text(
                            text = it.q
                        )
                    }
                }
            }
        }

    }

}