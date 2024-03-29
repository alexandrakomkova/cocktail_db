package com.example.cocktail_db.domain.model

data class Cocktail(
		val id: Int,
		val name: String,
		val glassType: String,
		val category: String,
		val image: String,
		val cocktailType: String?,
		val ingredientsList: List<Any?>,
		val measuresList: List<Any?>
)
