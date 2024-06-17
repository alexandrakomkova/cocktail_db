package com.example.cocktail_db.feature.favourite_cocktail

import com.example.cocktail_db.domain.model.FavCocktail

data class FavouriteListState(
		val isLoading: Boolean = false,
		val favourites: List<FavCocktail> = emptyList(),
		val error: String = ""
)
