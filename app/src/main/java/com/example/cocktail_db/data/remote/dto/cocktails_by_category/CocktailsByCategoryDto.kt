package com.example.cocktail_db.data.remote.dto.cocktails_by_category

import com.squareup.moshi.Json

data class CocktailsByCategoryDto(
    @field:Json(name="drinks")
    val cocktailsByCategory: List<ShortInfoCocktailDto>
)