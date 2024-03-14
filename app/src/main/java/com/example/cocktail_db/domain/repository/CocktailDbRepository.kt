package com.example.cocktail_db.domain.repository

import com.example.cocktail_db.data.remote.dto.Drink

interface CocktailDbRepository {
		suspend fun getCocktailDbRandomCocktail(): List<Drink>
}