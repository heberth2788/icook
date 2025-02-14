package com.yape.icook.data.datasource

import android.util.Log
import com.yape.icook.data.entity.FoodRecipeResponse
import javax.inject.Inject

class FoodRecipeDataSource @Inject constructor(
    private val foodRecipeService: FoodRecipeService,
) {

    suspend fun getFoodRecipes(): ResultApi<List<FoodRecipeResponse>> = try {
        ResultApi.Success(data = foodRecipeService.getFoodRecipes())
    } catch (e: Exception) {
        Log.d("FoodRecipeDataSource", "getFoodRecipes exception: ${ e.message }")
        ResultApi.Error(exception = e)
    }
}