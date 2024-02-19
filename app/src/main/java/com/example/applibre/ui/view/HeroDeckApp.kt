package com.example.applibre.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.applibre.R
import com.example.applibre.data.model.SuperHero
import com.example.applibre.navigation.Routes
import com.example.applibre.ui.model.HeroDeckViewModel
import com.example.applibre.ui.theme.Shrikhand
import kotlinx.coroutines.flow.StateFlow


@ExperimentalMaterial3Api
@Composable
fun Screen(heroDeckViewModel: HeroDeckViewModel, navController: NavController){
    val openDialog = remember { mutableStateOf(false) }
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
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Ir hacia atrás",
                        modifier = Modifier.clickable { navController.navigateUp() })
                }
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.Bottom
        ) {
            SuperHeroList(superHeroes = heroDeckViewModel.superHeroDeck, navController = navController)
        }
    }

    if (openDialog.value) {
        ExitGameDialog(openDialog = openDialog)
    }
    

}

/**
 * imprime la carta
 */
@Composable
fun SuperHeroCard(character: SuperHero, navController: NavController){
    val urlImagen = character.image.url
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth()) {

               AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(urlImagen)
                            .build(),
                        contentDescription = "SuperHero",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()//aquí hay que pasarle la ruta de la foto o algo
                            .clickable { navController.navigate(Routes.cartaDetalle.route)}
               )

            }
            Text(text = character.name,
                style = TextStyle(fontFamily = Shrikhand, fontSize = 20.sp))
        }
    }
}

/**
 * imprime la lista
 */
@Composable
fun SuperHeroList(superHeroes: StateFlow<List<SuperHero>>, navController: NavController) {
    val superHeroList by superHeroes.collectAsState()

    LazyRow{
        items(superHeroList) { superHero ->
            SuperHeroCard(superHero,navController)
        }
    }
}

/**
 * muestra por pantalla las cartas bocabajo 
 */
@Composable
fun BackSideCards(){
    LazyRow {
        items(3) { index ->
            Image(
                painter = painterResource(id = R.drawable.carta_bocabajo),
                contentDescription = "Carta boca abajo $index",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}

@Composable
fun ExitGameDialog(openDialog: MutableState<Boolean>) {
    AlertDialog(
        onDismissRequest = { openDialog.value = false },
        title = { Text(text = "¿Deseas salir del juego?",
            style = TextStyle(fontFamily = Shrikhand, fontSize = 20.sp)) },
        confirmButton = {
            Button(onClick = { /* Handle exit logic here */ }) {
                Text(text = "ACEPTAR",
                    style = TextStyle(fontFamily = Shrikhand, fontSize = 15.sp))
            }
        },
        dismissButton = {
            Button(onClick = { openDialog.value = false }) {
                Text(text = "CANCELAR",
                    style = TextStyle(fontFamily = Shrikhand, fontSize = 15.sp))
            }
        }
    )
}








