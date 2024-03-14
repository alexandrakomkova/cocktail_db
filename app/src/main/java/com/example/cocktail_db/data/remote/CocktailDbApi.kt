package com.example.cocktail_db.data.remote

import com.example.cocktail_db.data.remote.dto.RandomCocktail
import retrofit2.http.GET

interface CocktailDbApi {
		@GET("/api/json/v1/1/random.php")
		suspend fun getRandomCocktail(): RandomCocktail
}