package com.example.cocktail_db.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cocktail_db.domain.model.FavCocktail


@Database(entities = [FavCocktail::class], version = 1)
abstract class FavCocktailDatabase: RoomDatabase() {
		abstract val favCocktailDao: CocktailDao

		companion object {
				const val DATABASE_NAME = "favourite_cocktails_db"
		}
}