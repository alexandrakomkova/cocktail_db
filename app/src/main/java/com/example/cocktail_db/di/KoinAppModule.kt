package com.example.cocktail_db.di

import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.cocktail_db.presentation.category.CategoryViewModel
import com.example.cocktail_db.presentation.random_cocktail_screen.RandomCocktailViewModel
import com.example.cocktail_db.presentation.short_info_cocktail.ShortInfoCocktailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
val appModule = module {
		viewModel<RandomCocktailViewModel> {
				RandomCocktailViewModel(
						cocktailUseCases = get()
				)
		}

		viewModel<CategoryViewModel> {
				CategoryViewModel(
						cocktailUseCases = get()
				)
		}

		viewModel<ShortInfoCocktailViewModel> {
				ShortInfoCocktailViewModel(
						cocktailUseCases = get(),
						savedStateHandle = get()
				)
		}

}