package com.yape.icook.data.entity

data class FoodRecipeResponse(
    val id: Int = 0,
    val name: String = "",
    val imageUrl: String = "",
    val desc: String = "",
    val ingredients: String = "",
    val preparation: String = "",
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val alt: Double = 0.0,
)
