package com.yape.icook.data.entity

data class FoodRecipeResponse(
    val id: Int,
    val name: String,
    val desc: String,
    val state: Boolean,
    val lat: Double,
    val lon: Double,
    val alt: Double
)
