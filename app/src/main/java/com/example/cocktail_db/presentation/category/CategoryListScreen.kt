package com.example.cocktail_db.presentation.category

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cocktail_db.core.Constants
import com.example.cocktail_db.presentation.core.ErrorTextRetryBtn
import com.example.cocktail_db.ui.theme.cocktailName

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CategoryListScreen(
		viewModel: CategoryViewModel,
		navController: NavController
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
						CategoriesList(state = state, navController = navController)
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
fun CategoriesList(
		state: CategoryState,
		navController: NavController
) {
		Column {
				Text(
						text = "Categories",
						style = cocktailName,
						modifier = Modifier.fillMaxWidth()
				)
				LazyColumn(
						modifier = Modifier
								.fillMaxSize()
								.padding(top = 10.dp)
				) {

						items(state.categories) { category ->
								CategoryListItem(
										category = category,
										onItemClick = {
												navController.navigate(Constants.COCKTAIL_BY_CATEGORY_NAV_KEY + "/{" + Constants.COCKTAIL_CATEGORY_NAME_PARAM + "}")
										}
								)
						}
				}
		}

}