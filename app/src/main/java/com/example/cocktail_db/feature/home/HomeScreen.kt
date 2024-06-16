package com.example.cocktail_db.feature.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cocktail_db.R
import com.example.cocktail_db.feature.category_list.CategoryListScreen
import com.example.cocktail_db.feature.favourite_cocktail.FavouriteListScreen
import com.example.cocktail_db.ui.theme.Purple40
import kotlinx.coroutines.launch

enum class CocktailDbPage(
		@StringRes val titleResId: Int,
		@DrawableRes val drawableResId: Int
) {
		CATEGORY_LIST(R.string.category_list_title, R.drawable.baseline_format_list_bulleted_24),
		HOME(R.string.home_title, R.drawable.baseline_home_24),
		FAVOURITE_LIST(R.string.favourite_list_title, R.drawable.baseline_favorite_24)
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
		modifier: Modifier = Modifier,
		pages: Array<CocktailDbPage> = CocktailDbPage.entries.toTypedArray()
) {
		val pagerState = rememberPagerState(pageCount = { pages.size })

		Scaffold(
				modifier = modifier,
				topBar = {

				}
		) { contentPadding ->

				HomePagerScreen(
						pagerState = pagerState,
						pages = pages,
						Modifier.padding(top = contentPadding.calculateTopPadding())
				)

		}

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePagerScreen(
		pagerState: PagerState,
		pages: Array<CocktailDbPage>,
		modifier: Modifier = Modifier,
) {
		Column(modifier) {
				val coroutineScope = rememberCoroutineScope()

				// tab row
				TabRow(selectedTabIndex = pagerState.currentPage) {
						pages.forEachIndexed { index, page ->
								val title = stringResource(id = page.titleResId)

								Tab(
										selected = pagerState.currentPage == index,
										onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
										text = { Text(text = title) },
										icon = {
												Icon(
														painter = painterResource(id = page.drawableResId),
														contentDescription = title
												)
										},
										selectedContentColor = Purple40,
										unselectedContentColor = Color.Black
								)
						}
				}

				// pages
				HorizontalPager(
						modifier = Modifier.background(Color.White),
						state = pagerState,
						verticalAlignment = Alignment.Top
				) { index ->
						when(pages[index]) {
								CocktailDbPage.CATEGORY_LIST -> CategoryListScreen(modifier = Modifier.fillMaxSize())
								CocktailDbPage.HOME -> CachedCocktailListScreen(modifier = Modifier.fillMaxSize())
								CocktailDbPage.FAVOURITE_LIST -> FavouriteListScreen(modifier = Modifier.fillMaxSize())
						}

				}
		}

}