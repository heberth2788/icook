package com.yape.icook.data.repository

import com.yape.icook.data.datasource.FoodRecipeDataSource
import com.yape.icook.ui.domainentity.FoodRecipe
import javax.inject.Inject

class FoodRecipeRepository @Inject constructor(
    private val foodRecipeDataSource: FoodRecipeDataSource,
) {

    suspend fun getFoodRecipes(): List<FoodRecipe> = foodRecipeDataSource.getFoodRecipes()
}