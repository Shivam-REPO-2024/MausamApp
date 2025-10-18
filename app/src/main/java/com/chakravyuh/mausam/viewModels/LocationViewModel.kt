package com.chakravyuh.mausam.viewModels

import android.util.Log
import android.view.View
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chakravyuh.mausam.ApiServices.WeatherRetrofitInstance
import com.chakravyuh.mausam.location.LocationUtils
import com.chakravyuh.mausam.model.LocationData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {

    private val ApiService = WeatherRetrofitInstance.getApiService()
    private val _location = MutableStateFlow<LocationData?>(null)
    val location : StateFlow<LocationData?> = _location
    private val _address = MutableStateFlow<String?>(null)
    val address : StateFlow<String?> = _address

    private val _isGPSLocation = MutableStateFlow(true)
    val isGPSLocation: StateFlow<Boolean> = _isGPSLocation

    fun setGPSLocation(value: Boolean) {
        _isGPSLocation.value = value
    }

    fun updateLocation(newLocation : LocationData){
        _location.value = newLocation

    }
    fun updateAddress(newAddress : String){
        _address.value = newAddress

    }
    fun getCoordinates(
        cityWithCountry: String,
        limit: Int = 1,
        apiKey: String,
        weatherViewModel: WeatherViewModel,
        locationUtils: LocationUtils
    ){
        viewModelScope.launch {
            try {

                val response = ApiService.getCoordinates(cityWithCountry, limit, apiKey)
                val locationData = LocationData(response[0].lat,response[0].lon)
                updateLocation(locationData)
                setGPSLocation(false)
                locationUtils.stopLocationUpdates()
                Log.d("TAGGGG","${locationData.latitude} ${locationData.longitude}")
                weatherViewModel.fetchCurrentWeater(
                    locationData.latitude,
                    locationData.longitude,
                    apiKey
                )

            }catch (e: Exception){

            }
        }
    }
}


