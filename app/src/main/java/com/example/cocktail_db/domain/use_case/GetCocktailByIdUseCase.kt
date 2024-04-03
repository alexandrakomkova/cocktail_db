package com.example.cocktail_db.domain.use_case

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.cocktail_db.core.Resource
import com.example.cocktail_db.data.remote.dto.toCocktail
import com.example.cocktail_db.domain.model.Cocktail
import com.example.cocktail_db.domain.repository.CocktailDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import java.io.IOException

class GetCocktailByIdUseCase(
	private val repository: CocktailDbRepository
): KoinComponent {

		@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
		operator fun invoke(cocktailId: Int): Flow<Resource<List<Cocktail>>> = flow {

				try {
						emit(Resource.Loading())
						val cocktail = repository.getCocktailById(cocktailId).map { it.toCocktail() }
						emit(Resource.Success(cocktail))
				}
				catch (e: HttpException) {
						emit(Resource.Error(message = e.localizedMessage ?: "An unexpected HTTP error occurred"))
				}
				catch (e: IOException) {
						emit(Resource.Error(message = "Couldn't reach server. Check your internet connection"))
				}
		}
}