package com.example.cocktail_db.feature

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
		val route: String,
		val navArguments: List<NamedNavArgument> = emptyList()
) {
		// cached cocktails + search bar
		data object Home : Screen("home")

		// category list
		data object CategoryList : Screen("category_list")

		// cocktails in selected category
		data object CategoryDetail : Screen(
				route = "categoryDetail/{categoryName}",
				navArguments = listOf(navArgument("categoryName") {
						type = NavType.StringType
				})
		) {
				fun createRoute(categoryName: String) = "categoryDetail/${categoryName}"
		}

		// favourite cocktails list
		data object FavCocktailList : Screen("fav_cocktail_list")

		// cocktail details
		data object CocktailDetail : Screen(
				route = "cocktailDetail/{cocktailId}",
				navArguments = listOf(navArgument("cocktailId") {
						type = NavType.StringType
				})
		) {
				fun createRoute(cocktailId: String) = "cocktailDetail/${cocktailId}"
		}




}