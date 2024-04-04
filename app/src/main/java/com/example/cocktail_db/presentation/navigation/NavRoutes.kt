package com.example.cocktail_db.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import com.example.cocktail_db.core.Constants
import com.example.cocktail_db.presentation.category.CategoryListScreen
import com.example.cocktail_db.presentation.category.CategoryViewModel
import com.example.cocktail_db.presentation.onboarding_screen.OnboardingResult
import com.example.cocktail_db.presentation.onboarding_screen.OnboardingScreen
import com.example.cocktail_db.presentation.onboarding_screen.OnboardingState
import com.example.cocktail_db.presentation.onboarding_screen.OnboardingStorage
import com.example.cocktail_db.presentation.random_cocktail_screen.RandomCocktailScreen
import com.example.cocktail_db.presentation.random_cocktail_screen.RandomCocktailViewModel
import com.example.cocktail_db.presentation.short_info_cocktail.CocktailsByCategoryScreen
import com.example.cocktail_db.presentation.short_info_cocktail.ShortInfoCocktailViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun RandomCocktailRoute(
		onboardingStorage: OnboardingStorage,
		savedStateHandle: SavedStateHandle,
		toOnboarding: () -> Unit,
		onOnboardingCancelled: () -> Unit,
) {
		val onboardingState by onboardingStorage.onboardingState.collectAsState()
		val onboardingResult by savedStateHandle.getLiveData<OnboardingResult>(Constants.ONBOARDING_NAV_KEY).observeAsState()
		val viewModel: RandomCocktailViewModel = koinViewModel()

		when (onboardingState) {
				OnboardingState.NotOnboarded -> {
						when (onboardingResult) {
								null -> LaunchedEffect(key1 = Unit) {
										toOnboarding()
								}
								OnboardingResult.Completed -> RandomCocktailScreen(viewModel = viewModel, state = viewModel.state.value)
								OnboardingResult.Cancelled -> LaunchedEffect(key1 = Unit) {
										onOnboardingCancelled()
								}
						}
				}
				OnboardingState.Onboarded -> RandomCocktailScreen(viewModel = viewModel, state = viewModel.state.value)
		}
}

@Composable
fun OnboardingRoute(
		onboardingStorage: OnboardingStorage,
		popBackStack: () -> Unit,
) {
		OnboardingScreen(
				onOnboarded = {
						onboardingStorage.onOnboarded()
						popBackStack()
				},
		)
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CategoriesRoute(
		navController: NavController
) {
		val viewModel: CategoryViewModel = koinViewModel()

		CategoryListScreen(viewModel = viewModel, navController = navController)
}

@Composable
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
fun CocktailsByCategoryRoute() {
		val viewModel: ShortInfoCocktailViewModel = getViewModel()
		CocktailsByCategoryScreen(viewModel = viewModel)
}