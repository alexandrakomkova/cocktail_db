package com.example.cocktail_db.presentation.onboarding_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cocktail_db.presentation.random_cocktail_screen.RandomCocktailScreen
import com.example.cocktail_db.presentation.random_cocktail_screen.RandomCocktailViewModel

@Composable
fun OnBoardScreen(
		viewModel: RandomCocktailViewModel
) {

		val onboardPages = onboardPagesList
		val currentPage = remember { mutableIntStateOf(0) }

		Column(
				modifier = Modifier.fillMaxSize()
		) {

				OnBoardImageView(
						modifier = Modifier
								.weight(1f)
								.fillMaxWidth(),
						currentPage = onboardPages[currentPage.intValue]
				)

				OnBoardDetails(
						modifier = Modifier
								.weight(1f)
								.padding(16.dp),
						currentPage = onboardPages[currentPage.intValue]
				)

				OnBoardNavButton(
						modifier = Modifier
								.align(Alignment.CenterHorizontally)
								.padding(top = 16.dp, bottom = 15.dp),
						currentPage = currentPage.intValue,
						noOfPages = onboardPages.size
				) {
						currentPage.intValue++
				}

				TabSelector(
						onboardPages = onboardPages,
						currentPage = currentPage.intValue
				) { index ->
						currentPage.intValue = index
				}
		}
}

@Composable
fun OnBoardImageView(modifier: Modifier = Modifier, currentPage: OnboardPage) {
		val imageRes = currentPage.imageRes
		Box(
				modifier = modifier.background(Color.Blue)
		) {
				Image(
						painter = painterResource(id = imageRes),
						contentDescription = null,
						modifier = Modifier.fillMaxSize(),
						contentScale = ContentScale.FillWidth
				)
				Box(modifier = Modifier
						.fillMaxSize()
						.align(Alignment.BottomCenter)
						.graphicsLayer { alpha = 0.6f }
						.background(
								Brush.verticalGradient(
										colorStops = arrayOf(
												Pair(0.8f, Color.Transparent), Pair(1f, Color.White)
										)
								)
						))
		}
}

@Composable
fun OnBoardDetails(
		modifier: Modifier = Modifier, currentPage: OnboardPage
) {
		Column(
				modifier = modifier,
				verticalArrangement = Arrangement.Bottom
		) {
				Text(
						text = currentPage.title,
						style = MaterialTheme.typography.displaySmall,
						textAlign = TextAlign.Center,
						modifier = Modifier.fillMaxWidth()
				)
				Spacer(modifier = Modifier.height(16.dp))
				Text(
						text = currentPage.description,
						style = MaterialTheme.typography.bodyMedium,
						textAlign = TextAlign.Center,
						modifier = Modifier.fillMaxWidth()
				)
		}
}

@Composable
fun OnBoardNavButton(
		modifier: Modifier = Modifier, currentPage: Int, noOfPages: Int, onNextClicked: () -> Unit
) {
		Button(
				onClick = {
						if (currentPage < noOfPages - 1) {
								onNextClicked()
						} else {

								// RandomCocktailScreen(viewModel = viewModel, state = viewModel.state)
						}
				},
				modifier = modifier
		) {
				Text(text = if (currentPage < noOfPages - 1) "Next" else "Get Started")
		}
}

@Composable
fun TabSelector(onboardPages: List<OnboardPage>, currentPage: Int, onTabSelected: (Int) -> Unit) {
		TabRow(
				selectedTabIndex = currentPage,
				modifier = Modifier
						.fillMaxWidth()
						.background(MaterialTheme.colorScheme.primary)

		) {
				onboardPages.forEachIndexed { index, _ ->
						Tab(selected = index == currentPage, onClick = {
								onTabSelected(index)
						}, modifier = Modifier.padding(16.dp), content = {
								Box(
										modifier = Modifier
												.size(8.dp)
												.background(
														color = if (index == currentPage) MaterialTheme.colorScheme.onPrimary
														else Color.LightGray, shape = RoundedCornerShape(4.dp)
												)
								)
						})
				}
		}
}