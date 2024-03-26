package com.example.cocktail_db.di

import com.example.cocktail_db.data.repository.CocktailDbRepositoryImpl
import com.example.cocktail_db.domain.repository.CocktailDbRepository
import com.example.cocktail_db.presentation.onboarding_screen.OnboardingStorage
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
		single {
				CocktailDbRepositoryImpl (api = get())
		} bind CocktailDbRepository::class

		single { OnboardingStorage() }
}