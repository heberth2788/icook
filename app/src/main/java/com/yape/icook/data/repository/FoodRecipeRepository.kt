package com.yape.icook.data.repository

import com.yape.icook.data.datasource.ResultApi
import com.yape.icook.data.entity.FoodRecipeResponse

interface FoodRecipeRepository {

    suspend fun getFoodRecipes(): ResultApi<List<FoodRecipeResponse>>
}