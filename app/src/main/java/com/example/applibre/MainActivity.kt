package com.example.applibre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.applibre.navigation.Routes
import com.example.applibre.ui.model.HeroDeckViewModel
import com.example.applibre.ui.theme.AppLibreTheme
import com.example.applibre.ui.view.HeroDetailView
import com.example.applibre.ui.view.Screen
import com.example.applibre.ui.view.tipoPartida

class MainActivity : ComponentActivity() {
    private val heroDeckViewModel: HeroDeckViewModel = HeroDeckViewModel()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppLibreTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Routes.tipoPartida.route) {
                        composable(Routes.tipoPartida.route){
                            tipoPartida(navController = navController)
                        }
                        composable(Routes.cartasPlayer.route){
                            Screen(heroDeckViewModel = heroDeckViewModel,
                                navController = navController)
                        }
                        composable(Routes.login.route){

                        }
                    }

                    //Screen(heroDeckViewModel = heroDeckViewModel)
                }
            }
        }
    }
}

