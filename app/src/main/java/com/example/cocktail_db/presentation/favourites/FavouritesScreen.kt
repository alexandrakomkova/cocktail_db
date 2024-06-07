package com.example.cocktail_db.presentation.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktail_db.presentation.core.SmallCocktailCard
import com.example.cocktail_db.ui.theme.cocktailName

@Composable
fun FavouritesScreen(
		viewModel: FavouritesViewModel,
		navController: NavController
) {

		val state = viewModel.state.value

		Column(
				modifier = Modifier
						.fillMaxSize()
						.padding(top = 30.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
		) {

				Text(
						text = "Favourites",
						style = cocktailName,
						modifier = Modifier.fillMaxWidth()
				)

				LazyVerticalStaggeredGrid(
						columns = StaggeredGridCells.Adaptive(150.dp),
						modifier = Modifier
								.fillMaxSize()
								.padding(top = 15.dp),
						horizontalArrangement = Arrangement.spacedBy(20.dp),
						verticalItemSpacing = 20.dp
				) {
						items(state.favourites) {cocktail ->
								SmallCocktailCard(
										imageUrl = "",
										title = "",
//										onItemClick = {
//												navController.navigate(Constants.COCKTAIL_BY_CATEGORY_NAV_KEY + "/${category.categoryName}")
//										}
								)
						}
				}

		}
}