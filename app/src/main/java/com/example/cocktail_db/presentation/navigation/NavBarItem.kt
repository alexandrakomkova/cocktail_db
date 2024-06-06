package com.example.cocktail_db.presentation.navigation

import com.example.cocktail_db.R
import com.example.cocktail_db.core.Constants

data class NavBarItem (
		val label: String,
		val icon: Int,
		val route: String
)

val listOfNavItems: List<NavBarItem> = listOf(
		NavBarItem(
				label = "Categories",
				icon = R.drawable.baseline_format_list_bulleted_24,
				route = Constants.CATEGORIES_NAV_KEY
		),
		NavBarItem(
				label = "Home",
				icon = R.drawable.baseline_home_24,
				route = Constants.HOME_NAV_KEY
		),
		NavBarItem(
				label = "Favourites",
				icon = R.drawable.baseline_favorite_24,
				route = Constants.FAVOURITE_NAV_KEY
		),

)