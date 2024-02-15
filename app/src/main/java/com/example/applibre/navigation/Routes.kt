package com.example.applibre.navigation

sealed class Routes(val route: String){
    object paginaInicio: Routes("Página de Inicio")
    object cartasPlayer: Routes("Cartas Player")
    object cartaDetalle: Routes("Detalle de la carta")
}