package com.example.cocktail_db.data.remote.dto.search_cocktail_by_ame

import com.example.cocktail_db.data.remote.dto.CocktailDto
import com.squareup.moshi.Json

data class SearchCocktailsByNameDto (
		@field:Json(name="drinks")
		val cocktails: List<CocktailDto>
)