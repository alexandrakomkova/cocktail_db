package com.example.cocktail_db.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cocktail_db.R
import com.example.cocktail_db.domain.model.Cocktail
import com.example.cocktail_db.ui.theme.cocktailInfoWhite

@Composable
fun SmallCocktailCard(
		modifier: Modifier = Modifier,
		cocktail: Cocktail,
		onItemClick: (Cocktail) -> Unit
) {

		val checked = remember { mutableStateOf(false) }

		Card(
				modifier = modifier.fillMaxWidth().clickable { onItemClick(cocktail) },
				shape = RoundedCornerShape(15.dp),
				elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
		) {
				Box(
						modifier = Modifier.height(200.dp)
				) {

						AsyncCocktailImage(
								imageUrl = cocktail.image,
								contentDesc = cocktail.name,
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

						IconToggleButton (
								modifier = Modifier.align(Alignment.TopEnd),
								checked = checked.value,
								onCheckedChange = { checked.value = it }
						) {
								Icon(
										painter = painterResource(id =
											if (checked.value ) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
										),
										contentDescription = "add_to_favourite",
								)
						}

						Box(
								modifier = Modifier
										.fillMaxSize()
										.padding(12.dp),
								contentAlignment = Alignment.BottomStart
						) {
								Text(
										text = cocktail.name,
										style = cocktailInfoWhite
								)

						}
				}
		}

}