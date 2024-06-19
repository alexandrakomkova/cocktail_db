package com.example.cocktail_db.feature.category_detail

import android.os.Build
import androidx.activity.compose.ReportDrawn
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.cocktail_db.domain.model.toCocktail
import com.example.cocktail_db.ui.theme.cocktailInfoBlack
import com.example.cocktail_db.ui.theme.cocktailName

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CategoryDetailScreen(
		modifier: Modifier = Modifier,
		viewModel: CategoryDetailViewModel = hiltViewModel(),
		onCocktailClick: (Cocktail) -> Unit
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
				if(state.error.isNotBlank()) {
						ErrorTextRetryBtn(errorText = state.error) { viewModel.refresh() }
				}

				if(state.isLoading) {
						CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
				}

				if(state.shortInfoCocktails.isEmpty()) {
						EmptyCategoryDetailList(modifier = modifier)
				} else {
						CategoryDetailScreen(state = state, onCocktailClick = onCocktailClick)
				}



		}


}

@Composable
fun CategoryDetailScreen(
		state: CategoryDetailState,
		onCocktailClick: (Cocktail) -> Unit
) {
		Column(
				modifier = Modifier
						.fillMaxSize()
						.padding(20.dp),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
		) {

				Text(
						text = state.categoryName,
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
						items(state.shortInfoCocktails) {cocktail ->
								SmallCocktailCard(
										imageUrl = cocktail.image,
										title = cocktail.name,
										onItemClick = { onCocktailClick(cocktail.toCocktail()) }
								)
						}
				}

		}

}

@Composable
fun EmptyCategoryDetailList(
		modifier: Modifier = Modifier
) {
		ReportDrawn()

		Column(
				modifier = modifier,
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
		) {
				androidx.compose.material3.Text(
						text = stringResource(id = R.string.category_detail_empty),
						style = cocktailInfoBlack
				)

		}
}