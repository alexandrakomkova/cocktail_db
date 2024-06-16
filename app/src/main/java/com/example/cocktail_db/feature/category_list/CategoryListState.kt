package com.example.cocktail_db.feature.category_list

import com.example.cocktail_db.domain.model.Category

data class CategoryListState(
		val isLoading: Boolean = false,
		val categories: List<Category> = emptyList(),
		val error: String = ""
)
