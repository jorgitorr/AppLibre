package com.example.applibre.ui.view.principal

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.applibre.data.model.SuperHero
import com.example.applibre.ui.model.HeroDeckViewModel
import com.example.applibre.ui.theme.Blue
import com.example.applibre.ui.theme.Red
import com.example.applibre.ui.theme.Shrikhand

/**
 * muestra el detalle de la carta
 * la imagen en grande
 * y el poder y la defensa de la carta
 * @param HeroDechViewModel para poder coger la carta
 * @param navController para poder redirigirme a la página de atrás al darle otra vez a la carta
 * @param idHero el id del superHero que estoy clickando
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroDetailViewFav(heroDeckViewModel: HeroDeckViewModel, navController: NavController, idHero: String) {

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
                containerColor = Color.White,
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
                .padding(innerPadding),
            verticalArrangement = Arrangement.Bottom
        ) {
            val superHero = heroDeckViewModel.findById(idHero)//este método devuelve el superHeroe con ese id

            HeroDetailId(
                heroDeckViewModel = heroDeckViewModel,
                navController = navController,
                superHero = superHero,
                context = context,
                idHero = idHero
            )


        }
    }

    if (openDialog.value) {
        ExitGameDialog(openDialog = openDialog, navController)
    }
}


@Composable
fun HeroDetailId(heroDeckViewModel: HeroDeckViewModel, navController: NavController,
                 superHero: SuperHero, context: Context, idHero: String){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .size(450.dp)
                        .clickable { navController.navigateUp() }
                ) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(superHero.image.url)
                            .build(),
                        contentDescription = "SuperHero",
                        contentScale = ContentScale.Crop,
                        loading = { CircularProgressIndicator() },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            item {
                Text(
                    text = superHero.name + "\n("+superHero.biography.fullName+")",
                    style = TextStyle(fontFamily = Shrikhand, fontSize = 25.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Blue
                )
            }

            item {
                Text(
                    text = superHero.biography.publisher,
                    style = TextStyle(fontFamily = Shrikhand, fontSize = 25.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Blue
                )
            }

            item {
                Skills(superHero = superHero)
            }

            item {
                Spacer(modifier = Modifier.padding(15.dp))
            }


            item {
                Text(
                    text = "ELIMINAR",
                    color = Red,
                    style = TextStyle(fontFamily = Shrikhand, fontSize = 25.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            heroDeckViewModel.deleteSuperHero(idHero) {
                                Toast
                                    .makeText(context, "SuperHeroe eliminado", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                )
            }
        }
}

