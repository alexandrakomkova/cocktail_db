package com.example.cocktail_db.domain.use_case.fav_cocktails_use_case

data class FavCocktailUseCases(
		val getFavCocktailsUseCase: GetFavCocktailsUseCase,
		val getFavCocktailByIdUseCase: GetFavCocktailByIdUseCase,
		val addFavCocktailUseCase: AddFavCocktailUseCase,
		val deleteFavCocktailUseCase: DeleteFavCocktailUseCase
)