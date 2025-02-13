package com.yape.icook.data.datasource

import com.yape.icook.ui.domainentity.FoodRecipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodRecipeDataSource @Inject constructor(
    private val foodRecipeService: FoodRecipeService,
) {

    suspend fun getFoodRecipes(): List<FoodRecipe> = foodRecipeService.getFoodRecipes()
}