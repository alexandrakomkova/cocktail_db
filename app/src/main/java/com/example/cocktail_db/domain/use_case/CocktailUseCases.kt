package com.example.cocktail_db.domain.use_case

data class CocktailUseCases(
		val getRandomCocktailUseCase: GetRandomCocktailUseCase,
		val getCategoryUseCase: GetCategoryUseCase,
		val getCocktailsByCategoryUseCase: GetCocktailsByCategoryUseCase,
		val getCocktailByIdUseCase: GetCocktailByIdUseCase
)
