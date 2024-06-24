package com.example.cocktail_db.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
				Column(
						modifier = Modifier.fillMaxSize(),
						verticalArrangement = Arrangement.Center,
						horizontalAlignment = Alignment.CenterHorizontally
				) {
						Text(
								text = errorText,
								color = Color.Red,
								textAlign = TextAlign.Center,
								modifier = Modifier
										.fillMaxWidth()
										.padding(10.dp)
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
}

@Preview
@Composable
fun ErrorTextRetryBtnPreview() {
		ErrorTextRetryBtn(errorText = "123", refreshFunction = {})
}