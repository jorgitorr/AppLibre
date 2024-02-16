package com.example.applibre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.applibre.navigation.NavManager
import com.example.applibre.ui.model.HeroDeckViewModel
import com.example.applibre.ui.theme.AppLibreTheme
import com.example.applibre.ui.view.Screen

class MainActivity : ComponentActivity() {
    private val heroDeckViewModel: HeroDeckViewModel = HeroDeckViewModel()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppLibreTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    //NavManager(viewModel = heroDeckViewModel)
                    Screen(heroDeckViewModel = heroDeckViewModel)
                }
            }
        }
    }
}

