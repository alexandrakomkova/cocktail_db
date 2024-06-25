package com.example.cocktail_db.domain.use_case.cocktail_db_use_case

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.cocktail_db.core.Resource
import com.example.cocktail_db.data.remote.dto.category.toCategory
import com.example.cocktail_db.domain.model.Category
import com.example.cocktail_db.domain.repository.CocktailDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
		private val repository: CocktailDbRepository
) {
		@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
		operator fun invoke(): Flow<Resource<List<Category>>> = flow {
				try {
						emit(Resource.Loading())
						val cocktailsCategory = repository.getCategories().map { it.toCategory() }
						emit(Resource.Success(cocktailsCategory))
				} catch (e: HttpException) {
						emit(Resource.Error(message = e.localizedMessage ?: "An unexpected HTTP error occurred"))
				} catch (e: IOException) {
						emit(Resource.Error(message = "Couldn't reach server. Check your internet connection"))
				}
		}
}


