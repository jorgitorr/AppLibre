package com.example.applibre

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.applibre.data.SuperHero
import com.example.applibre.ui.model.HeroDeckViewModel

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
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            LazyColumn{
                item { SuperHeroCard(superHero = heroDeckViewModel.superHero) }
            }

        }
    }
}

/**
 * imprime la carta
 */
@Composable
fun SuperHeroCard(superHero:SuperHero){
    val urlImagen = superHero.image.url
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(urlImagen)
            .build(),
        contentDescription = "SuperHero",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )
    
    Text(text = superHero.name)
    Text(text = superHero.powerStats.toString())
    
}



