package com.example.cocktail_db.di

import android.app.Application
import androidx.room.Room
import com.example.cocktail_db.data.data_source.CocktailDao
import com.example.cocktail_db.data.data_source.FavCocktailDatabase
import com.example.cocktail_db.data.repository.CocktailDbRepositoryImpl
import com.example.cocktail_db.data.repository.FavCocktailRepositoryImpl
import com.example.cocktail_db.domain.repository.CocktailDbRepository
import com.example.cocktail_db.domain.repository.FavCocktailRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModuleCocktailDb = module {

		single {
				CocktailDbRepositoryImpl (api = get())
		} bind CocktailDbRepository::class


//		single {
//				FavCocktailRepositoryImpl(database = get())
//		} bind FavCocktailRepository::class

		single<FavCocktailRepository> { FavCocktailRepositoryImpl(get()) }

		single { provideDataBase(get()) }
		single { provideDao(get()) }
}

fun provideDataBase(application: Application): FavCocktailDatabase =
		Room.databaseBuilder(
				application,
				FavCocktailDatabase::class.java,
				FavCocktailDatabase.DATABASE_NAME
		).fallbackToDestructiveMigration().build()
fun provideDao(favCocktailDataBase: FavCocktailDatabase): CocktailDao = favCocktailDataBase.getFavCocktailDao()