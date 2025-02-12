package com.yape.icook.ui.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.yape.icook.mock.mockFoodRecipe
import com.yape.icook.ui.domainentity.FoodRecipe

class DetailViewModel : ViewModel() {

    /**
     * Food recipe to show in the detail screen
     */
    var foodRecipe: FoodRecipe by mutableStateOf(mockFoodRecipe)
        private set

    /**
     * Go to the previous screen
     */
//    fun onClickBack() {
//        Log.d("DetailViewModel", "onClickBack")
//    }

    /**
     * Go to the map screen
     */
//    fun onClickMap() {
//        Log.d("DetailViewModel", "onClickMap")
//    }
}