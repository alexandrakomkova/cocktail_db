package com.example.cocktail_db.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cocktail_db.MainActivity
import com.example.cocktail_db.presentation.onboarding_screen.OnboardingStorage
import com.example.cocktail_db.ui.theme.Purple40

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun AppNavigation(
		mainActivity: MainActivity,
		storage: OnboardingStorage,
) {
		val navController: NavHostController = rememberNavController()


		Scaffold(
				bottomBar = {
						BottomNavBar(navController)
				},
				containerColor = Color.White
		) { paddingValues ->
				BottomBarNavHost(
						navController,
						storage,
						mainActivity,
						paddingValues
				)
		}
}

@Composable
fun BottomNavBar(
		navController: NavController
) {
		NavigationBar(
				modifier = Modifier.padding(bottom = 20.dp),
				containerColor = Color.White
		) {
				val navBackStackEntry by navController.currentBackStackEntryAsState()
				val currentDestination = navBackStackEntry?.destination

				listOfNavItems.forEach { navItem ->
						AddItem(
								navItem = navItem,
								currentDestination = currentDestination,
								navController = navController
						)
				}

		}
}

@Composable
fun RowScope.AddItem(
		navItem: NavBarItem,
		currentDestination: NavDestination?,
		navController: NavController
) {
		NavigationBarItem(
				selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
				onClick = {
						navController.navigate(navItem.route) {
								popUpTo(navController.graph.findStartDestination().id) { saveState = true }
								launchSingleTop = true
								restoreState = true
						}
				},
				icon = {
						Icon(
								painter = painterResource(id = navItem.icon),
								contentDescription = navItem.label,
								modifier = Modifier.size(30.dp)
						)
				},
				label = {
						Text(
								text = navItem.label,
						)
				},
				alwaysShowLabel = true,
				colors = NavigationBarItemDefaults.colors(
						selectedIconColor = Purple40,
						selectedTextColor = Purple40,
						unselectedIconColor = Color.Black,
						unselectedTextColor = Color.Black,
						indicatorColor = Color.White
				)
		)
}