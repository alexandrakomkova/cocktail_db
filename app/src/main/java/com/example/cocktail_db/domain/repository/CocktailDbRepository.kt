package com.example.cocktail_db.domain.repository

import com.example.cocktail_db.data.remote.dto.Drink
import com.example.cocktail_db.data.remote.dto.category.CategoryDto
import com.example.cocktail_db.data.remote.dto.cocktails_by_category.ShortInfoCocktailDto

interface CocktailDbRepository {
		suspend fun getCocktailDbRandomCocktail(): List<Drink>
		suspend fun getCategories(): List<CategoryDto>
		suspend fun getCocktailsByCategoryName(categoryName: String): List<ShortInfoCocktailDto>
		suspend fun getCocktailById(cocktailId: String): List<Drink>
}