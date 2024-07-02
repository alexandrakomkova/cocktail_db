package com.example.cocktail_db.data.remote.dto.search_cocktail_by_ame

import com.example.cocktail_db.data.remote.dto.Drink

data class SearchCocktailsByName(
		val drinks: List<Drink>
)