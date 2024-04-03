package com.example.cocktail_db.data.remote

import com.example.cocktail_db.data.remote.dto.category.CategoryListDto
import com.example.cocktail_db.data.remote.dto.cocktails_by_category.CocktailsByCategoryDto
import com.example.cocktail_db.data.remote.dto.CocktailDto
import retrofit2.http.GET
import retrofit2.http.Path


interface CocktailDbApi {
		@GET("/api/json/v1/1/random.php")
		suspend fun getRandomCocktail(): CocktailDto

		@GET("/api/json/v1/1/list.php?c=list")
		suspend fun getCategories(): CategoryListDto

		@GET("/api/json/v1/1/filter.php?c={categoryName}")
		suspend fun getCocktailsByCategoryName(@Path("categoryName") categoryName: String): CocktailsByCategoryDto

		@GET("/api/json/v1/1/lookup.php?i={cocktailId}")
		suspend fun getCocktailById(@Path("cocktailId") cocktailId: Int): CocktailDto


}