package com.example.cocktail_db.feature.category_list

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
class CategoryListViewModel @Inject constructor(
		private val cocktailDbUseCases: CocktailDbUseCases
): ViewModel() {
		private val _state = mutableStateOf(CategoryListState())
		val state: State<CategoryListState> = _state
		init { getCategories() }
		private fun getCategories() {

				viewModelScope.launch() {
						cocktailDbUseCases.getCategoriesUseCase()
								.flowOn(Dispatchers.IO)
								.collect { result ->
										when (result) {
												is Resource.Error -> { _state.value = CategoryListState(isLoading = false, error = result.message ?: "An unexpected error occurred")}
												is Resource.Loading -> { _state.value = CategoryListState(isLoading = true) }
												is Resource.Success -> { _state.value = CategoryListState(isLoading = false, categories = result.data ?: emptyList()) }
								}
						}
				}

		}

		fun refresh() {
				getCategories()
		}

}