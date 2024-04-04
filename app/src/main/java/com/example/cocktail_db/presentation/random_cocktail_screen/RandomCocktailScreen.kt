package com.example.cocktail_db.presentation.random_cocktail_screen

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import com.example.cocktail_db.domain.model.Cocktail
import com.example.cocktail_db.presentation.core.ErrorTextRetryBtn
import com.example.cocktail_db.ui.theme.cocktailId
import com.example.cocktail_db.ui.theme.cocktailInfoBlack
import com.example.cocktail_db.ui.theme.cocktailInfoGrey
import com.example.cocktail_db.ui.theme.cocktailName


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RandomCocktailScreen(
		viewModel: RandomCocktailViewModel,
		state: RandomCocktailState
) {

		val refreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
		val pullRefreshState = rememberPullRefreshState(refreshing, { viewModel.refresh() })

		Column(
				modifier = Modifier
						.fillMaxSize()
						.pullRefresh(pullRefreshState, true)
						.verticalScroll(rememberScrollState()),
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally
		) {

				if (state.cocktails.isEmpty()) state.cocktails.lastIndex.toString() else CocktailCard(state.cocktails[0])

				if(state.error.isNotBlank()) {
						ErrorTextRetryBtn(errorText = state.error) { viewModel.refresh() }
				}

				if(state.isLoading) {
						CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
				}

				PullRefreshIndicator(refreshing, pullRefreshState)
		}
}

@Composable
fun CocktailCard(cocktail: Cocktail) {

		//img
		Box { CocktailImage(cocktail.image) }

		// all info
		Box(
				modifier = Modifier
						.fillMaxWidth()
						.padding(top = 15.dp)
		) {
				Column(
						modifier = Modifier.padding(horizontal = 15.dp)
				) {

						FavouriteButton()
						// id + name
						OverlayText(cocktail.id.toString(), cocktail.name)

						cocktail.cocktailType?.let {
								Text(
										text = it,
										style = cocktailInfoGrey,
										modifier = Modifier.padding(bottom = 20.dp)
								)
						}
						Text(
								text = "Category: ${cocktail.category}",
								style = cocktailInfoBlack
						)
						Divider(modifier = Modifier
								.width(15.dp)
								.padding(vertical = 10.dp), thickness = 1.dp, color = Color.Gray)
						Text(
								text = "Glass type: ${cocktail.glassType}",
								style = cocktailInfoBlack
						)
						Divider(modifier = Modifier
								.width(15.dp)
								.padding(vertical = 10.dp), thickness = 1.dp, color = Color.Gray)
						Text(
								text = "Glass type: ${cocktail.glassType}",
								style = cocktailInfoBlack
						)
						Divider(modifier = Modifier
								.width(15.dp)
								.padding(vertical = 10.dp), thickness = 1.dp, color = Color.Gray)
						Text(
								text = "Ingredients:",
								style = cocktailInfoBlack
						)
						IngredientsList(cocktail = cocktail)
				}
		}


}

@Composable
fun CocktailImage(imageUrl: String) {

		SubcomposeAsyncImage(
				model = imageUrl,
				contentDescription = "cocktail_img"
		) {
				val state = painter.state
				if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
						CircularProgressIndicator()
				} else {
						Image(
								painter = painter,
								contentDescription = null,
								contentScale = ContentScale.Crop,
								modifier = Modifier
										.fillMaxWidth()
										.clip(MaterialTheme.shapes.medium)
						)
				}
		}
}

@Composable
fun OverlayText(text1: String, text2: String) {
		Box(
				modifier = Modifier.fillMaxWidth(),
				contentAlignment = Alignment.Center
		) {
				Text(
						text = text1,
						style = cocktailId,
						modifier = Modifier.align(Alignment.TopStart)
				)

				Text(
						text = text2,
						style = cocktailName,
						maxLines = 2,
						modifier = Modifier
								.align(Alignment.TopStart)
								.fillMaxWidth()
								.padding(top = 5.dp)
				)
		}
}

@Composable
fun FavouriteButton() {
		val checked = remember { mutableStateOf(false) }
		Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.End
		) {
				IconToggleButton(
						modifier = Modifier.size(32.dp),
						checked = checked.value,
						onCheckedChange = { checked.value = it }
				) {
						Icon(
								if (checked.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
								contentDescription = "favourite_btn",
								tint = if (checked.value) Color.Red else Color.Black,
						)
				}
		}
}

@Composable
fun IngredientsList(
		cocktail: Cocktail
) {
		LazyColumn(
				modifier = Modifier
						.fillMaxWidth()
						.padding(top = 15.dp)
						.height(400.dp)
		) {
				val mapIngredientWithMeasure = cocktail.ingredientsList.associateWith { key -> cocktail.measuresList[cocktail.ingredientsList.indexOf(key)] ?: "to taste" }

				items(cocktail.ingredientsList) {ingredient ->
						Row(
								modifier = Modifier
										.fillMaxWidth()
										.padding(bottom = 5.dp),
								horizontalArrangement = Arrangement.SpaceBetween,
								verticalAlignment = Alignment.CenterVertically
						) {
								Text(
										text = "${ingredient}: ",
										style = cocktailInfoBlack
								)
								Text(
										text = "${mapIngredientWithMeasure[ingredient]}",
										style = cocktailInfoGrey
								)
						}
				}
		}
}