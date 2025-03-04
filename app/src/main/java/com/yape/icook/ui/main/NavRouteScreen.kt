package com.yape.icook.ui.main

import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute

@Serializable
data class DetailScreenRoute(val foodRecipeId: Int)

@Serializable
data class MapScreenRoute(val foodRecipeId: Int)