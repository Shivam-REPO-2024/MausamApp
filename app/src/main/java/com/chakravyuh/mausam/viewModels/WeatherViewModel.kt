package com.chakravyuh.mausam.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chakravyuh.mausam.ApiServices.WeatherRetrofitInstance
import com.chakravyuh.mausam.model.ForecastResponse
import com.chakravyuh.mausam.model.ForecastResponseState
import com.chakravyuh.mausam.model.LocationData
import com.chakravyuh.mausam.model.WeatherResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel(){

    private val apiService = WeatherRetrofitInstance.getApiService()

    private val _weatherResponseState = MutableStateFlow<WeatherResponseState?>(
        WeatherResponseState(
            isLoading = true,
            currentWeatherData = null,
            error = null
        )
    )

    val weatherResponseState : StateFlow<WeatherResponseState?> = _weatherResponseState

    private val _weatherForecastResponseState = MutableStateFlow<ForecastResponseState>(
        ForecastResponseState(
            isLoading = true,
            WeatherForecastData = null,
            error = null
        ))
    val weatherForecastResponseState : StateFlow<ForecastResponseState?> = _weatherForecastResponseState


    fun fetchWeatherForecast(locationData: LocationViewModel,
                             appId : String
                             ){

        viewModelScope.launch {

            try {
                val locationData = locationData.location.value
                if (locationData != null){
                    val response = apiService.getForecastWeatherReport(
                        locationData.latitude,
                        locationData.longitude,
                        appId
                    )

                    _weatherForecastResponseState.value= ForecastResponseState(
                        isLoading = false,
                        response,
                        error = null
                    )
                }



            }catch (e: Exception){
                _weatherForecastResponseState.value= ForecastResponseState(
                    isLoading = false,
                    null,
                    error = null
                )

            }
        }
    }


    fun fetchCurrentWeater(lat: Double,
                           lon: Double,
                           appId : String
                           ){
        _weatherResponseState.value = WeatherResponseState(
            isLoading = true,
            currentWeatherData = null,
            error = null

        )

       viewModelScope.launch {

           try {


               if ((lat != null) && (lon != null)){
                   val response = apiService.getCurrentWeatherReport(
                       lat,
                       lon,
                       appId
                   )
                   _weatherResponseState.value = WeatherResponseState(
                       isLoading = false,
                       currentWeatherData = response,
                       error = null

                   )
               }



           }catch (e: Exception){
               _weatherResponseState.value = WeatherResponseState(
                   isLoading = false,
                   currentWeatherData = null,
                   error = e.message
               )


           }
       }
    }



}