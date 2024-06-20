package com.example.cocktail_db.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavCocktail(
		@PrimaryKey
		val id: Int,
		val name: String,
		val glassType: String,
		val category: String,
		val image: String,
		val cocktailType: String?,
		//val ingredientsList: List<String?>,
		//val measuresList: List<String?>
)

fun FavCocktail.toCocktail(): Cocktail {
		return Cocktail(
				id = id,
				name = name,
				glassType = glassType,
				image = image,
				category = category,
				cocktailType = cocktailType,
				ingredientsList = emptyList(),
				measuresList = emptyList()
		)
}