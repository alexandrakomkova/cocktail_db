package com.example.cocktail_db.di

import androidx.room.Room
import com.example.cocktail_db.data.data_source.FavCocktailDatabase
import com.example.cocktail_db.data.repository.CocktailDbRepositoryImpl
import com.example.cocktail_db.data.repository.FavCocktailRepositoryImpl
import com.example.cocktail_db.domain.repository.CocktailDbRepository
import com.example.cocktail_db.domain.repository.FavCocktailRepository
import com.example.cocktail_db.presentation.onboarding_screen.OnboardingStorage
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModuleCocktailDb = module {

		single {
				CocktailDbRepositoryImpl (api = get())
		} bind CocktailDbRepository::class

		single { OnboardingStorage() }
		single {
				Room
						.databaseBuilder(context = get(), FavCocktailDatabase::class.java, "favcocktail_db")
						.build()
		}

		single {
				FavCocktailRepositoryImpl(database = get())
		} bind FavCocktailRepository::class
}

val dataModuleFavCocktail = module {

		single {
				Room
						.databaseBuilder(context = get(), FavCocktailDatabase::class.java, "favcocktail_db")
						.build()
		}

		single {
				FavCocktailRepositoryImpl(database = get())
		} bind FavCocktailRepository::class
}