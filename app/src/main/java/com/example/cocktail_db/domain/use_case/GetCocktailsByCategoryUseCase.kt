package com.example.cocktail_db.domain.use_case

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.cocktail_db.core.Resource
import com.example.cocktail_db.data.remote.dto.cocktails_by_category.toShortInfoCocktail
import com.example.cocktail_db.domain.model.ShortInfoCocktail
import com.example.cocktail_db.domain.repository.CocktailDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import java.io.IOException


class GetCocktailsByCategoryUseCase(
		private val repository: CocktailDbRepository
): KoinComponent {
		@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
		operator fun invoke(categoryName: String): Flow<Resource<List<ShortInfoCocktail>>> = flow {
				try {
						emit(Resource.Loading())
						val cocktailsByCategory = repository.getCocktailsByCategoryName(categoryName).map { it.toShortInfoCocktail() }
						emit(Resource.Success(cocktailsByCategory))

				} catch (e: HttpException) {
						emit(Resource.Error(message = e.localizedMessage ?: "An unexpected HTTP error occurred"))
				} catch (e: IOException) {
						emit(Resource.Error(message = "Couldn't reach server. Check your internet connection"))
				}

		}
}