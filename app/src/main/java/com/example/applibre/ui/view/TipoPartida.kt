package com.example.applibre.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applibre.ui.theme.Shrikhand
import java.time.format.TextStyle

@Preview
@Composable
fun tipoPartida(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Title()
            Button(
                onClick = {  },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "LOCAL",
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = Shrikhand,
                        fontSize = 25.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = {  },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "ONLINE",
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = Shrikhand,
                        fontSize = 25.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
