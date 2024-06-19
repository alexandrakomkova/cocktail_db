package com.example.cocktail_db.domain.model

data class ShortInfoCocktail(
		val id: String,
		val name: String,
		val image: String
)

fun ShortInfoCocktail.toCocktail(): Cocktail {
		return Cocktail(
				id = id.toInt(),
				name = name,
				image = image,
				glassType = "",
				category = "",
				cocktailType = "",
				measuresList = emptyList(),
				ingredientsList = emptyList()
		)
}
