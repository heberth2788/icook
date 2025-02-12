package com.yape.icook.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yape.icook.ui.detail.DetailScreen
import com.yape.icook.ui.home.HomeScreen
import com.yape.icook.ui.map.MapScreen

@Composable
fun MainNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
    ) {

        // For HomeScreen navigation
        composable(
            route = "home",
        ) {
            HomeScreen(navHostController = navController)
        }

        // For DetailScreen navigation
        composable(
            route = "detail/{foodRecipeId}",
            arguments = listOf(
                navArgument("foodRecipeId") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) {
            DetailScreen(
                navHostController = navController,
                foodRecipeId = it.arguments?.getInt("foodRecipeId") ?: 0,
            )
        }

        // For MapScreen navigation
        composable(
            route = "map/{foodRecipeId}",
            arguments = listOf(
                navArgument("foodRecipeId") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) {
            MapScreen(
                navHostController = navController,
                foodRecipeId = it.arguments?.getInt("foodRecipeId") ?: 0,
            )
        }
    }
}