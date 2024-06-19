package com.example.cocktail_db.feature.cocktail_detail

import com.example.cocktail_db.domain.model.Cocktail

data class CocktailDetailState (
		val isLoading: Boolean = false,
		val cocktails: List<Cocktail> = emptyList(),
		val error: String = ""
)