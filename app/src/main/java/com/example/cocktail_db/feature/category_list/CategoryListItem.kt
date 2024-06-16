package com.example.cocktail_db.feature.category_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cocktail_db.domain.model.Category
import com.example.cocktail_db.ui.theme.cocktailInfoBlack

@Composable
fun CategoryListItem(
		category: Category,
		onCategoryClick: (Category) -> Unit
) {
		Row(
				modifier = Modifier
						.fillMaxWidth()
						.clickable { onCategoryClick(category) }
						.padding(20.dp)
		) {
				Text(
						text = category.categoryName,
						style = cocktailInfoBlack
				)

		}


}