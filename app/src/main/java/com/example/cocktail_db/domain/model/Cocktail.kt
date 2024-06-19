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

fun Cocktail.toFavCocktail(): FavCocktail {
		return FavCocktail(
				id = id,
				name = name,
				glassType = glassType,
				image = image,
				category = category,
				cocktailType = cocktailType
		)
}
