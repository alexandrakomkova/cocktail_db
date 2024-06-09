package com.example.cocktail_db.data.repository

import com.example.cocktail_db.data.data_source.CocktailDao
import com.example.cocktail_db.domain.model.Cocktail
import com.example.cocktail_db.domain.model.FavCocktail
import com.example.cocktail_db.domain.repository.FavCocktailRepository
import kotlinx.coroutines.flow.Flow

class FavCocktailRepositoryImpl(
		private val dao: CocktailDao
): FavCocktailRepository {
		override fun getFavourites(): Flow<List<FavCocktail>> {
				return dao.getFavourites()
		}

		override suspend fun getFavCocktailById(id: Int): FavCocktail? {
				return dao.getFavCocktailById(id)
		}

		override suspend fun insertFavCocktail(cocktail: FavCocktail) {
				dao.insertFavCocktail(cocktail)
		}

		override suspend fun deleteFavCocktail(cocktail: FavCocktail) {
				dao.deleteFavCocktail(cocktail)
		}
}