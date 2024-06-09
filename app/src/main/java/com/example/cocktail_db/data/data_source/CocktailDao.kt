package com.example.cocktail_db.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.cocktail_db.domain.model.FavCocktail
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDao {
		@Query("select * from favcocktail")
		fun getFavourites(): Flow<List<FavCocktail>>

		@Query("select * from favcocktail where id = :id")
		suspend fun getFavCocktailById(id: Int): FavCocktail?
		@Insert(onConflict = REPLACE)
		suspend fun insertFavCocktail(cocktail: FavCocktail)
		@Delete
		suspend fun deleteFavCocktail(cocktail: FavCocktail)
}