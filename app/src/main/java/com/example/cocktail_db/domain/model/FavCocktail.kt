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