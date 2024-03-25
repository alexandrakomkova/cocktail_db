package com.example.cocktail_db.presentation.random_cocktail_screen

import com.example.cocktail_db.domain.model.Cocktail

data class RandomCocktailState(
		val isLoading: Boolean = false,
		val cocktails: List<Cocktail> = emptyList(),
		val error: String = ""
)