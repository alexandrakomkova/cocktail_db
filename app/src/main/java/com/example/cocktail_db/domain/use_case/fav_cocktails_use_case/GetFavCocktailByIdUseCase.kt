package com.example.cocktail_db.domain.use_case.fav_cocktails_use_case

import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.cocktail_db.core.Resource
import com.example.cocktail_db.domain.model.FavCocktail
import com.example.cocktail_db.domain.repository.FavCocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class GetFavCocktailByIdUseCase(
		private val repository: FavCocktailRepository
): KoinComponent {

		@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
		operator fun invoke(cocktailId: Int): Flow<Resource<FavCocktail>> = flow {

				try {
						emit(Resource.Loading())
						val favCocktail = repository.getFavCocktailById(cocktailId)
						emit(Resource.Success(favCocktail))
				} catch (e: Exception) {
						emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
				}
		}
}