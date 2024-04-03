package com.example.cocktail_db.di

import com.example.cocktail_db.domain.use_case.CocktailUseCases
import com.example.cocktail_db.domain.use_case.GetCategoryUseCase
import com.example.cocktail_db.domain.use_case.GetCocktailByIdUseCase
import com.example.cocktail_db.domain.use_case.GetCocktailsByCategoryUseCase
import com.example.cocktail_db.domain.use_case.GetRandomCocktailUseCase
import org.koin.dsl.module

val domainModule = module {
		factory<CocktailUseCases> {
				CocktailUseCases(
						getRandomCocktailUseCase = get(),
						getCategoryUseCase = get(),
						getCocktailsByCategoryUseCase = get(),
						getCocktailByIdUseCase = get()
				)
		}

		factory<GetRandomCocktailUseCase> {
				GetRandomCocktailUseCase(repository = get())
		}

		factory<GetCategoryUseCase> {
				GetCategoryUseCase(repository = get())
		}

		factory<GetCocktailsByCategoryUseCase> {
				GetCocktailsByCategoryUseCase(repository = get())
		}

		factory<GetCocktailByIdUseCase> {
				GetCocktailByIdUseCase(repository = get())
		}
}