package com.example.cocktail_db.di

import com.example.cocktail_db.core.API_COCKTAILDB_URL
import com.example.cocktail_db.data.remote.CocktailDbApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
		factory { provideCocktailDbApi(retrofit = get()) }
		single { provideCocktailDbRetrofit(okHttpClient = get()) }
}

fun provideCocktailDbRetrofit(okHttpClient: OkHttpClient): Retrofit {
		return Retrofit.Builder()
				.baseUrl(API_COCKTAILDB_URL)
				// .client(okHttpClient)
				.addConverterFactory(MoshiConverterFactory.create())
				.build()
}

fun provideCocktailDbApi(retrofit: Retrofit): CocktailDbApi = retrofit.create(CocktailDbApi::class.java)