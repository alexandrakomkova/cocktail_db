package com.example.cocktail_db.presentation.onboarding_screen

import com.example.cocktail_db.R

data class OnboardPage(
		val imageRes: Int,
		val title: String,
		val description: String
)

val onboardPagesList = listOf(
		OnboardPage(
				imageRes = R.drawable.ic_launcher_foreground,
				title = "Welcome!",
				description = "This is cocktail_App"
		), OnboardPage(
				imageRes = R.drawable.ic_launcher_foreground,
				title = "Get Random cocktails!",
				description = "This app gives You a random cocktail\'s name, image, ingredients and other info "
		), OnboardPage(
				imageRes = R.drawable.ic_launcher_foreground,
				title = "Pull-to-Refresh update!",
				description = "Easy and comfortable random cocktail update!"
		)
)
