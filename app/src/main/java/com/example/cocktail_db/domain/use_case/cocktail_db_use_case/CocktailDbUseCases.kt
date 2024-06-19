package com.example.cocktail_db.domain.use_case.cocktail_db_use_case

data class CocktailDbUseCases(
		val getRandomCocktailUseCase: GetRandomCocktailUseCase, // ok
		val getCategoriesUseCase: GetCategoriesUseCase, // ok
		val getCocktailsByCategoryUseCase: GetCocktailsByCategoryUseCase, // ok
		val getCocktailByIdUseCase: GetCocktailByIdUseCase
)
