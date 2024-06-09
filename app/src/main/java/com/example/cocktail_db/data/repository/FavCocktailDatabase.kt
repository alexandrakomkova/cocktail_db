package com.example.cocktail_db.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cocktail_db.data.data_source.CocktailDao
import com.example.cocktail_db.domain.model.FavCocktail


@Database(
		entities = [FavCocktail::class],
		version = 1
)


abstract class FavCocktailDatabase: RoomDatabase() {
		abstract val cocktailDao: CocktailDao

		companion object {
				const val DATABASE_NAME = "favourite_cocktails_db"
		}
}