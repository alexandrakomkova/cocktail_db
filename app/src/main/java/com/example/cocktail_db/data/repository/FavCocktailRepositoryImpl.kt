package com.example.cocktail_db.data.repository

import com.example.cocktail_db.data.data_source.CocktailDao
import com.example.cocktail_db.domain.model.Cocktail
import com.example.cocktail_db.domain.repository.FavCocktailRepository
import kotlinx.coroutines.flow.Flow

class FavCocktailRepositoryImpl(
		private val dao: CocktailDao
): FavCocktailRepository {
		override fun getFavourites(): Flow<List<Cocktail>> {
				TODO("Not yet implemented")
		}

		override suspend fun getFavCocktailById(id: Int): Cocktail? {
				TODO("Not yet implemented")
		}

		override suspend fun insertFavCocktail(cocktail: Cocktail) {
				TODO("Not yet implemented")
		}

		override suspend fun deleteFavCocktail(cocktail: Cocktail) {
				TODO("Not yet implemented")
		}
}