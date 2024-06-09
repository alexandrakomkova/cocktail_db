package com.example.cocktail_db.presentation.category

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktail_db.core.Resource
import com.example.cocktail_db.domain.use_case.cocktail_use_case.CocktailUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class CategoryViewModel(
		private val cocktailUseCases: CocktailUseCases
): ViewModel(), KoinComponent {
		private val _state = mutableStateOf(CategoryState())
		val state: State<CategoryState> = _state

		init { getCategories() }

		private fun getCategories() {
				cocktailUseCases.getCategoriesUseCase().onEach { result ->
						when(result) {
								is Resource.Error -> { _state.value = CategoryState(error = result.message ?: "An unexpected error occurred") }
								is Resource.Loading -> { _state.value = CategoryState(isLoading = true) }
								is Resource.Success -> { _state.value = CategoryState(categories = result.data ?: emptyList()) }
						}
				}.launchIn(viewModelScope)
		}

		fun refresh() {
				getCategories()
		}

}