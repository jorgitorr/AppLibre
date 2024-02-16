package com.example.applibre.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.applibre.ui.theme.Shrikhand


@Composable
fun Pantalla(navController: NavController){

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()){

        Title()
        Text(text = "Usuario", color = Color.Blue,style = TextStyle(fontFamily = Shrikhand, fontSize = 25.sp))
        UsuarioField()
        Text(text = "Contraseña", color = Color.Red,style = TextStyle(fontFamily = Shrikhand, fontSize = 25.sp))
        ContraseniaField()
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = { navController.navigate("Screen") }) {
            Text(text = "ACEPTAR",style = TextStyle(fontFamily = Shrikhand, fontSize = 25.sp))
        }
    }
}

@Composable
fun Title(){
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("HERO")
            }
            withStyle(style = SpanStyle(color = Color.Red)) {
                append("DECK")
            }
        },
        style = TextStyle(fontFamily = Shrikhand, fontSize = 25.sp),
        modifier = Modifier.padding(15.dp)
    )
}


@Composable
fun UsuarioField() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
fun ContraseniaField(){
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.padding(10.dp)
    )
}