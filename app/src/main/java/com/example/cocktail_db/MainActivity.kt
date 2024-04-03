package com.example.cocktail_db

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cocktail_db.core.Constants
import com.example.cocktail_db.presentation.navigation.OnboardingRoute
import com.example.cocktail_db.presentation.navigation.RandomCocktailRoute
import com.example.cocktail_db.presentation.onboarding_screen.OnboardingResult
import com.example.cocktail_db.presentation.onboarding_screen.OnboardingStorage
import com.example.cocktail_db.ui.theme.Cocktail_dbTheme

class MainActivity : ComponentActivity() {
		private lateinit var storage: OnboardingStorage
		@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
		override fun onCreate(savedInstanceState: Bundle?) {
				enableEdgeToEdge()
				super.onCreate(savedInstanceState)
				setContent {
						Cocktail_dbTheme {
								Surface(
										modifier = Modifier.fillMaxSize(),
										color = MaterialTheme.colorScheme.background
								) {

										// val viewModel: RandomCocktailViewModel = koinViewModel()
										// RandomCocktailScreen(viewModel = viewModel, state = viewModel.state.value)
										storage = OnboardingStorage()


										val navController = rememberNavController()
										NavHost(
												navController = navController,
												startDestination = Constants.RANDOM_COCKTAIL_NAV_KEY,
										) {
												composable(Constants.RANDOM_COCKTAIL_NAV_KEY) { backStackEntry ->
														RandomCocktailRoute(
																onboardingStorage = storage,
																savedStateHandle = backStackEntry.savedStateHandle,
																toOnboarding = {
																		navController.navigate(Constants.ONBOARDING_NAV_KEY)
																},
																onOnboardingCancelled = {
																		this@MainActivity.finish()
																}
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
										}
								}
						}
				}
		}
}



