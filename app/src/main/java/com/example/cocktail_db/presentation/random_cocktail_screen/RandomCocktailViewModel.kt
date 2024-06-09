package com.example.cocktail_db.presentation.random_cocktail_screen

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktail_db.core.Resource
import com.example.cocktail_db.domain.use_case.cocktail_use_case.CocktailUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class RandomCocktailViewModel(
		private val cocktailUseCases: CocktailUseCases
): ViewModel(), KoinComponent {
		private val _state = mutableStateOf(RandomCocktailState())
		val state: State<RandomCocktailState> = _state


		private val _isRefreshing = MutableStateFlow(false)
		val isRefreshing: StateFlow<Boolean>
				get() = _isRefreshing.asStateFlow()

		init { getRandomCocktail() }

		private fun getRandomCocktail() {
				cocktailUseCases.getRandomCocktailUseCase().onEach { result ->
						when(result) {
								is Resource.Loading -> { _state.value = RandomCocktailState(isLoading = true) }
								is Resource.Success -> { _state.value = RandomCocktailState(cocktails = result.data ?: emptyList()) }
								is Resource.Error -> { _state.value = RandomCocktailState(error = result.message ?: "An unexpected error occurred")
								}
						}
				}.launchIn(viewModelScope)
		}

		private fun getCocktailById(cocktailId: Int) {
				cocktailUseCases.getCocktailByIdUseCase(cocktailId).onEach { result ->
						when(result) {
								is Resource.Loading -> { _state.value = RandomCocktailState(isLoading = true) }
								is Resource.Success -> { _state.value = RandomCocktailState(cocktails = result.data ?: emptyList()) }
								is Resource.Error -> { _state.value = RandomCocktailState(error = result.message ?: "An unexpected error occurred")
								}
						}
				}.launchIn(viewModelScope)
		}

		fun refresh() {
				getRandomCocktail()
		}
}