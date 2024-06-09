package com.example.cocktail_db.domain.repository

import com.example.cocktail_db.domain.model.FavCocktail
import kotlinx.coroutines.flow.Flow

interface FavCocktailRepository {

		fun getFavourites(): Flow<List<FavCocktail>>

		suspend fun getFavCocktailById(id: Int):FavCocktail?

		suspend fun insertFavCocktail(cocktail: FavCocktail)

		suspend fun deleteFavCocktail(cocktail: FavCocktail)
}