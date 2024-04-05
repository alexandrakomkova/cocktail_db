package com.example.cocktail_db.presentation.short_info_cocktail

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cocktail_db.presentation.core.AsyncCocktailImage
import com.example.cocktail_db.ui.theme.cocktailInfoWhite
import com.example.cocktail_db.ui.theme.cocktailName

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CocktailsByCategoryScreen(
		viewModel: ShortInfoCocktailViewModel
) {
		val state = viewModel.state.value
		Column(
				modifier = Modifier
						.fillMaxSize()
						.padding(20.dp),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
		) {
				// category name
				// lazy grid

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
								CocktailCard(
										imageUrl = cocktail.image,
										title = cocktail.name
								)
						}
				}

		}
}

@Composable
fun CocktailCard(
		imageUrl: String,
		title: String,
		modifier: Modifier = Modifier
) {
		Card(
				modifier = modifier.fillMaxWidth(),
				shape = RoundedCornerShape(15.dp),
				elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
		) {
				Box(
						modifier = Modifier.height(200.dp)
				) {
						AsyncCocktailImage(
								imageUrl = imageUrl,
								contentDesc = title,
								modifier = Modifier.fillMaxSize())

						Box(
								modifier = Modifier
										.fillMaxSize()
										.background(brush = Brush.verticalGradient(
												colors = listOf(
														Color.Transparent,
														Color.Black
												),
												startY = 300f
										))
						)

						Box(
								modifier = Modifier
										.fillMaxSize()
										.padding(12.dp),
								contentAlignment = Alignment.BottomStart
						) {
								Text(
										text = title,
										style = cocktailInfoWhite
								)

						}
				}
		}

}

