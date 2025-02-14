package com.yape.icook.ui.home

import com.yape.icook.data.entity.FoodRecipeResponse

data class HomeUiState(
    val foodRecipeResponses: List<FoodRecipeResponse> = emptyList(),
)