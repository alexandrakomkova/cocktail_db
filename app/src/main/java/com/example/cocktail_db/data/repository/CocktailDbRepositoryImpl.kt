package com.example.cocktail_db.data.repository

import com.example.cocktail_db.data.remote.CocktailDbApi
import com.example.cocktail_db.data.remote.dto.Drink
import com.example.cocktail_db.domain.repository.CocktailDbRepository
import org.koin.core.component.KoinComponent

class CocktailDbRepositoryImpl(
		private val api: CocktailDbApi
): CocktailDbRepository, KoinComponent {
		override suspend fun getCocktailDbRandomCocktail(): List<Drink> {
				return api.getRandomCocktail().drinks
		}
}