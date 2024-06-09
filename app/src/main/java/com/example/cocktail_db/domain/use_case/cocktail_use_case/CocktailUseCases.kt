package com.example.cocktail_db.domain.use_case.cocktail_use_case

data class CocktailUseCases(
		val getRandomCocktailUseCase: GetRandomCocktailUseCase, // ok
		val getCategoriesUseCase: GetCategoriesUseCase, // ok
		val getCocktailsByCategoryUseCase: GetCocktailsByCategoryUseCase, // ok
		val getCocktailByIdUseCase: GetCocktailByIdUseCase
)
