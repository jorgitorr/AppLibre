package com.example.applibre.ui.view.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.example.applibre.ui.theme.Shrikhand
import com.example.applibre.ui.view.components.PlayAudio

@ExperimentalMaterial3Api
@Composable
fun Favoritos(heroDeckViewModel: HeroDeckViewModel, navController: NavController){
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
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
                PlayAudio(context = context)
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.Bottom
        ) {
            SuperHeroListSaved(navController, heroDeckViewModel)
        }
    }

    if (openDialog.value) {
        ExitGameDialog(openDialog = openDialog)
    }


}


/**
 * imprime la lista de superheroes de la bdd
 */
@Composable
fun SuperHeroListSaved(navController: NavController, heroDeckViewModel:HeroDeckViewModel){
    val superHeroListGuardada by heroDeckViewModel.fetchSuperHeroes().collectAsState()
    LazyRow{
        items(superHeroListGuardada){ superHero ->
            SuperHeroCard(character = superHero, navController = navController)
        }
    }
}



