package com.example.cocktail_db.presentation.category

import com.example.cocktail_db.domain.model.Category

data class CategoryState(
		val isLoading: Boolean = false,
		val categories: List<Category> = emptyList(),
		val error: String = ""
)