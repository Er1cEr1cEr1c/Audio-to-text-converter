package com.example.audiototextconverter.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.audiototextconverter.R

/*
@Composable
fun MainScreen(onCaptureAudio: () -> Unit, onCopy: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var generatedText by remember { mutableStateOf("") }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .weight(12f),
            value = generatedText,
            onValueChange = {
                generatedText = it
            },
            shape = RoundedCornerShape(20.dp),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .weight(2f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularImageCard(R.drawable.ic_mic, "")

            Spacer(modifier = Modifier.padding(40.dp))

            CircularImageCard(R.drawable.ic_copy, "")
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}
*/

@Composable
fun CircularImageCard(modifier: Modifier = Modifier, imageResource: Int, contentDescription: String) {
    Card(
        shape = CircleShape,
        modifier = modifier.size(80.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = contentDescription,
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
