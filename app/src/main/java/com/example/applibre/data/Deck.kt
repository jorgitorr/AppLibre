package com.example.applibre.data

import kotlin.random.Random

class Deck {

    companion object {
        // The list to store the deck of cards
        val listaCharacters: ArrayList<SuperHeroe> = ArrayList()

        /**
         * Genera un número aleatorio en el rango de 1 a 731
         * que son todos los posibles id
         */
        fun obtenerNumeroAleatorio(): Int {
            return Random.nextInt(1, 732)
        }

        fun shuffle() {
            listaCharacters.shuffle()
        }

        /**
         * Gets the last card from the deck.
         *
         * @return The last card in the deck.
         */
        fun getSuperHero(): SuperHeroe {
            val carta = listaCharacters.last()
            listaCharacters.removeLast()
            return carta
        }




        /**
         * Gets the total number of cards in the deck.
         *
         * @return The total number of cards in the deck.
         */
        fun getCardsTotal(): Int {
            return listaCharacters.size
        }

    }
}