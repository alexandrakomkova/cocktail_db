package com.example.cocktail_db

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.cocktail_db.presentation.random_cocktail_screen.RandomCocktailScreen
import com.example.cocktail_db.presentation.random_cocktail_screen.RandomCocktailViewModel
import com.example.cocktail_db.ui.theme.Cocktail_dbTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {


		@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
		override fun onCreate(savedInstanceState: Bundle?) {
				enableEdgeToEdge()
				super.onCreate(savedInstanceState)
				setContent {
						Cocktail_dbTheme {
								Surface(
										modifier = Modifier.fillMaxSize(),
										color = MaterialTheme.colorScheme.background
								) {

										val viewModel: RandomCocktailViewModel = koinViewModel()
										RandomCocktailScreen(viewModel = viewModel, state = viewModel.state.value)

//										OnBoardScreen(viewModel)

								}
						}
				}
		}


}


