package com.example.addappt.components.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.addappt.R

@Composable
fun ClickableRowSettings(
    icon : Int,
    primaryText : String,
    subText : String = "",
    destinationRoute : String,
    onClick : (String) -> Unit
){
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .fillMaxWidth()
            .clickable {
                onClick(destinationRoute)
            },
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Settings Row",
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(48.dp))
        Column(
            modifier = Modifier.width(240.dp)
        ){
            Text(text = primaryText)
            if(subText.isNotBlank()) {
                Text(text = subText)
            }
        }
        Icon(painter = painterResource(id = R.drawable.ic_arrow_forward), contentDescription = "Forward arrow")

    }
}

@Composable
@Preview
fun PreviewClickableRowSettingsWithSubText(){
    Surface(
        modifier = Modifier.fillMaxWidth()
    ){
        ClickableRowSettings(
            icon = R.drawable.ic_accounts,
            primaryText = "Accounts",
            subText = "Passwords etc.",
            destinationRoute = "",
            onClick = {}
        )
    }

}

@Composable
@Preview
fun PreviewClickableRowSettingsNoSubText(){
    Surface(
        modifier = Modifier.fillMaxWidth()
    ){
        ClickableRowSettings(
            icon = R.drawable.ic_accounts,
            primaryText = "Accounts",
            destinationRoute = "",
            onClick = {}
        )
    }

}