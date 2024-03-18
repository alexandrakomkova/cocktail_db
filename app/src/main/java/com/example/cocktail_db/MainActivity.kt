package com.example.cocktail_db

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cocktail_db.presentation.RandomCocktailScreen
import com.example.cocktail_db.presentation.RandomCocktailViewModel
import com.example.cocktail_db.ui.theme.Cocktail_dbTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
		@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
		override fun onCreate(savedInstanceState: Bundle?) {
				super.onCreate(savedInstanceState)
				setContent {
						Cocktail_dbTheme {
								// A surface container using the 'background' color from the theme
								Surface(
										modifier = Modifier.fillMaxSize(),
										color = MaterialTheme.colorScheme.background
								) {
										val viewModel: RandomCocktailViewModel = koinViewModel()

										RandomCocktailScreen(viewModel = viewModel, state = viewModel.state.value)
								}
						}
				}
		}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
		Text(
				text = "Hello $name!",
				modifier = modifier
		)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
		Cocktail_dbTheme {
				Greeting("Android")
		}
}