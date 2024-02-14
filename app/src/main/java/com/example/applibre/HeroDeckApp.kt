package com.example.applibre

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.applibre.data.SuperHero
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
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Hero-Deck")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
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
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            /*LazyColumn{
                item { SuperHeroCard(character = heroDeckViewModel.character) }
            }*/
            SuperHeroList(superHeroes = heroDeckViewModel.superHeroDeck)
            //a veces me va y a veces me da fallo
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
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(urlImagen)
            .build(),
        contentDescription = "SuperHero",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )

    Button(onClick = { showText = !showText }, modifier = Modifier.padding(start = 150.dp)) {
        Text(if (showText) "Ocultar" else "Ver más")
    }

    if (showText) {
        Mostrar(character)
    }
}

@Composable
fun SuperHeroList(superHeroes: StateFlow<List<SuperHero>>) {
    val superHeroList by superHeroes.collectAsState()

    LazyRow {
        items(superHeroList) { superHero ->
            SuperHeroCard(superHero)
        }
    }
}

@Composable
fun Mostrar(character: SuperHero){
    Text(text = character.name)
    Text(text = character.powerStats.toString())
}



