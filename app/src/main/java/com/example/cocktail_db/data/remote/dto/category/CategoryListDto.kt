package com.example.cocktail_db.data.remote.dto.category

import com.squareup.moshi.Json

data class CategoryListDto(

    @Json(name="drinks")
    val categories: List<CategoryDto>
)