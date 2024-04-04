package com.example.cocktail_db.presentation.short_info_cocktail

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CocktailsByCategoryScreen(
		viewModel: ShortInfoCocktailViewModel
) {
		val state = viewModel.state.value

}