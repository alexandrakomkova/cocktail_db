package com.example.cocktail_db.domain.use_case.fav_cocktails_use_case

import com.example.cocktail_db.domain.model.FavCocktail
import com.example.cocktail_db.domain.repository.FavCocktailRepository
import org.koin.core.component.KoinComponent

class AddFavCocktailUseCase (
		private val repository: FavCocktailRepository
): KoinComponent  {
		suspend operator fun invoke(favCocktail: FavCocktail) {
				repository.insertFavCocktail(favCocktail)
		}
}