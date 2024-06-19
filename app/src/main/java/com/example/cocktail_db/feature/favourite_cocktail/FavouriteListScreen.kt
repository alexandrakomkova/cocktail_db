package com.example.cocktail_db.feature.favourite_cocktail

import androidx.activity.compose.ReportDrawn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cocktail_db.R
import com.example.cocktail_db.core.components.ErrorTextRetryBtn
import com.example.cocktail_db.core.components.SmallCocktailCard
import com.example.cocktail_db.domain.model.Cocktail
import com.example.cocktail_db.ui.theme.cocktailInfoBlack

@Composable
fun FavouriteListScreen(
		modifier: Modifier = Modifier,
		viewModel: FavouriteListViewModel = hiltViewModel(),
		onCocktailClick: (Cocktail) -> Unit,
) {
		val state = viewModel.state.value

		Box(
				modifier = Modifier
						.fillMaxSize()
						.padding(
								horizontal = 15.dp,
								vertical = 10.dp
						)
		) {

				if(state.favourites.isEmpty()) {
						EmptyFavouriteList(modifier = modifier)
				} else {
						FavouriteListScreen(
								modifier = modifier,
								state = state,
								onCocktailClick = onCocktailClick
						)
				}
				if(state.error.isNotBlank()) {
						ErrorTextRetryBtn(errorText = state.error) { viewModel.refresh() }
				}
				if(state.isLoading) {
						CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
				}
		}

}

@Composable
fun FavouriteListScreen(
		modifier: Modifier = Modifier,
		state: FavouriteListState,
		onCocktailClick: (Cocktail) -> Unit
) {
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
								imageUrl = cocktail.image,
								title = cocktail.name,
								onCocktailClick = {
										onCocktailClick
								}
						)
				}
		}

}

@Composable
fun EmptyFavouriteList(
		modifier: Modifier = Modifier
) {
		ReportDrawn()

		Column(
				modifier = modifier,
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
		) {
				Text(
						text = stringResource(id = R.string.favourite_list_empty),
						style = cocktailInfoBlack
				)

		}
}