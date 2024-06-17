package com.example.cocktail_db.data.repository


import com.example.cocktail_db.data.remote.CocktailDbApi
import com.example.cocktail_db.data.remote.dto.Drink
import com.example.cocktail_db.data.remote.dto.category.CategoryDto
import com.example.cocktail_db.data.remote.dto.cocktails_by_category.ShortInfoCocktailDto
import com.example.cocktail_db.domain.repository.CocktailDbRepository
import javax.inject.Inject

class CocktailDbRepositoryImpl @Inject constructor(
		private val api: CocktailDbApi
): CocktailDbRepository {
		override suspend fun getCocktailDbRandomCocktail(): List<Drink> {
				return api.getRandomCocktail().drinks
		}

		override suspend fun getCategories(): List<CategoryDto> {
				return api.getCategories().categories
		}

		override suspend fun getCocktailsByCategoryName(categoryName: String): List<ShortInfoCocktailDto> {
				return api.getCocktailsByCategoryName(categoryName).cocktailsByCategory
		}

		override suspend fun getCocktailById(cocktailId: Int): List<Drink> {
				return api.getCocktailById(cocktailId).drinks
		}
}