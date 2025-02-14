package com.yape.icook.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yape.icook.data.datasource.ApiStatus
import com.yape.icook.data.datasource.ResultApi
import com.yape.icook.data.repository.FoodRecipeRepository
import com.yape.icook.data.entity.FoodRecipeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val foodRecipeRepository: FoodRecipeRepository,
) : ViewModel() {

    /**
     * Food recipe to show in the detail screen
     */
    var foodRecipeResponse: FoodRecipeResponse by mutableStateOf(FoodRecipeResponse())
        private set

    fun loadFoodRecipe(foodRecipeId: Int)= viewModelScope.launch  {
        val result = foodRecipeRepository.getFoodRecipes()
        when (result.apiStatus) {
            ApiStatus.SUCCESS -> {
                val foodRecipeResponseList: List<FoodRecipeResponse> = (result as? ResultApi.Success)?.data ?: emptyList()
                foodRecipeResponse = foodRecipeResponseList
                    .find { it.id == foodRecipeId }
                    ?: FoodRecipeResponse()
            }
            ApiStatus.ERROR -> {
                foodRecipeResponse = FoodRecipeResponse()
            }
            ApiStatus.LOADING -> TODO()
        }
    }
}