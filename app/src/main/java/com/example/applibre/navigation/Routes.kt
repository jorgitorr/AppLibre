package com.example.applibre.navigation

sealed class Routes(val route: String){
    object tipoPartida:Routes("Eleccion tipo de partida")
    object login: Routes("Página de Inicio")
    object registro: Routes("Página de registro")
    object cartasPlayer: Routes("Cartas Player")
    object cartaDetalle: Routes("Detalle de la carta")
}