package com.example.cocktail_db.domain.model

data class Cocktail(
		val id: Int,
		val name: String,
		val glassType: String,
		val category: String,
		val image: String,
		val cocktailType: String?,
		val ingredient1: Any?,
		val ingredient2: Any?,
		val ingredient3: Any?,
		val ingredient4: Any?,
		val ingredient5: Any?,
		val ingredient6: Any?
)
