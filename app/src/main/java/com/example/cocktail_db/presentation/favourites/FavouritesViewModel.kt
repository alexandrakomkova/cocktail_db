package com.example.cocktail_db.presentation.favourites

import androidx.lifecycle.ViewModel
import com.example.cocktail_db.domain.repository.FavCocktailRepository
import org.koin.core.component.KoinComponent

class FavouritesViewModel(
		favCocktailRepo: FavCocktailRepository
) : KoinComponent, ViewModel() {

//		val state: StateFlow<List<FavCocktail>> =
//				favCocktailRepo.getFavourites()
//						.stateIn(
//								scope = viewModelScope,
//								started = SharingStarted.WhileSubscribed(5_000),
//								initialValue = emptyList(),
//		)
}