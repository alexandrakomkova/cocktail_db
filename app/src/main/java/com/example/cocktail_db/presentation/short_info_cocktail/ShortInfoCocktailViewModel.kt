package com.example.cocktail_db.presentation.short_info_cocktail

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktail_db.core.Constants
import com.example.cocktail_db.core.Resource
import com.example.cocktail_db.domain.use_case.cocktail_use_case.CocktailUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class ShortInfoCocktailViewModel(
		private val cocktailUseCases: CocktailUseCases,
		savedStateHandle: SavedStateHandle
): ViewModel(), KoinComponent {
		private val _state = mutableStateOf(ShortInfoCocktailState())
		val state: State<ShortInfoCocktailState> = _state

		init {
				savedStateHandle.get<String>(Constants.COCKTAIL_CATEGORY_NAME_PARAM)?.let { categoryName ->
						getCocktailsByCategory(categoryName)
				}
		}

		private fun getCocktailsByCategory(categoryName: String) {
				Log.d("getCocktailsByCategory", categoryName)
				cocktailUseCases.getCocktailsByCategoryUseCase(categoryName).onEach { result ->
						when(result) {
								is Resource.Loading -> { _state.value = ShortInfoCocktailState(isLoading = true) }
								is Resource.Success -> { _state.value = ShortInfoCocktailState(categoryName = categoryName, shortInfoCocktails = result.data ?: emptyList()) }
								is Resource.Error -> { _state.value = ShortInfoCocktailState(error = result.message ?: "An unexpected error occurred")
								}
						}
				}.launchIn(viewModelScope)
		}
}