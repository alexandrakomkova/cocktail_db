package com.example.cocktail_db.presentation.onboarding_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cocktail_db.ui.theme.Purple40
import com.example.cocktail_db.ui.theme.cocktailInfoBlack
import com.example.cocktail_db.ui.theme.cocktailName


@Composable
fun OnboardingScreen(
		onOnboarded: () -> Unit
) {

		val onboardPages = onboardPagesList
		val currentPage = remember { mutableIntStateOf(0) }

		Column(
				modifier = Modifier
						.fillMaxSize()
						.background(Color.White)
		) {

				OnBoardImageView(
						modifier = Modifier
								.weight(4f)
								.padding(horizontal = 30.dp, vertical = 30.dp)
								.align(Alignment.CenterHorizontally)
								.clip(RoundedCornerShape(15.dp)),
						currentPage = onboardPages[currentPage.intValue]
				)

				OnBoardDetails(
						modifier = Modifier
								.weight(3f)
								.padding(16.dp),
						currentPage = onboardPages[currentPage.intValue]
				)

				OnBoardNavButton(
						modifier = Modifier
								.weight(1f)
								.align(Alignment.CenterHorizontally)
								.padding(bottom = 15.dp),
						currentPage = currentPage.intValue,
						noOfPages = onboardPages.size,
						onNextClicked = { currentPage.intValue++ },
						onOnboarded = onOnboarded
				)

				/* TabSelector(
						onboardPages = onboardPages,
						currentPage = currentPage.intValue
				) { index ->
						currentPage.intValue = index
				}*/
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

		) {
				Text(
						text = currentPage.title,
						style = cocktailName,
						textAlign = TextAlign.Center,
						modifier = Modifier.fillMaxWidth()
				)
				Spacer(modifier = Modifier.height(16.dp))
				Text(
						text = currentPage.description,
						style = cocktailInfoBlack,
						textAlign = TextAlign.Center,
						modifier = Modifier.fillMaxWidth()
				)
		}
}

@Composable
fun OnBoardNavButton(
		modifier: Modifier = Modifier,
		currentPage: Int,
		noOfPages: Int,
		onNextClicked: () -> Unit,
		onOnboarded: () -> Unit
) {
		Button(
				onClick = {
						if (currentPage < noOfPages - 1) {
								onNextClicked()
						} else {
								onOnboarded()
						}
				},
				modifier = modifier.width(150.dp).height(60.dp).padding(bottom = 45.dp),
				colors = ButtonDefaults.buttonColors(
						backgroundColor = Purple40
				),
				shape = RoundedCornerShape(25.dp)
		) {
				Text(
						text = if (currentPage < noOfPages - 1) "Next" else "Get Started",
						style = cocktailInfoBlack,
						color = Color.White
				)
		}
}

@Composable
fun TabSelector(onboardPages: List<OnboardPage>, currentPage: Int, onTabSelected: (Int) -> Unit) {
		TabRow(
				selectedTabIndex = currentPage,
				modifier = Modifier
						.fillMaxWidth()
						.padding(bottom = 55.dp),
				contentColor = MaterialTheme.colorScheme.onPrimary,
				backgroundColor = Purple40
		) {
				onboardPages.forEachIndexed { index, _ ->
						Tab(
								selected = index == currentPage,
								onClick = { onTabSelected(index) },
								modifier = Modifier.padding(16.dp),
								content = {
										Box(
												modifier = Modifier
														.size(8.dp)
														.background(
																color = if (index == currentPage) MaterialTheme.colorScheme.onPrimary
																else Color.LightGray,
																shape = RoundedCornerShape(4.dp)
														)
										)
								}
						)
				}
		}
}