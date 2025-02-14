package com.yape.icook.data.datasource

import com.yape.icook.data.entity.FoodRecipeResponse
import retrofit2.http.GET

interface FoodRecipeService {

    @GET("food-recipes")
    suspend fun getFoodRecipes(): List<FoodRecipeResponse>
}
