package com.yape.icook.ui.home

import androidx.lifecycle.ViewModel
import com.yape.icook.mock.createMockData
import com.yape.icook.ui.domainentity.FoodRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

//@HiltViewModel
//class HomeViewModel @Inject constructor(
//    // TODO: Add repositories
//): ViewModel() {

class HomeViewModel : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(
        HomeUiState(foodRecipes = createMockData())
    )
    val homeUiState: StateFlow<HomeUiState> = _homeUiState
}