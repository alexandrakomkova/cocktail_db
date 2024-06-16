package com.example.cocktail_db.presentation.favourites


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktail_db.core.Resource
import com.example.cocktail_db.domain.model.FavCocktail
import com.example.cocktail_db.domain.use_case.fav_cocktails_use_case.FavCocktailUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent


class FavouritesViewModel(
		private val favCocktailUseCases: FavCocktailUseCases
) : ViewModel(), KoinComponent {

		private val _state = mutableStateOf(FavouritesState())
		val state: State<FavouritesState> = _state

		init {
				getFavCocktails()
		}

		private fun getFavCocktails() {
				favCocktailUseCases.getFavCocktailsUseCase().onEach { result ->
						when(result) {
								is Resource.Loading -> { _state.value = FavouritesState(isLoading = true) }
								is Resource.Error -> { _state.value = FavouritesState(error = result.message ?: "An unexpected error occurred") }
								is Resource.Success -> { _state.value = FavouritesState(favourites = result.data ?: emptyList()) }
						}

				}.launchIn(viewModelScope)
		}

		fun addFavCocktail(cocktail: FavCocktail) {
				viewModelScope.launch(Dispatchers.IO) {
						favCocktailUseCases.addFavCocktailUseCase(cocktail)
				}
		}

		fun deleteFavCocktail(cocktail: FavCocktail) {
				viewModelScope.launch(Dispatchers.IO) {
						favCocktailUseCases.deleteFavCocktailUseCase(cocktail)
				}
		}
}