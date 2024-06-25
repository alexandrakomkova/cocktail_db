package com.example.cocktail_db.feature.cocktail_detail

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cocktail_db.R
import com.example.cocktail_db.core.components.AsyncCocktailImage
import com.example.cocktail_db.core.components.ErrorTextRetryBtn
import com.example.cocktail_db.domain.model.Cocktail
import com.example.cocktail_db.ui.theme.cocktailId
import com.example.cocktail_db.ui.theme.cocktailInfoBlack
import com.example.cocktail_db.ui.theme.cocktailInfoGrey
import com.example.cocktail_db.ui.theme.cocktailName

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CocktailDetailScreen(
		modifier: Modifier = Modifier,
		viewModel: CocktailDetailViewModel = hiltViewModel(),
		onUpClick: () -> Unit,
) {
		val state = viewModel.state.value

		Column(
				modifier = Modifier
						.fillMaxSize()
						.verticalScroll(rememberScrollState()),
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally
		) {
				when {
						state.isLoading -> {
								CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
						}
						state.error.isNotBlank() -> {
								ErrorTextRetryBtn(errorText = state.error) { viewModel.refresh() }
						}
						state.cocktails.isEmpty() -> {
								EmptyCocktailDetail(modifier = modifier)
						}
						else -> {
								CocktailCard(modifier = modifier, cocktail = state.cocktails[0], onUpClick = onUpClick)
						}
				}
		}
}

@Composable
fun CocktailCard(
		modifier: Modifier = Modifier,
		cocktail: Cocktail,
		onUpClick: () -> Unit
) {

		Box {
				AsyncCocktailImage(
						imageUrl = cocktail.image,
						contentDesc = cocktail.name,
						modifier = modifier
								.fillMaxWidth()
								.clip(MaterialTheme.shapes.medium)
				)

				CocktailDetailToolbar(
						modifier = modifier,
						onUpClick = onUpClick
				)
		}


		Box(
				modifier = modifier
						.fillMaxSize()
						.padding(top = 15.dp)
		) {
				Column(
						modifier = modifier.padding(horizontal = 15.dp)
				) {


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
						HorizontalDivider(
								modifier = Modifier
										.width(15.dp)
										.padding(vertical = 10.dp), thickness = 1.dp, color = Color.Gray
						)
						Text(
								text = "Glass type: ${cocktail.glassType}",
								style = cocktailInfoBlack
						)
						HorizontalDivider(
								modifier = Modifier
										.width(15.dp)
										.padding(vertical = 10.dp), thickness = 1.dp, color = Color.Gray
						)
						Text(
								text = "Ingredients:",
								style = cocktailInfoBlack
						)
						IngredientsList(cocktail = cocktail)
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


@Composable
fun EmptyCocktailDetail(
		modifier: Modifier = Modifier
) {
		ReportDrawn()

		Column(
				modifier = modifier,
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
		) {
				Text(
						text = stringResource(id = R.string.cocktail_detail_empty),
						style = cocktailInfoBlack
				)

				CocktailDetailToolbar(
						modifier = modifier,
						onUpClick = {}
				)

		}

}

@Composable
fun CocktailDetailToolbar(
		modifier: Modifier = Modifier,
		onUpClick: () -> Unit
) {

		val checked = remember { mutableStateOf(false) }

		Row(
				modifier = modifier
						.fillMaxSize()
						.systemBarsPadding()
						.padding(vertical = 15.dp, horizontal = 10.dp),
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
								.padding(start =12.dp)
								.then(iconModifier)
				) {
						Icon(
								Icons.AutoMirrored.Filled.ArrowBack,
								contentDescription = stringResource(id = R.string.arrow_back)
						)
				}

				IconToggleButton(
						checked = checked.value,
						onCheckedChange = { checked.value = it },
						modifier = Modifier
								.padding(end =12.dp)
								.then(iconModifier)
				) {
						Icon(
								if (checked.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
								contentDescription = "favourite_btn",
								tint = if (checked.value) Color.Red else Color.Black,
						)
				}
		}

}

@Preview
@Composable
fun CocktailCardPreview() {
		CocktailCard(
				cocktail = Cocktail(
						id = 15346,
						name = "155 Belmont",
						glassType = "White wine glass",
						category = "Cocktail",
						cocktailType = "Alcoholic",
						ingredientsList = listOf("Dark rum", "Light rum", "Vodka", "Orange juice"),
						measuresList = listOf("1 shot", "2 shots ", "1 shot ", "1 shot "),
						image = "https://www.thecocktaildb.com//images//media//drink//yqvvqs1475667388.jpg"
				),
				onUpClick = {}
		)
}