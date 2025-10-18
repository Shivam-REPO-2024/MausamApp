package com.chakravyuh.mausam.ApiServices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRetrofitInstance {

    // URl ; https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
    // BAse URl ; https://api.openweathermap.org/data/2.5/
    // apikey : 9cef30487be97e49da538604a4017e7d

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService(): WeatherApiService = getInstance().create(WeatherApiService::class.java)


}