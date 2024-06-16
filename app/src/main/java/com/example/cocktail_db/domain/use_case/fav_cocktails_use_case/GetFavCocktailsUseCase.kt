package com.example.cocktail_db.domain.use_case.fav_cocktails_use_case

import com.example.cocktail_db.core.Resource
import com.example.cocktail_db.domain.model.FavCocktail
import com.example.cocktail_db.domain.repository.FavCocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class GetFavCocktailsUseCase(
		private val repository: FavCocktailRepository
): KoinComponent {
		operator fun invoke(): Flow<Resource<List<FavCocktail>>> = flow {

				try {
						emit(Resource.Loading())
						val favCocktails = repository.getFavourites()
						emit(Resource.Success(favCocktails))
				} catch (e: Exception) {
						emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
				}
		}
}