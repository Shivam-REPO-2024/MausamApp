package com.chakravyuh.mausam.model

data class WeatherResponseState(
    val isLoading: Boolean = true,
    val currentWeatherData: CurrentWeatherResponse? =null,
    val error: String? = null
)