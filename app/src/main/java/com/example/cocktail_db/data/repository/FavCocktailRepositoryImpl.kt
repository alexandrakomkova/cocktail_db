package com.example.cocktail_db.data.repository

import com.example.cocktail_db.data.data_source.FavCocktailDatabase
import com.example.cocktail_db.domain.model.FavCocktail
import com.example.cocktail_db.domain.repository.FavCocktailRepository
import org.koin.core.component.KoinComponent

class FavCocktailRepositoryImpl(
		// private val dao: CocktailDao
		private val database: FavCocktailDatabase
): FavCocktailRepository, KoinComponent {

		private val dao = database.favCocktailDao()
		override suspend fun getFavourites(): List<FavCocktail> { return dao.getFavourites() }

		override suspend fun getFavCocktailById(id: Int): FavCocktail { return dao.getFavCocktailById(id) }

		override suspend fun insertFavCocktail(cocktail: FavCocktail) { dao.insertFavCocktail(cocktail) }

		override suspend fun deleteFavCocktail(cocktail: FavCocktail) { dao.deleteFavCocktail(cocktail) }
}