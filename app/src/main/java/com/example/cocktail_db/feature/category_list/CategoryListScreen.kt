package com.example.cocktail_db.feature.category_list

import android.os.Build
import androidx.annotation.RequiresExtension
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
import androidx.compose.ui.unit.dp
import com.example.cocktail_db.core.ErrorTextRetryBtn
import com.example.cocktail_db.domain.model.Category
import com.example.cocktail_db.ui.theme.cocktailName
import org.koin.androidx.compose.koinViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CategoryListScreen(
		modifier: Modifier = Modifier,
		viewModel: CategoryListViewModel = koinViewModel(),
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
						state.categories.lastIndex
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