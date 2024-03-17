package com.example.cocktail_db.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import com.example.cocktail_db.domain.model.Cocktail
import com.example.cocktail_db.ui.theme.Purple40
import com.example.cocktail_db.ui.theme.cocktailId
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

		//img
		Box { CocktailImage(cocktail.image) }

		// all info
		Box(
				modifier = Modifier.fillMaxWidth().padding(top = 15.dp)
		) {
				Column(
						modifier = Modifier.padding(horizontal = 15.dp)
				) {
						// id + name
						OverlayText(cocktail.id.toString(), cocktail.name)

						cocktail.cocktailType?.let {
								Text(
										text = it,
										style = TextStyle(
												fontSize = 16.sp,
												fontFamily = FontFamily.Monospace,
												color = Color.Gray,
												textAlign = TextAlign.Left,
										),
										modifier = Modifier.padding(bottom = 20.dp)
								)
						}
						Text(
								text = "Category: ${cocktail.category}",
								style = cocktailInfo
						)
						Divider(modifier = Modifier.width(15.dp).padding(vertical = 10.dp), thickness = 1.dp, color = Color.Gray)
						Text(
								text = "Glass type: ${cocktail.glassType}",
								style = cocktailInfo
						)
						Divider(modifier = Modifier.width(15.dp).padding(vertical = 10.dp), thickness = 1.dp, color = Color.Gray)
						Text(
								text = "Glass type: ${cocktail.glassType}",
								style = cocktailInfo
						)
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
				Divider(modifier = Modifier
						.height(48.dp)
						.width(5.dp)
						.padding(vertical = 10.dp),
						thickness = 1.dp,
						color = Purple40)

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
								.width(200.dp)
								.padding(top = 25.dp)
				)
		}
}