package com.example.applibre.ui.view.principal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.applibre.ui.model.HeroDeckViewModel
import com.example.applibre.ui.theme.Azure
import com.example.applibre.ui.theme.Blue
import com.example.applibre.ui.theme.Red
import com.example.applibre.ui.theme.Shrikhand
import com.example.applibre.ui.view.components.PlayAudio

@ExperimentalMaterial3Api
@Composable
fun Favoritos(heroDeckViewModel: HeroDeckViewModel, navController: NavController){
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    LaunchedEffect(Unit){
        heroDeckViewModel.fetchSuperHeroes()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp).background(Azure),
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
                contentColor = MaterialTheme.colorScheme.primary,
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
                .padding(innerPadding).background(Azure),
            verticalArrangement = Arrangement.Bottom
        ) {
            SuperHeroListSaved(navController, heroDeckViewModel)
        }
    }

    if (openDialog.value) {
        ExitGameDialog(openDialog = openDialog, navController)
    }


}


/**
 * imprime la lista de superheroes de la bdd
 */
@Composable
fun SuperHeroListSaved(navController: NavController, heroDeckViewModel:HeroDeckViewModel){
    val superHeroListGuardada by heroDeckViewModel.superHeroDeckPlayer.collectAsState()
    LazyRow{
        items(superHeroListGuardada){ superHero ->
            SuperHeroCard(superHero, navController)
        }
    }
}



