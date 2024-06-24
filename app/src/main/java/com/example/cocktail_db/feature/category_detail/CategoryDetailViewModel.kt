package com.example.cocktail_db.feature.category_detail

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktail_db.core.Resource
import com.example.cocktail_db.domain.use_case.cocktail_db_use_case.CocktailDbUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
		savedStateHandle: SavedStateHandle,
		private val cocktailDbUseCases: CocktailDbUseCases
): ViewModel() {
		private val _state = mutableStateOf(CategoryDetailState())
		val state: State<CategoryDetailState> = _state

		val categoryName: String = savedStateHandle.get<String>(CATEGORY_NAME_SAVED_STATE_KEY)!!

		init {
				getCocktailsByCategory()
		}

		private fun getCocktailsByCategory() {
				viewModelScope.launch() {
						cocktailDbUseCases.getCocktailsByCategoryUseCase(categoryName)
								.flowOn(Dispatchers.IO)
								.collect { result ->
										when (result) {
												is Resource.Error -> {
														_state.value = CategoryDetailState(error = result.message ?: "An unexpected error occurred")
												}
												is Resource.Loading -> { _state.value = CategoryDetailState(isLoading = true) }
												is Resource.Success -> { _state.value = CategoryDetailState(
														isLoading = false,
														categoryName = categoryName,
														shortInfoCocktails = result.data ?: emptyList()
												) }
										}
								}
				}
		}

		fun refresh() {
				getCocktailsByCategory()
		}

		companion object {
				private const val CATEGORY_NAME_SAVED_STATE_KEY = "categoryName"
		}

}