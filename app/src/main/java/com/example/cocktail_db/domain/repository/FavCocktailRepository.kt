package com.example.cocktail_db.domain.repository

import com.example.cocktail_db.domain.model.FavCocktail

interface FavCocktailRepository {

		suspend fun getFavourites(): List<FavCocktail>
		suspend fun getFavCocktailById(id: Int):FavCocktail
		suspend fun insertFavCocktail(cocktail: FavCocktail)
		suspend fun deleteFavCocktail(cocktail: FavCocktail)
}