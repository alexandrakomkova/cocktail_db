package com.example.cocktail_db.presentation.short_info_cocktail

import com.example.cocktail_db.domain.model.ShortInfoCocktail

data class ShortInfoCocktailState(
		val isLoading: Boolean = false,
		val shortInfoCocktails: List<ShortInfoCocktail> = emptyList(),
		val categoryName: String = "",
		val error: String = ""
)
