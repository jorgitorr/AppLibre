package com.example.applibre.ui.view.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import com.example.applibre.ui.theme.Shrikhand
import com.example.applibre.ui.view.principal.ExitGameDialog
import com.example.applibre.ui.view.components.BotonAceptarRegistro
import com.example.applibre.ui.view.components.IntroducirContrasenia
import com.example.applibre.ui.view.components.IntroducirEmail
import com.example.applibre.ui.view.components.IntroducirUsuario
import com.example.applibre.ui.view.components.TengoCuenta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(loginViewModel: LoginViewModel, navController: NavController){
    val openDialog = remember { mutableStateOf(false) }
    androidx.compose.material3.Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.Blue)) {
                                    append("HERO ")
                                }
                                withStyle(style = SpanStyle(color = Color.Red)) {
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
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                IconButton(onClick = { openDialog.value = true }) {
                    Icon(
                        Icons.Filled.ArrowBack, contentDescription = "Ir hacia atrás",
                        modifier = Modifier.clickable { navController.navigateUp() })
                }
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IntroducirUsuario(loginViewModel = loginViewModel)
            Spacer(modifier = Modifier.padding(top = 20.dp))
            IntroducirEmail(loginViewModel = loginViewModel)
            Spacer(modifier = Modifier.padding(top = 20.dp))
            IntroducirContrasenia(loginViewModel = loginViewModel)
            Spacer(modifier = Modifier.padding(top = 20.dp))
            BotonAceptarRegistro(loginViewModel,navController)
            Spacer(modifier = Modifier.padding(top = 20.dp))
            TengoCuenta(navController = navController)
        }
    }

    if (openDialog.value) {
        ExitGameDialog(openDialog = openDialog, navController)
    }
}




