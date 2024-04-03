package com.example.cocktail_db.data.remote.dto.cocktails_by_category

import com.example.cocktail_db.domain.model.ShortInfoCocktail

data class ShortInfoCocktailDto(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
)

fun ShortInfoCocktailDto.toShortInfoCocktail(): ShortInfoCocktail {
    return ShortInfoCocktail(
        id = idDrink,
        name = strDrink,
        image = strDrinkThumb
    )
}