package com.yape.icook.data.datasource

import com.yape.icook.data.repository.FoodRecipeRepository
import com.yape.icook.data.repository.NetworkFoodRecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Web service URL
    private const val URL = "http://demo6946172.mockable.io/"

    @Provides
    @Singleton
    fun provideFoodRecipeService(
        retrofit: Retrofit,
    ): FoodRecipeService = retrofit.create(FoodRecipeService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideFoodRecipeRepository(
        foodRecipeDataSource: FoodRecipeDataSource,
    ): FoodRecipeRepository = NetworkFoodRecipeRepository(foodRecipeDataSource)

    @Provides
    @Singleton
    fun provideFoodRecipeDataSource(
        foodRecipeService: FoodRecipeService,
    ): FoodRecipeDataSource =
        FoodRecipeDataSource(foodRecipeService)
}