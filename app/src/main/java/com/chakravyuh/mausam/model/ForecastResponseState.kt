package com.chakravyuh.mausam.model

data class ForecastResponseState(
    val isLoading: Boolean = true,
    val WeatherForecastData: ForecastResponse? =null,
    val error: String? = null
)
