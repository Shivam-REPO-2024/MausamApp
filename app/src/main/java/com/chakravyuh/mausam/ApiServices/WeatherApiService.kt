package com.chakravyuh.mausam.ApiServices

import com.chakravyuh.mausam.model.CurrentWeatherResponse
import com.chakravyuh.mausam.model.ForecastResponse
import com.chakravyuh.mausam.model.LocationSearchResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherReport(
        @Query("lat") latitude:Double,
        @Query("lon") longitude:Double,
        @Query("appid") apiKey:String,

    ): CurrentWeatherResponse


    //BAse URL :https://api.openweathermap.org/geo/1.0/direct?q=london,us&limit=1&appid=9cef30487be97e49da538604a4017e7d

    @GET("geo/1.0/direct")
    suspend fun getCoordinates(
        @Query("q") cityWithCountry:String,
        @Query("limit") limit: Int =1,
        @Query("appid") apiKey:String,

        ): List<LocationSearchResponseItem>
    // Base url : api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={API key}

    @GET("data/2.5/forecast")
    suspend fun getForecastWeatherReport(
        @Query("lat") latitude:Double,
        @Query("lon") longitude:Double,
        @Query("appid") apiKey:String,

    ): ForecastResponse

}