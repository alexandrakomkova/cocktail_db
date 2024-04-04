package com.example.cocktail_db.presentation.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cocktail_db.ui.theme.Purple40
import com.example.cocktail_db.ui.theme.cocktailInfoBlack

@Composable
fun ErrorTextRetryBtn(
		errorText: String,
		refreshFunction: () -> Unit
) {
		Box(
				modifier = Modifier.fillMaxWidth()
		) {
				Text(
						text = errorText,
						color = Color.Red,
						textAlign = TextAlign.Center,
						modifier = Modifier
								.fillMaxWidth()
								.align(Alignment.Center)
								.padding(
										start = 20.dp,
										end = 20.dp,
										top = 170.dp,
										bottom = 70.dp
								)
				)

				Button(
						onClick = refreshFunction,
						modifier = Modifier,
						colors = ButtonDefaults.buttonColors(
								backgroundColor = Purple40
						),
						shape = RoundedCornerShape(25.dp)
				) {
						Text(
								text = "Retry",
								style = cocktailInfoBlack,
								color = Color.White
						)
				}
		}
}