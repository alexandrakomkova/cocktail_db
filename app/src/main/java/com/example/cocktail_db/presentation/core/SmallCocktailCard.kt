package com.example.cocktail_db.presentation.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.cocktail_db.ui.theme.cocktailInfoWhite


@Composable
fun SmallCocktailCard(
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
