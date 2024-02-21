package com.example.applibre.ui.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.applibre.ui.model.LoginViewModel
import com.example.applibre.ui.theme.Shrikhand

@Composable
fun IntroducirContrasenia(loginViewModel: LoginViewModel){
    Text(text = "Contraseña", color = Color.Red,
        style = TextStyle(
            fontFamily = Shrikhand,
            fontSize = 25.sp
        )
    )
    OutlinedTextField(
        value = loginViewModel.password,
        onValueChange = { loginViewModel.changePassword(it) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )
}

@Composable
fun IntroducirEmail(loginViewModel: LoginViewModel){
    Text(text = "Email", color = Color.Blue,
        style = TextStyle(
            fontFamily = Shrikhand,
            fontSize = 25.sp
        )
    )
    OutlinedTextField(
        value = loginViewModel.email,
        onValueChange = { loginViewModel.changeEmail(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )
}


@Composable
fun TengoCuenta(navController: NavController){
    Text(text = "Tengo cuenta", modifier = Modifier
        .clickable { navController.navigate("Login")})
}


@Composable
fun BotonAceptarLogin(loginViewModel: LoginViewModel, navController: NavController){
    Text(text = "ACEPTAR",
        style = TextStyle(
            fontFamily = Shrikhand,
            fontSize = 25.sp
        ),
        color = Color.Red,
        modifier = Modifier
            .clickable { loginViewModel.login { navController.navigate("CartasJugador") }}
            .padding(50.dp))

}


@Composable
fun BotonAceptarRegistro(loginViewModel: LoginViewModel, navController: NavController){
    Text(text = "ACEPTAR",
        style = TextStyle(
            fontFamily = Shrikhand,
            fontSize = 25.sp
        ),
        color = Color.Red,
        modifier = Modifier
            .clickable { loginViewModel.createUser { navController.navigate("CartasJugador") }}
            .padding(50.dp))

}


@Composable
fun NoTengoCuenta(navController: NavController){
    Text(text = "No tengo cuenta", modifier = Modifier
        .clickable { navController.navigate("Registro") })
}



@Composable
fun IntroducirUsuario(loginViewModel: LoginViewModel){
    Text(text = "Usuario", color = Color.Red,
        style = TextStyle(
            fontFamily = Shrikhand,
            fontSize = 25.sp
        )
    )
    OutlinedTextField(
        value = loginViewModel.userName,
        onValueChange = { loginViewModel.changeUserName(it) }
    )
}