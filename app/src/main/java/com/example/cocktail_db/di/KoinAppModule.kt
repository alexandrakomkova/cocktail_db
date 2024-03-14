package com.example.cocktail_db.di

import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.dsl.module

val appModule = module {
		viewModel<RandomCocktailViewModel> {
				RandomCocktailViewModel(
						cocktailUseCases = get()
				)
		}
}