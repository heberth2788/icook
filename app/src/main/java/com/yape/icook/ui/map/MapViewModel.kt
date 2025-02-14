package com.yape.icook.ui.map

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
class MapViewModel @Inject constructor(
    private val foodRecipeRepository: FoodRecipeRepository,
) : ViewModel() {

    /**
     * Food recipe to show in the map screen
     */
    var foodRecipeResponse: FoodRecipeResponse by mutableStateOf(FoodRecipeResponse())
        private set

    /**
     * For google maps state
     */
//    var markerState: MarkerState by mutableStateOf(MarkerState(position = LatLng(0.0, 0.0)))
//        private set
//    var cameraPositionState: CameraPositionState by mutableStateOf(CameraPositionState())
//        private set

//    val cameraPositionState: CameraPositionState = mutableStateOf(CameraPositionState()) {

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