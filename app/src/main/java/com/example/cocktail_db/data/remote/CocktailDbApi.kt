package com.example.cocktail_db.data.remote

import com.example.cocktail_db.data.remote.dto.CocktailDto
import com.example.cocktail_db.data.remote.dto.category.CategoryListDto
import com.example.cocktail_db.data.remote.dto.cocktails_by_category.CocktailsByCategoryDto
import retrofit2.http.GET
import retrofit2.http.Query


interface CocktailDbApi {
		@GET("/api/json/v1/1/random.php")
		suspend fun getRandomCocktail(): CocktailDto

		@GET("/api/json/v1/1/list.php?c=list")
		suspend fun getCategories(): CategoryListDto

		@GET("/api/json/v1/1/filter.php?")
		suspend fun getCocktailsByCategoryName(@Query("c") categoryName: String): CocktailsByCategoryDto

		@GET("/api/json/v1/1/lookup.php?")
		suspend fun getCocktailById(@Query("i") cocktailId: String): CocktailDto


}