package com.example.cocktail_db.di

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresExtension
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApp: Application() {
		@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
		override fun onCreate() {
				super.onCreate()

				startKoin {
						androidContext(this@KoinApp)

						modules(
								listOf(
									appModule,
									dataModuleCocktailDb, // dataModuleFavCocktail,
									domainModuleCocktailDb, //domainModuleFavCocktails,
									networkModule
								)
						)
				}
		}
}