package com.example.applibre.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme.colors
import com.example.applibre.R
import com.example.applibre.data.model.SuperHero
import com.example.applibre.ui.theme.Red


/**
 * muestra el detalle de la carta
 */
@Composable
fun HeroDetailView(superHero: SuperHero) {
    Column(
        modifier = Modifier
            .padding()
            .background(Color.White)
    ) {
        SuperHeroCard(superHero)

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 5.dp)
        ) {
            Skills(superHero)
        }

        val scroll = rememberScrollState(0)

        Text(
            text = superHero.name,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                .verticalScroll(scroll)
        )
    }
}


/**
 * iconos con el número de powerStats
 */
@Composable
fun Skills(superHero:SuperHero){
    Row{
        Box(){
            Image(
                painter = painterResource(id = R.drawable.strength),
                contentDescription = "Icono de fuerza",
                modifier = Modifier.fillMaxSize()
            )
            Text(text = superHero.powerStats.strength)
        }

        Box(){
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