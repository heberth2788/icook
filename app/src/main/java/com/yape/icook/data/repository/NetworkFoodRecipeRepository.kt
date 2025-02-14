package com.yape.icook.data.repository

import com.yape.icook.data.datasource.FoodRecipeDataSource
import com.yape.icook.data.datasource.ResultApi
import com.yape.icook.data.entity.FoodRecipeResponse
import javax.inject.Inject

class NetworkFoodRecipeRepository @Inject constructor(
    private val foodRecipeDataSource: FoodRecipeDataSource,
) : FoodRecipeRepository {

    override suspend fun getFoodRecipes(): ResultApi<List<FoodRecipeResponse>> = foodRecipeDataSource.getFoodRecipes()
}