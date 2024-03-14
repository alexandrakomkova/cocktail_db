package com.example.cocktail_db.di

import com.example.cocktail_db.domain.use_case.CocktailUseCases
import com.example.cocktail_db.domain.use_case.GetRandomCocktailUseCase
import org.koin.dsl.module

val domainModule = module {
		factory<CocktailUseCases> {
				CocktailUseCases(
						getRandomCocktailUseCase = get()
				)
		}

		factory<GetRandomCocktailUseCase> {
				GetRandomCocktailUseCase(repository = get())
		}
}