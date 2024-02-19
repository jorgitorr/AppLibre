package com.example.applibre.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applibre.ui.model.HeroDeckViewModel
import com.example.applibre.ui.model.LoginViewModel
import com.example.applibre.ui.view.cards.HeroDetailView
import com.example.applibre.ui.view.cards.Screen
import com.example.applibre.ui.view.login.Login
import com.example.applibre.ui.view.login.Register
import com.example.applibre.ui.view.tipoPartida


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavManager(heroDeckViewModel: HeroDeckViewModel, loginViewModel: LoginViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.tipoPartida.route) {
        composable(Routes.tipoPartida.route){
            tipoPartida(navController = navController)
        }
        composable(Routes.cartasPlayer.route){
            Screen(heroDeckViewModel = heroDeckViewModel, navController)
        }
        composable(Routes.login.route){
            Login(loginViewModel = loginViewModel, navController)
        }
        composable(Routes.cartaDetalle.route){
            HeroDetailView(heroDeckViewModel, navController)
        }
        composable(Routes.registro.route){
            Register(loginViewModel = loginViewModel, navController)
        }

    }
}

