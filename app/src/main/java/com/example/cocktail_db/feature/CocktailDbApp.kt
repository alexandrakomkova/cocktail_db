package com.example.cocktail_db.feature

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cocktail_db.feature.home.HomeScreen

@Composable
fun CocktailDbApp() {
		val navController = rememberNavController()
		CocktailDbNavHost(
				navController = navController
		)
}

@Composable
fun CocktailDbNavHost(
		navController: NavHostController
) {
		val activity = (LocalContext.current as Activity)
		NavHost(
				navController = navController,
				startDestination = Screen.Home.route) {

				composable(route = Screen.Home.route) {
						HomeScreen(

						)
				}

				composable(
						route = Screen.CategoryDetail.route,
						arguments = Screen.CategoryDetail.navArguments
				) {}

				composable(
						route = Screen.CocktailDetail.route,
						arguments = Screen.CocktailDetail.navArguments
				) {}
		}
}