package com.example.cocktail_db.feature.favourite_cocktail

import androidx.activity.compose.ReportDrawn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cocktail_db.R
import com.example.cocktail_db.core.ErrorTextRetryBtn
import com.example.cocktail_db.domain.model.Cocktail
import com.example.cocktail_db.ui.theme.cocktailInfoBlack
import org.koin.androidx.compose.getViewModel

@Composable
fun FavouriteListScreen(
		modifier: Modifier = Modifier,
		viewModel: FavouriteListViewModel = getViewModel(),
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