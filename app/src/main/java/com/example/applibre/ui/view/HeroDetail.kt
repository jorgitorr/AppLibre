package com.example.applibre.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.MaterialTheme.colors
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.applibre.R
import com.example.applibre.data.model.SuperHero
import com.example.applibre.ui.model.HeroDeckViewModel
import com.example.applibre.ui.theme.Red
import com.example.applibre.ui.theme.Shrikhand


/**
 * muestra el detalle de la carta
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroDetailView(heroDeckViewModel: HeroDeckViewModel, navController: NavController) {
    val superHero = heroDeckViewModel.character//esto está mal
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
            /*AsyncImage(model = ImageRequest.Builder(context = LocalContext.current)
                .data(superHero.image.url)
                .build(), contentDescription = superHero.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize())*/
            Skills(superHero)
        }
    }

    if (openDialog.value) {
        ExitGameDialog(openDialog = openDialog)
    }
}


/**
 * iconos con el número de powerStats
 */
@Composable
fun Skills(superHero:SuperHero){
    Row{
        Box(modifier = Modifier.weight(1f)){
            Image(
                painter = painterResource(id = R.drawable.strength),
                contentDescription = "Icono de fuerza",
                modifier = Modifier.fillMaxSize()
            )
            Text(text = superHero.powerStats.strength)
        }

        Box(modifier = Modifier.weight(1f)){
            Image(painter = painterResource(id = R.drawable.durability),
                contentDescription = "Icono de defensa",
                modifier = Modifier.fillMaxSize())
            Text(text = superHero.powerStats.durability)
        }
        /*speed -> Box(){
            Image(
                painter = painterResource(id = R.drawable.speed),
                contentDescription = "Icono de velocidad",
                modifier = Modifier.fillMaxSize()
            )
            Text(text = superHero.powerStats.speed)
        }*/

        /*Button(onClick = { /*TODO*/ }) {
            Text(text = "USAR")
        }*/
    }
}