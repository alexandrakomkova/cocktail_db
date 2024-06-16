package com.example.cocktail_db.di

import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.cocktail_db.feature.category_list.CategoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
val appModule = module {


		viewModel<CategoryListViewModel> {
				CategoryListViewModel(
						cocktailDbUseCases = get()
				)
		}


}