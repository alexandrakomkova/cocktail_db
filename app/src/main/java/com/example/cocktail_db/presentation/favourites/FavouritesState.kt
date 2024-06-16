package com.example.cocktail_db.presentation.favourites

import com.example.cocktail_db.domain.model.FavCocktail

data class FavouritesState (
		val favourites: List<FavCocktail> = emptyList(),
		val isLoading: Boolean = true,
		val error: String = ""
)