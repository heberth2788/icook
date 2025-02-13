package com.yape.icook.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yape.icook.data.repository.FoodRecipeRepository
import com.yape.icook.ui.domainentity.FoodRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val foodRecipeRepository: FoodRecipeRepository,
) : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(
//        HomeUiState(foodRecipes = createMockData())
        HomeUiState()
    )
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    var textToSearch: String by mutableStateOf("")
        private set

    private var foodRecipesCache: MutableList<FoodRecipe> = mutableListOf()

    fun loadFoodRecipes()= viewModelScope.launch  {
        foodRecipesCache.clear()
        foodRecipesCache.addAll(foodRecipeRepository.getFoodRecipes())
        _homeUiState.value = _homeUiState.value.copy(
            foodRecipes = foodRecipeRepository.getFoodRecipes()
        )
    }

    fun onQueryChange(query: String) {
        textToSearch = query
        filterFoodRecipes(query)
    }

    private fun filterFoodRecipes(query: String) = viewModelScope.launch {
        _homeUiState.value = _homeUiState.value.copy(
            foodRecipes = foodRecipesCache.filter { foodRecipe ->
                foodRecipe.name.lowercase().contains(query.lowercase())
                        || foodRecipe.ingredients.lowercase().contains(query.lowercase())
            }
        )
    }
}