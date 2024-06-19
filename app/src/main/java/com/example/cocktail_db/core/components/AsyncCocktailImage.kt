package com.example.cocktail_db.core.components

import androidx.compose.foundation.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage

@Composable
fun AsyncCocktailImage(
		imageUrl: String,
		contentDesc: String,
		modifier: Modifier = Modifier
) {

		SubcomposeAsyncImage(
				model = imageUrl,
				contentDescription = contentDesc
		) {
				val state = painter.state
				if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
						CircularProgressIndicator()
				} else {
						Image(
								painter = painter,
								contentDescription = contentDesc,
								contentScale = ContentScale.Crop,
								modifier = modifier
						)
				}
		}
}