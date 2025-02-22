package com.yape.icook.data.repository

import com.yape.icook.data.datasource.ResultApi
import com.yape.icook.data.entity.FoodRecipeResponse
import com.yape.icook.mock.createMockData
import com.yape.icook.ui.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @Test
    fun getFoodRecipeList_error() = runTest {
        // Arrange
        val homeViewModel = HomeViewModel(FakeNetworkFoodRecipeRepositoryError())
        val expectedRecipeCount = 0

        // Act
        homeViewModel.loadFoodRecipes()

        // Assert
        val emptyList = homeViewModel.homeUiState.value.foodRecipeResponses
        assert(emptyList.size == expectedRecipeCount)
    }

    @Test
    fun getFoodRecipeList_success() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        try {
            // Arrange
            val homeViewModel = HomeViewModel(FakeNetworkFoodRecipeRepositorySuccess())
            val expectedRecipeCount = 26

            // Act
            homeViewModel.loadFoodRecipes()

            // Assert
            val foodRecipes = homeViewModel.homeUiState.value.foodRecipeResponses
            assert(foodRecipes.size == expectedRecipeCount)
        } finally {
            Dispatchers.resetMain()
        }
    }
}

class FakeNetworkFoodRecipeRepositoryError : FoodRecipeRepository {

    override suspend fun getFoodRecipes(): ResultApi<List<FoodRecipeResponse>> = ResultApi
        .Error(Exception("Server error"))
}

class FakeNetworkFoodRecipeRepositorySuccess : FoodRecipeRepository {

    override suspend fun getFoodRecipes(): ResultApi<List<FoodRecipeResponse>> = ResultApi
        .Success(data = createMockData())
}