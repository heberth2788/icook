package com.yape.icook.data.repository

import com.yape.icook.data.datasource.FoodRecipeDataSource
import com.yape.icook.data.datasource.ResultApi
import com.yape.icook.data.entity.FoodRecipeResponse
import javax.inject.Inject

class FoodRecipeRepository @Inject constructor(
    private val foodRecipeDataSource: FoodRecipeDataSource,
) {

    suspend fun getFoodRecipes(): ResultApi<List<FoodRecipeResponse>> = foodRecipeDataSource.getFoodRecipes()
}