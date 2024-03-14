package com.example.cocktail_db.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApp: Application() {
		override fun onCreate() {
				super.onCreate()

				startKoin {
						androidContext(this@KoinApp)
						modules(listOf(appModule, dataModule, domainModule, networkModule))
				}
		}
}