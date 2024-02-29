package com.example.applibre.ui.view.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.applibre.ui.model.LoginViewModel
import com.example.applibre.ui.theme.Azure
import com.example.applibre.ui.theme.Blue
import com.example.applibre.ui.theme.Red
import com.example.applibre.ui.theme.Shrikhand
import com.example.applibre.ui.theme.White
import com.example.applibre.ui.view.principal.ExitGameDialog
import com.example.applibre.ui.view.components.Alert
import com.example.applibre.ui.view.components.BotonAceptarLogin
import com.example.applibre.ui.view.components.IntroducirContrasenia
import com.example.applibre.ui.view.components.IntroducirEmail
import com.example.applibre.ui.view.components.NoTengoCuenta

/**
 * Login del usuario
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(loginViewModel: LoginViewModel, navController: NavController){
    val openDialog = remember { mutableStateOf(false) }
    androidx.compose.material3.Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp).background(Azure),
                        contentAlignment = Alignment.Center
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
                            style = TextStyle(fontFamily = Shrikhand, fontSize = 25.sp)
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Azure,
                contentColor = Red,
            ) {
                IconButton(onClick = { openDialog.value = true }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Ir hacia atrás",
                        modifier = Modifier.clickable { navController.navigateUp() })
                }
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding).background(Azure),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            IntroducirEmail(loginViewModel = loginViewModel)
            Spacer(modifier = Modifier.padding(top = 20.dp))
            IntroducirContrasenia(loginViewModel = loginViewModel)
            BotonAceptarLogin(loginViewModel,navController)
            Spacer(modifier = Modifier.padding(top = 5.dp))
            NoTengoCuenta(navController = navController)
        }
    }

    if (loginViewModel.showAlert) {
        Alert(title = "Alerta",
            message = "Usuario y/o contrasena incorrectos",
            confirmText = "Aceptar",
            onConfirmClick = { loginViewModel.closeAlert() },
            onDismissClick = { } ) // DCS - ninguna acción en onDismissClick para que no oculte el diálogo
    }

    if (openDialog.value) {
        ExitGameDialog(openDialog = openDialog, navController)
    }
}







