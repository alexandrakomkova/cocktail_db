package com.example.cocktail_db.presentation.onboarding_screen

import com.example.cocktail_db.R

data class OnboardPage(
		val imageRes: Int,
		val title: String,
		val description: String
)

val onboardPagesList = listOf(
		OnboardPage(
				imageRes = R.drawable.lefteris_kallergis_qsmdvt5ptmw_unsplash,
				//imageRes = R.drawable.ic_launcher_foreground,
				title = "Welcome!",
				description = "This is cocktail_db_App made by alexandrakomkova"
		), OnboardPage(
				imageRes = R.drawable.igor_stepanov_rnkkusijnd4_unsplash,
				// imageRes = R.drawable.ic_launcher_foreground,
				title = "Get Random cocktails!",
				description = "This app gives You information about a random cocktail"
		), OnboardPage(
				imageRes = R.drawable.alexandra_golovac_kp8qykwd1r0_unsplash,
				// imageRes = R.drawable.ic_launcher_foreground,
				title = "Pull-to-Refresh update!",
				description = "Easy and comfortable random cocktail update!"
		)
)
