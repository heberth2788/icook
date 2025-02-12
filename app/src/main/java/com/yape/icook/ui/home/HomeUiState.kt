package com.yape.icook.ui.home

import com.yape.icook.ui.domainentity.FoodRecipe

data class HomeUiState(
    val foodRecipes: List<FoodRecipe> = emptyList(),
)