package com.yape.icook.data.datasource

enum class ApiStatus {
    SUCCESS,
    ERROR,
    LOADING
}

sealed class ResultApi<out T : Any?>(val apiStatus: ApiStatus) {

    data class Success<T>(
        val data: T,
    ) : ResultApi<T>(apiStatus = ApiStatus.SUCCESS)

    data class Error(
        val exception: Exception,
    ) : ResultApi<Nothing>(apiStatus = ApiStatus.ERROR)

    data object Loading : ResultApi<Nothing>(apiStatus = ApiStatus.LOADING)
}