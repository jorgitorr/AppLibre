package com.example.applibre.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.applibre.ui.theme.Azure
import com.example.applibre.ui.theme.Blue
import com.example.applibre.ui.theme.Red
import com.example.applibre.ui.theme.Shrikhand
import com.example.applibre.ui.theme.White
import java.time.format.TextStyle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TipoPartida(navController: NavController) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Azure),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Blue)) {
                    append("HERO ")
                }
                withStyle(style = SpanStyle(color = Red)) {
                    append("DECK")
                }
            },
            style = androidx.compose.ui.text.TextStyle(fontFamily = Shrikhand, fontSize = 58.sp),
            modifier = Modifier.padding(bottom = 200.dp)
        )


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate("CartasJugador")},
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(White)
            ) {
                Text(
                    text = "CONTINUAR",
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = Shrikhand,
                        fontSize = 25.sp,
                        color = Blue
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = { navController.navigate("Login") },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(White)
            ) {
                Text(
                    text = "REGISTRARSE",
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = Shrikhand,
                        fontSize = 25.sp,
                        color = Red
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
