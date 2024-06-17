package com.example.cocktail_db.feature.favourite_cocktail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktail_db.core.Resource
import com.example.cocktail_db.domain.use_case.fav_cocktails_use_case.FavCocktailUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class FavouriteListViewModel(
		private val favCocktailUseCases: FavCocktailUseCases
): ViewModel(), KoinComponent {

		private val _state = mutableStateOf(FavouriteListState())
		val state: State<FavouriteListState> = _state

		init { getFavourites() }

		private fun getFavourites() {
				viewModelScope.launch(Dispatchers.IO) {
						favCocktailUseCases.getFavCocktailsUseCase().onEach { result ->
								when(result) {
										is Resource.Error -> { _state.value = FavouriteListState(error = result.message ?: "An unexpected error occurred") }
										is Resource.Loading -> { _state.value = FavouriteListState(isLoading = true) }
										is Resource.Success -> { _state.value = FavouriteListState(favourites = result.data ?: emptyList()) }
								}
						}
				}
		}

		fun refresh() {
				getFavourites()
		}
}