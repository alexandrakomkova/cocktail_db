package com.example.cocktail_db.feature

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cocktail_db.feature.category_detail.CategoryDetailScreen
import com.example.cocktail_db.feature.cocktail_detail.CocktailDetailScreen
import com.example.cocktail_db.feature.home.HomeScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CocktailDbApp() {
		val navController = rememberNavController()
		CocktailDbNavHost(
				navController = navController
		)
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CocktailDbNavHost(
		navController: NavHostController
) {

		NavHost(
				navController = navController,
				startDestination = Screen.Home.route) {

				composable(route = Screen.Home.route) {
						HomeScreen(
								onCategoryClick = {
										navController.navigate(
												Screen.CategoryDetail.createRoute(
														categoryName = it.categoryName
												)
										)
								},
								onCocktailClick = {
										navController.navigate(
												Screen.CocktailDetail.createRoute(
														cocktailId = it.id.toString()
												)
										)
								},
								onFavCocktailClick = {
										navController.navigate(
												Screen.CocktailDetail.createRoute(
														cocktailId = it.id.toString()
												)
										)
								}
						)
				}

				composable(
						route = Screen.CategoryDetail.route,
						arguments = Screen.CategoryDetail.navArguments
				) {
						CategoryDetailScreen(
								onCocktailClick = {
										Log.d("onCocktailClick", it.id.toString())
										navController.navigate(
												Screen.CocktailDetail.createRoute(
														cocktailId = it.id.toString()
												)
										)
								}
						)
				}

				composable(
						route = Screen.CocktailDetail.route,
						arguments = Screen.CocktailDetail.navArguments
				) {
						CocktailDetailScreen(
								onUpClick = {
										navController.navigateUp()
								}
						)
				}
		}
}