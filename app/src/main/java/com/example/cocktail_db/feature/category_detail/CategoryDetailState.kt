package com.example.cocktail_db.feature.category_detail

import com.example.cocktail_db.domain.model.ShortInfoCocktail

data class CategoryDetailState(
		val isLoading: Boolean = false,
		val shortInfoCocktails: List<ShortInfoCocktail> = emptyList(),
		val categoryName: String = "",
		val error: String = ""
)
