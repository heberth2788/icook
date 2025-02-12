package com.yape.icook.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.yape.icook.mock.createMockData
import com.yape.icook.ui.domainentity.FoodRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

//@HiltViewModel
//class HomeViewModel @Inject constructor(
//    // TODO: Add repositories
//): ViewModel() {

class HomeViewModel : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(
        HomeUiState(foodRecipes = createMockData())
    )
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

//    private val _textToSearch: MutableStateFlow<String> = MutableStateFlow("")
//    val textToSearch: StateFlow<String> = _textToSearch.asStateFlow()
    var textToSearch: String by mutableStateOf("")
        private set

//    var selectedIdFoodRecipe: Int by mutableIntStateOf(-1)
//        private set

//    fun onClickFoodRecipe(foodRecipeId: Int) {
//        selectedIdFoodRecipe = foodRecipeId
//    }

    fun onQueryChange(query: String) {
//        _textToSearch.value = query
        textToSearch = query
        filterFoodRecipes(query)
    }

    private fun filterFoodRecipes(query: String) {
        _homeUiState.value = _homeUiState.value.copy(
            foodRecipes = createMockData().filter { foodRecipe ->
                foodRecipe.name.lowercase().contains(query.lowercase())
                || foodRecipe.ingredients.lowercase().contains(query.lowercase())
            }
        )
    }
}