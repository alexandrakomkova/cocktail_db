package com.example.cocktail_db.feature.category_list

import androidx.lifecycle.ViewModel
import com.example.cocktail_db.domain.use_case.cocktail_use_case.CocktailDbUseCases
import org.koin.core.component.KoinComponent

class CategoryListViewModel(
		private val cocktailDbUseCases: CocktailDbUseCases
): ViewModel(), KoinComponent {

}