package com.example.cocktail_db.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cocktail_db.MainActivity
import com.example.cocktail_db.core.Constants
import com.example.cocktail_db.presentation.onboarding_screen.OnboardingResult
import com.example.cocktail_db.presentation.onboarding_screen.OnboardingStorage

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun BottomBarNavHost(
		navController: NavHostController,
		storage: OnboardingStorage,
		mainActivity: MainActivity,
		paddingValues: PaddingValues

) {
		NavHost(
				navController = navController,
				startDestination = Constants.CATEGORIES_NAV_KEY,
		) {
				composable(Constants.RANDOM_COCKTAIL_NAV_KEY) { backStackEntry ->
						RandomCocktailRoute(
								onboardingStorage = storage,
								savedStateHandle = backStackEntry.savedStateHandle,
								toOnboarding = {
										navController.navigate(Constants.ONBOARDING_NAV_KEY)
								},
								onOnboardingCancelled = { mainActivity.finish() }
						)
				}
				composable(Constants.ONBOARDING_NAV_KEY) {
						LaunchedEffect(key1 = Unit) {
								navController.previousBackStackEntry?.savedStateHandle?.set(
										Constants.ONBOARDING_NAV_KEY,
										OnboardingResult.Cancelled,
								)
						}
						OnboardingRoute(
								onboardingStorage = storage,
								popBackStack = { navController.popBackStack() },
						)
				}


				composable(Constants.CATEGORIES_NAV_KEY) { CategoriesRoute(navController) }
				composable(Constants.HOME_NAV_KEY) {}
				composable(Constants.FAVOURITE_NAV_KEY) { FavouritesRoute(navController) }

				composable(
						Constants.COCKTAIL_BY_CATEGORY_NAV_KEY + "/{" + Constants.COCKTAIL_CATEGORY_NAME_PARAM + "}"
				) { CocktailsByCategoryRoute() }
		}
}