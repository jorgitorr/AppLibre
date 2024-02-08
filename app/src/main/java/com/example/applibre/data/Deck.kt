package com.example.applibre.data

import android.content.Context
import com.example.applibre.ui.model.HeroDeckViewModel
import kotlin.random.Random

class Deck {

    companion object {
        // The list to store the deck of cards
        private val listaSuperHeroes: ArrayList<SuperHero> = ArrayList()

        /**
         * Creates a new deck of cards and populates the list.
         *
         * @param context The application context for retrieving resources.
         */
        fun newDeckOfHeroes(HeroDeckViewModel:HeroDeckViewModel) {
            listaSuperHeroes.clear()
            for (i in 1..5){
                listaSuperHeroes.add(HeroDeckViewModel.getSuperHeroe2())
            }
        }

        /**
         * Genera un número aleatorio en el rango de 1 a 731
         * que son todos los posibles id
         */
        fun obtenerNumeroAleatorio(): Int {
            return Random.nextInt(1, 732)
        }

        fun shuffle() {
            listaSuperHeroes.shuffle()
        }

        /**
         * Gets the last card from the deck.
         *
         * @return The last card in the deck.
         */
        fun getSuperHero(): SuperHero {
            val carta = listaSuperHeroes.last()
            listaSuperHeroes.removeLast()
            return carta
        }




        /**
         * Gets the total number of cards in the deck.
         *
         * @return The total number of cards in the deck.
         */
        fun getCardsTotal(): Int {
            return listaSuperHeroes.size
        }

    }
}