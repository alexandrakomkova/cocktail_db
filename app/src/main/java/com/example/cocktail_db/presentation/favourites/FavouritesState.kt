package com.example.cocktail_db.presentation.favourites

import com.example.cocktail_db.domain.model.ShortInfoCocktail

data class FavouritesState (
		val favourites: List<ShortInfoCocktail> = emptyList(),
		val isLoading: Boolean = true,
)