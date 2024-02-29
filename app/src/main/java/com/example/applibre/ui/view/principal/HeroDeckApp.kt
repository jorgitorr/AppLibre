package com.example.applibre.ui.view.principal

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.applibre.R
import com.example.applibre.data.model.SuperHero
import com.example.applibre.data.model.db.SuperHeroState
import com.example.applibre.ui.model.HeroDeckViewModel
import com.example.applibre.ui.theme.Azure
import com.example.applibre.ui.theme.Blue
import com.example.applibre.ui.theme.Red
import com.example.applibre.ui.theme.Shrikhand
import com.example.applibre.ui.view.components.AlertFavorites


@ExperimentalMaterial3Api
@Composable
fun Screen(heroDeckViewModel: HeroDeckViewModel, navController: NavController){
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Azure),
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
                        //tengo que hacerlo
                        Box(modifier = Modifier.padding(start = 300.dp)){
                            IconButton(onClick = {  }) {
                                Icon(Icons.Default.Search, contentDescription = "Buscar",
                                    tint = Blue)
                            }
                        }
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
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Ir hacia atrás")
                }
                //PlayAudio(context = context)//Audio del juego
                AlertFavorites(navController = navController)

            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(color = Azure),
            verticalArrangement = Arrangement.Bottom
        ) {

            SuperHeroList(navController, heroDeckViewModel)

        }
    }

    if (openDialog.value) {
        ExitGameDialog(openDialog = openDialog, navController)
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
                Log.d("Imagen",urlImagen)
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(urlImagen)
                        .build(),
                    contentDescription = "SuperHero",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onDoubleTap = {

                                }
                            )
                        }
                        .clickable {
                            navController.navigate("HeroDetail/${character.id}")
                        }
                )

            }
        }
    }
}

/**
 * sobreescribible que le pasa el SuperHeroState
 * ya que solo es un string de la imagen
 */
@Composable
fun SuperHeroCard(character: SuperHeroState, navController: NavController){
    val urlImagen = character.image
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth()) {
                Log.d("Imagen",urlImagen)
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(urlImagen)
                        .build(),
                    contentDescription = "SuperHero",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onDoubleTap = {

                                }
                            )
                        }
                        .clickable {
                            navController.navigate("HeroDetailFav/${character.id}")
                        }
                )

            }
            Text(text = character.name,
                style = TextStyle(fontFamily = Shrikhand, fontSize = 20.sp))
        }
    }
}

@Composable
fun SuperHeroList(navController: NavController, heroDeckViewModel:HeroDeckViewModel) {
    val superHeroList by heroDeckViewModel.superHeroDeckDC.collectAsState()//coge las cartas con la lista de cartas DC
    val superHeroList2 by heroDeckViewModel.superHeroDeckMarvel.collectAsState()//coge las cartas con la lista de cartas Marvel
    LazyVerticalGrid(columns =  GridCells.Adaptive(minSize = 128.dp)){
        items(superHeroList) { superHero ->
            SuperHeroCard(superHero, navController)
        }
    }


}


/**
 * imprime la lista
 */
/*@Composable
fun SuperHeroList(navController: NavController, heroDeckViewModel:HeroDeckViewModel) {
    val superHeroList by heroDeckViewModel.superHeroDeckDC.collectAsState()//coge las cartas con la lista de cartas DC
    val superHeroList2 by heroDeckViewModel.superHeroDeckMarvel.collectAsState()//coge las cartas con la lista de cartas Marvel
    LazyVerticalGrid(columns =  GridCells.Adaptive(minSize = 128.dp)){
        items(superHeroList) { superHero ->
                SuperHeroCard(superHero, navController)
        }
        items(superHeroList2){ superHero ->
            SuperHeroCard(superHero, navController)
        }
    }
}*/

/**
 * muestra por pantalla las cartas bocabajo
 */
@Composable
fun BackSideCards(){
    LazyRow {
        items(3) { index ->
            Image(
                painter = painterResource(id = R.drawable.carta_boca_abajo),
                contentDescription = "Carta boca abajo $index",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}

@Composable
fun ExitGameDialog(openDialog: MutableState<Boolean>, navController: NavController) {
    AlertDialog(
        onDismissRequest = { openDialog.value = false },
        title = { Text(text = "¿Deseas salir del juego?",
            style = TextStyle(fontFamily = Shrikhand, fontSize = 20.sp))},
        confirmButton = {
            Button(onClick = { navController.navigateUp() } ) {
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








