package com.example.cocktail_db.feature.category_list

import android.os.Build
import androidx.activity.compose.ReportDrawn
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cocktail_db.R
import com.example.cocktail_db.core.ErrorTextRetryBtn
import com.example.cocktail_db.domain.model.Category
import com.example.cocktail_db.ui.theme.cocktailInfoBlack
import com.example.cocktail_db.ui.theme.cocktailName

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CategoryListScreen(
		modifier: Modifier = Modifier,
		viewModel: CategoryListViewModel = hiltViewModel(),
		onCategoryClick: (Category) -> Unit,
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

				if(state.categories.isEmpty()) {
						EmptyCategoryList(modifier = modifier)
				} else {
						CategoryListScreen(state = state, onCategoryClick = onCategoryClick)
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
fun CategoryListScreen(
		state: CategoryListState,
		onCategoryClick: (Category) -> Unit
) {
		Column(
				modifier = Modifier
						.fillMaxSize()
						.padding(20.dp)
		) {
				Text(
						text = "Categories",
						style = cocktailName,
						modifier = Modifier.fillMaxWidth()
				)
				LazyColumn(
						modifier = Modifier
								.fillMaxSize()
								.padding(top = 15.dp)
				) {

						items(
								items = state.categories,
								key = { it.categoryName }
						) { category ->
								CategoryListItem(
										category = category,
										onCategoryClick = {
												onCategoryClick(category)
										}
								)
						}
				}
		}
}

@Composable
fun EmptyCategoryList(
		modifier: Modifier = Modifier
) {
		ReportDrawn()

		Column(
				modifier = modifier,
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
		) {
				androidx.compose.material3.Text(
						text = stringResource(id = R.string.category_list_empty),
						style = cocktailInfoBlack
				)

		}
}