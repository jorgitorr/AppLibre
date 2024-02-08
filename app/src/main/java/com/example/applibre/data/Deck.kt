package com.example.applibre.data

import android.content.Context
import com.example.applibre.ui.model.HeroDeckViewModel

class Deck {

    companion object {
        // The list to store the deck of cards
        private val listaSuperHeroes: ArrayList<SuperHero> = ArrayList()

        /**
         * Creates a new deck of cards and populates the list.
         *
         * @param context The application context for retrieving resources.
         */
        fun newDeckOfCards(HeroDeckViewModel:HeroDeckViewModel) {
            listaSuperHeroes.clear()

            listaSuperHeroes.add(HeroDeckViewModel.superHero)


            }
        }

        /**
         * Shuffles the deck of cards.
         */
        fun shuffle() {
            listaSuperHeroes.shuffle()
        }

        /**
         * Gets the last card from the deck.
         *
         * @return The last card in the deck.
         */
        fun getCard(): SuperHero {
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