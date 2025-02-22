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

// TODO: use @serializable objects/classes to define route destinations for each screen. See: https://developer.android.com/guide/navigation/design#compose-minimal

@Composable
fun MainNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        // TODO: DO NOT pass the NavHostController object to the below composable function
        navController = navController,
        startDestination = "home",
    ) {

        // For HomeScreen navigation
        composable(
            route = "home",
        ) {
            HomeScreen(navHostController = navController) //TODO: refactor, don't pass NavHostController
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
                navHostController = navController, //TODO: refactor, don't pass NavHostController
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
                navHostController = navController, //TODO: refactor, don't pass NavHostController
                foodRecipeId = it.arguments?.getInt("foodRecipeId") ?: 0,
            )
        }
    }
}