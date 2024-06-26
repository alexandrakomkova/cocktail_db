package com.example.cocktail_db.feature.category_detail

import android.os.Build
import androidx.activity.compose.ReportDrawn
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
		onCocktailClick: (Cocktail) -> Unit,
		onUpClick: () -> Unit
) {
		val state = viewModel.state.value

		Box(
				modifier = Modifier
						.fillMaxSize()
//						.padding(
//								horizontal = 15.dp,
//								vertical = 10.dp
//						)
		) {

				when {
						state.isLoading -> {
								CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
						}
						state.error.isNotBlank() -> {
								ErrorTextRetryBtn(errorText = state.error) { viewModel.refresh() }
						}
						state.shortInfoCocktails.isEmpty() -> {
								EmptyCategoryDetailList(modifier = modifier)
						}
						else -> {
								CategoryDetailScreen(
										modifier = modifier,
										state = state,
										onCocktailClick = onCocktailClick,
										onUpClick = onUpClick
								)
						}
				}

		}


}

@Composable
fun CategoryDetailScreen(
		modifier: Modifier = Modifier,
		state: CategoryDetailState,
		onCocktailClick: (Cocktail) -> Unit,
		onUpClick: () -> Unit
) {
				Column(
						horizontalAlignment = Alignment.CenterHorizontally,
						verticalArrangement = Arrangement.Center
				) {

						CategoryDetailToolbar(modifier = modifier, onUpClick = onUpClick, categoryName = state.categoryName)

						LazyVerticalStaggeredGrid(
								columns = StaggeredGridCells.Adaptive(150.dp),
								modifier = Modifier
										.fillMaxSize()
										//.padding(top = 15.dp),
										.padding(vertical = 15.dp, horizontal = 20.dp),
								horizontalArrangement = Arrangement.spacedBy(20.dp),
								verticalItemSpacing = 20.dp
						) {
								items(state.shortInfoCocktails) { cocktail ->
										SmallCocktailCard(
												cocktail = cocktail.toCocktail(),
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

@Composable
fun CategoryDetailToolbar(
		modifier: Modifier = Modifier,
		onUpClick: () -> Unit,
		categoryName: String
) {
		Row(
				modifier = modifier
						.fillMaxWidth()
						.systemBarsPadding()
						.padding(10.dp)
								,
				horizontalArrangement = Arrangement.SpaceBetween
		) {
				val iconModifier = Modifier
						.sizeIn(
								maxWidth = 32.dp,
								maxHeight = 32.dp
						)
						.background(
								color = MaterialTheme.colorScheme.surface,
								shape = CircleShape
						)

				IconButton(
						onClick = onUpClick,
						modifier = Modifier
								.padding(start = 12.dp)
								.then(iconModifier)

				) {
						Icon(
								Icons.AutoMirrored.Filled.ArrowBack,
								contentDescription = stringResource(id = R.string.arrow_back)
						)
				}

				Text(
						text = categoryName,
						style = cocktailName,
						modifier = Modifier.padding(start = 5.dp)
				)
		}

}

@Preview
@Composable
fun CategoryDetailToolbarPreview() {
		CategoryDetailToolbar(
				onUpClick = {},
				categoryName = "Cocktails"
		)
}