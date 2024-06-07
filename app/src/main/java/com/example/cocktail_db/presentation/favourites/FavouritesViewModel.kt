package com.example.cocktail_db.presentation.favourites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

class FavouritesViewModel() : KoinComponent, ViewModel() {
		private val _state = mutableStateOf(FavouritesState())
		val state: State<FavouritesState> = _state
}