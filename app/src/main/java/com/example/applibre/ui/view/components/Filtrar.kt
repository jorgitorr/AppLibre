package com.example.applibre.ui.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.applibre.ui.model.HeroDeckViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(heroDeckViewModel: HeroDeckViewModel, navController: NavController){
    val active by heroDeckViewModel.active.collectAsState()
    val query by heroDeckViewModel.query.collectAsState()

    val superHero by heroDeckViewModel.superHeroDeckDC.collectAsState()


    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        query = query,
        onQueryChange = { heroDeckViewModel.setQuery(it) }, // DCS - Actualiza el texto de búsqueda en el ViewModel.
        onSearch = { heroDeckViewModel.setActive(false) }, // DCS - Desactiva la búsqueda al presionar el botón de búsqueda.
        active = active,
        onActiveChange = { heroDeckViewModel.setActive(it) }, // DCS - Actualiza el estado de activación de la búsqueda.
        placeholder = { Text(text = "Search") }, // DCS - Muestra un texto placeholder en la barra de búsqueda.
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "")
        },
        trailingIcon = {
            // DCS - Icono para cerrar la vista de búsqueda o limpiar el texto de búsqueda.
            Icon(imageVector = Icons.Default.Close, contentDescription = "",
                modifier = Modifier.clickable { navController.popBackStack() }
            )
        }
    ) {
        if(query.isNotEmpty()){
            val filterName = superHero.filter { it.name.contains(query,ignoreCase = true) }

            filterName.forEach{
                Text(text = it.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 10.dp, start = 10.dp)
                        .clickable { navController.navigate("HeroDetail/${it.id}") }
                )
            }
        }
    }

}

