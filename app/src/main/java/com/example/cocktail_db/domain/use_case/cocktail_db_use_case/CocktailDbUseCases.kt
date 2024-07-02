package com.example.cocktail_db.domain.use_case.cocktail_db_use_case

data class CocktailDbUseCases(
		val getRandomCocktailUseCase: GetRandomCocktailUseCase,
		val getCategoriesUseCase: GetCategoriesUseCase,
		val getCocktailsByCategoryUseCase: GetCocktailsByCategoryUseCase,
		val getCocktailByIdUseCase: GetCocktailByIdUseCase,
		val getCocktailByNameUseCase: GetCocktailByNameUseCase
)
