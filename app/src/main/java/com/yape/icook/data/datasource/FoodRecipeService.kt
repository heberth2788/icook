package com.yape.icook.data.datasource

import com.yape.icook.ui.domainentity.FoodRecipe
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface FoodRecipeService {

    @GET("food-recipes")
    suspend fun getFoodRecipes(): List<FoodRecipe>
}
