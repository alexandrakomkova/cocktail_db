package com.example.cocktail_db

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import com.example.cocktail_db.feature.CocktailDbApp
import com.example.cocktail_db.ui.theme.Cocktail_dbTheme

class MainActivity : ComponentActivity() {

		@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
		override fun onCreate(savedInstanceState: Bundle?) {
				enableEdgeToEdge()
				super.onCreate(savedInstanceState)
				setContent {
						Cocktail_dbTheme {
								CocktailDbApp()
						}
				}
		}
}



