package com.example.cocktail_db.api

import com.example.cocktail_db.core.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object TestCocktailDbApiImpl {
		fun provideApi(): TestCocktailDbApi = Retrofit.Builder()
				.baseUrl(Constants.API_COCKTAIL_DB_URL)
				.addConverterFactory(MoshiConverterFactory.create())
				.build()
				.create(TestCocktailDbApi::class.java)
}