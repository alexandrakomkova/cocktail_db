package com.example.cocktail_db.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cocktail_db.domain.model.Cocktail
import com.example.cocktail_db.ui.theme.cocktailInfo
import com.example.cocktail_db.ui.theme.cocktailName


@Composable
fun RandomCocktailScreen(
		state: RandomCocktailState
) {
		Box(
				modifier = Modifier.fillMaxSize()
		) {
				Column(
						verticalArrangement = Arrangement.Center,
						horizontalAlignment = Alignment.CenterHorizontally
				) {

						if (state.cocktails.isEmpty()) state.cocktails.lastIndex.toString() else CocktailCard(state.cocktails[0])

						if(state.error.isNotBlank()) {
								Text(
										text = state.error,
										color = Color.Red,
										textAlign = TextAlign.Center,
										modifier = Modifier
												.fillMaxWidth()
												.padding(horizontal = 20.dp)
												.align(Alignment.CenterHorizontally)
								)
						}

						if(state.isLoading) {
								CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
						}

				}
		}
}

@Composable
fun CocktailCard(cocktail: Cocktail) {

		Row(

		) {
				Text(
						text = cocktail.id.toString()
				)
				Divider(modifier = Modifier.height(20.dp).width(2.dp).background(Color.Red))
				Text(
						text = cocktail.name,
						modifier = Modifier.fillMaxWidth(),
						style = cocktailName
				)
		}
		cocktail.cocktailType?.let {
				Text(
						text = it,
						style = TextStyle(
								fontSize = 14.sp,
								fontFamily = FontFamily.Monospace,
								color = Color.Gray,
								textAlign = TextAlign.Left,
						)
				)
		}
		Text(
				text = "Category: ${cocktail.category}",
				style = cocktailInfo
		)
		Text(
				text = "Glass type: ${cocktail.glassType}",
				style = cocktailInfo
		)

}

//fun abc(state: RandomCocktailState): String {
//		return if (state.cocktails.isEmpty()) {
//				state.cocktails.lastIndex.toString()
//		} else state.cocktails[0].name
//}