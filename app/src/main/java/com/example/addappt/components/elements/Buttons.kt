package com.example.addappt.components.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SubmitButton(
    textId : String,
    loading : Boolean,
    validInputs : Boolean,
    onClick : () -> Unit
){
    Button(
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        enabled = !loading && validInputs,
        shape = CircleShape
    ) {
        if(loading) {
            CircularProgressIndicator()
        } else {
            Text(
                text = textId,
                modifier = Modifier
                    .padding(6.dp)
            )
        }

    }
}