package com.example.addappt.components.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.addappt.R

@Composable
fun TitleLogo(modifier: Modifier = Modifier){
    Image(painter = painterResource(
        id = R.drawable.logo_addappt),
        contentDescription = "Title Logo",
        modifier = Modifier
            .height(120.dp)
    )
}