package com.example.cocktail_db.feature.category_list

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktail_db.core.Resource
import com.example.cocktail_db.domain.use_case.cocktail_use_case.CocktailDbUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class CategoryListViewModel(
		private val cocktailDbUseCases: CocktailDbUseCases
): ViewModel(), KoinComponent {
		private val _state = mutableStateOf(CategoryListState())
		val state: State<CategoryListState> = _state

		init { getCategories() }

		private fun getCategories() {
				cocktailDbUseCases.getCategoriesUseCase().onEach { result ->
						when(result) {
								is Resource.Error -> { _state.value = CategoryListState(error = result.message ?: "An unexpected error occurred") }
								is Resource.Loading -> { _state.value = CategoryListState(isLoading = true) }
								is Resource.Success -> { _state.value = CategoryListState(categories = result.data ?: emptyList()) }
						}
				}.launchIn(viewModelScope)
		}

		fun refresh() {
				getCategories()
		}

}