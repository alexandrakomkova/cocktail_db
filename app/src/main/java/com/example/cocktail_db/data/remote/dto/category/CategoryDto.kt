package com.example.cocktail_db.data.remote.dto.category

import com.example.cocktail_db.domain.model.Category

data class CategoryDto(
    val strCategory: String
)

fun CategoryDto.toCategory(): Category {
    return Category(categoryName = strCategory)
}