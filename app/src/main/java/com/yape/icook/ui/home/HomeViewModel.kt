package com.yape.icook.ui.home

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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

    private val foodRecipesCacheResponse: MutableList<FoodRecipeResponse> = mutableListOf()

    fun loadFoodRecipes()= viewModelScope.launch  {
        val result = foodRecipeRepository.getFoodRecipes()
        when (result.apiStatus) {
            ApiStatus.SUCCESS -> {
                val foodRecipeResponseList: List<FoodRecipeResponse> = (result as? ResultApi.Success)?.data ?: emptyList()
                foodRecipesCacheResponse.clear()
                foodRecipesCacheResponse.addAll(foodRecipeResponseList)
                _homeUiState.value = _homeUiState.value.copy(
                    foodRecipeResponses = foodRecipeResponseList
                )
            }
            ApiStatus.ERROR -> {
                    _homeUiState.value = _homeUiState.value.copy(
                    foodRecipeResponses = emptyList(),
                )
            }
            ApiStatus.LOADING -> TODO()
        }
    }

    fun onQueryChange(query: String) {
        textToSearch = query
        filterFoodRecipes(query)
    }

    private fun filterFoodRecipes(query: String) = viewModelScope.launch {
        _homeUiState.value = _homeUiState.value.copy(
            foodRecipeResponses = foodRecipesCacheResponse.filter { foodRecipe ->
                foodRecipe.name.lowercase().contains(query.lowercase())
                        || foodRecipe.ingredients.lowercase().contains(query.lowercase())
            }
        )
    }
}