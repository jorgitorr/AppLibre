package com.example.applibre.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.applibre.R
import com.example.applibre.data.model.SuperHero
import com.example.applibre.ui.model.HeroDeckViewModel
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun visualizar(){
    screen()
}
/*Contiene toda la pantalla*/
@ExperimentalMaterial3Api
@Composable
fun screen(){
    val heroDeckViewModel:HeroDeckViewModel = HeroDeckViewModel()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Blue)) {
                                append("Hero")
                            }
                            withStyle(style = SpanStyle(color = Color.Red)) {
                                append("Deck")
                            }
                        }
                    )
                }
            )
        },
        /*bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Button(onClick = { heroDeckViewModel.getSuperHeroe() }) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Bottom app bar",
                    )
                }
            }
        },*/
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.Bottom
        ) {
            //EnemyCards()
            SuperHeroList(superHeroes = heroDeckViewModel.superHeroDeck)
        }
    }
}

/**
 * imprime la carta
 */
@Composable
fun SuperHeroCard(character: SuperHero){
    var showText by remember { mutableStateOf(false) }

    val urlImagen = character.image.url
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(urlImagen)
                    .build(),
                contentDescription = "SuperHero",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SuperHeroList(superHeroes: StateFlow<List<SuperHero>>) {
    val superHeroList by superHeroes.collectAsState()

    LazyRow{
        items(superHeroList) { superHero ->
            SuperHeroCard(superHero)
        }
    }
}

@Composable
fun EnemyCards(){
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
fun Mostrar(character: SuperHero){
    Column{
        Text(text = character.name)
        Text(text = character.powerStats.power.toString())
        Text(text = character.powerStats.durability.toString())
        Text(text = character.powerStats.speed.toString())
    }
}



