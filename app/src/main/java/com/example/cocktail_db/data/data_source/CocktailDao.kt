package com.example.cocktail_db.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.cocktail_db.domain.model.Cocktail
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDao {
		@Query("select * from favourite_cocktails")
		fun getFavourites(): Flow<List<Cocktail>>

		@Query("select * from favourite_cocktails where id = :id")
		suspend fun getFavCocktailById(id: Int): Cocktail?
		@Insert(onConflict = REPLACE)
		suspend fun insertFavCocktail(cocktail: Cocktail)
		@Delete
		suspend fun deleteFavCocktail(cocktail: Cocktail)
}