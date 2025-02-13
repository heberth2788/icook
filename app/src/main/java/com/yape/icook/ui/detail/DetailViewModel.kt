package com.yape.icook.ui.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yape.icook.data.repository.FoodRecipeRepository
import com.yape.icook.mock.mockFoodRecipe
import com.yape.icook.ui.domainentity.FoodRecipe
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
    var foodRecipe: FoodRecipe by mutableStateOf(FoodRecipe())
        private set

    fun loadFoodRecipe(foodRecipeId: Int)= viewModelScope.launch  {
        foodRecipe = foodRecipeRepository.getFoodRecipes()
            .find { it.id == foodRecipeId }
            ?: FoodRecipe()
    }
}