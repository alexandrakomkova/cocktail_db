package com.example.cocktail_db.domain.repository

import com.example.cocktail_db.domain.model.Cocktail
import kotlinx.coroutines.flow.Flow

interface FavCocktailRepository {

		fun getFavourites(): Flow<List<Cocktail>>

		suspend fun getFavCocktailById(id: Int):Cocktail?

		suspend fun insertFavCocktail(cocktail: Cocktail)

		suspend fun deleteFavCocktail(cocktail: Cocktail)
}