package com.example.applibre.ui.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.applibre.ui.model.LoginViewModel
import com.example.applibre.ui.theme.Blue
import com.example.applibre.ui.theme.Red
import com.example.applibre.ui.theme.Shrikhand
import com.example.applibre.ui.theme.White

/**
 * Introducir contraseña para crear un nuevo usuario
 * @param loginViewModel le pasamos el viewmodel para que ingrese y guarde la contraseña
 */
@Composable
fun IntroducirContrasenia(loginViewModel: LoginViewModel){
    var hidden by remember { mutableStateOf(true) } //1
    Text(text = "Contraseña", color = Red,
        style = TextStyle(
            fontFamily = Shrikhand,
            fontSize = 25.sp
        )
    )
    OutlinedTextField(
        value = loginViewModel.password,
        onValueChange = { loginViewModel.changePassword(it) },
        visualTransformation =
        if (hidden) PasswordVisualTransformation() else VisualTransformation.None,//3
        trailingIcon = {
            IconButton(onClick = { hidden = !hidden }) {
                val vector = if (hidden) Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                val description = if (hidden) "Ocultar contraseña" else "Revelar contraseña" //6
                Icon(imageVector = vector, contentDescription = description)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.background(White).clip(RoundedCornerShape(8.dp))
    )
}

/**
 * Introducir email para crear un nuevo usuario
 * @param loginViewModel le pasamos el viewmodel para que guarde el email introducido
 */
@Composable
fun IntroducirEmail(loginViewModel: LoginViewModel){
    Text(text = "Email", color = Blue,
        style = TextStyle(
            fontFamily = Shrikhand,
            fontSize = 25.sp
        )
    )
    OutlinedTextField(
        value = loginViewModel.email,
        onValueChange = { loginViewModel.changeEmail(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier.background(White).clip(RoundedCornerShape(8.dp))
    )
}

/**
 * Componente que nos permite ingresar un nuevo usuario
 * @param loginViewModel le pasamos el viewmodel del login que guarda la informacion de este usuario nuevo
 * y la añade a la base de datos
 */
@Composable
fun IntroducirUsuario(loginViewModel: LoginViewModel){
    Text(text = "Usuario", color = Red,
        style = TextStyle(
            fontFamily = Shrikhand,
            fontSize = 25.sp
        )
    )
    OutlinedTextField(
        value = loginViewModel.userName,
        onValueChange = { loginViewModel.changeUserName(it) },
        modifier = Modifier.background(White).clip(RoundedCornerShape(8.dp))
    )
}

/**
 * Boton de aceptar del login
 * @param loginViewModel viewModel del login
 * @param navController nav que nos permite ir a donde queramos al darle a aceptar para tener un usuario
 */
@Composable
fun BotonAceptarLogin(loginViewModel: LoginViewModel, navController: NavController){
    Text(text = "ACEPTAR",
        style = TextStyle(
            fontFamily = Shrikhand,
            fontSize = 25.sp
        ),
        color = Red,
        modifier = Modifier
            .clickable { loginViewModel.login { navController.navigate("CartasJugador") }}
            .padding(50.dp))

}

/**
 * Boton de aceptar del registro
 * @param loginViewModel le pasamos el viewmodel del login
 * @param navController le pasamos el nav que nos lleva a donde queramos al darle a aceptar y
 * registrar un nuevo usuario en la base de datos
 */
@Composable
fun BotonAceptarRegistro(loginViewModel: LoginViewModel, navController: NavController){
    Text(text = "ACEPTAR",
        style = TextStyle(
            fontFamily = Shrikhand,
            fontSize = 25.sp
        ),
        color = Red,
        modifier = Modifier
            .clickable { loginViewModel.createUser { navController.navigate("CartasJugador") }}
            .padding(50.dp))

}

/**
 * En el caso  de tener una cuenta
 * @param navController le pasamos el nav que nos lleva a donde queramos una vez selecionemos
 */
@Composable
fun TengoCuenta(navController: NavController){
    Text(text = "Tengo cuenta", color = Blue, modifier = Modifier
        .clickable { navController.navigate("Login")},style = TextStyle(
        fontFamily = Shrikhand,
        fontSize = 25.sp
    ))
}


/**
 * Texto de no tener una cuenta que a su vez nos lleva a otra pantalla que sería la de registro
 * es tan solo un componente que nos redirige a esa página
 * @param navController nav que nos lleva a la página de registro
 */

@Composable
fun NoTengoCuenta(navController: NavController){
    Text(text = "No tengo cuenta", color = Blue, modifier = Modifier
        .clickable { navController.navigate("Registro") },
        style = TextStyle(
            fontFamily = Shrikhand,
            fontSize = 25.sp
        ))
}
