package com.yape.icook.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.yape.icook.ui.detail.DetailScreen
import com.yape.icook.ui.home.HomeScreen
import com.yape.icook.ui.map.MapScreen

@Composable
fun MainNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeScreenRoute,
    ) {

        // For HomeScreen navigation
        composable<HomeScreenRoute> {
            HomeScreen { foodRecipeId: Int ->
                navController.navigate(route = DetailScreenRoute(foodRecipeId))
            }
        }

        // For DetailScreen navigation
        composable<DetailScreenRoute> { stackEntry ->
            val detailScreenRoute: DetailScreenRoute = stackEntry.toRoute()
            DetailScreen(
                foodRecipeId = detailScreenRoute.foodRecipeId,
                navigateBack = {
                    navController.navigateUp()
                },
            ) {
                navController.navigate(route = MapScreenRoute(detailScreenRoute.foodRecipeId))
            }
        }

        // For MapScreen navigation
        composable<MapScreenRoute> { stackEntry ->
            val mapScreenRoute: MapScreenRoute = stackEntry.toRoute()
            MapScreen(
                foodRecipeId = mapScreenRoute.foodRecipeId,
            ) {
                navController.navigateUp()
            }
        }
    }
}