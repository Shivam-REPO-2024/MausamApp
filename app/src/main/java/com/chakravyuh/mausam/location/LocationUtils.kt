package com.chakravyuh.mausam.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.chakravyuh.mausam.model.LocationData
import com.chakravyuh.mausam.viewModels.LocationViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng

import java.util.Locale


class LocationUtils(val context: Context) {

    private val _fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)


    private var locationCallback: LocationCallback? = null
    @Suppress("MissingPermission")
    fun requestLocationUpdates(viewModel: LocationViewModel){
         locationCallback = object: LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let {
                    val location = LocationData(it.latitude, it.longitude)
                    viewModel.updateLocation(location)
                }
            }
        }

        val locationRequest = com.google.android.gms.location.LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,1000)
            .build()
        _fusedLocationClient.requestLocationUpdates(locationRequest,locationCallback!!, Looper.getMainLooper())


    }
    fun stopLocationUpdates() {
        locationCallback?.let {
            _fusedLocationClient.removeLocationUpdates(it)
        }
        locationCallback = null
    }

    fun hasLocationPermission(context:Context): Boolean{

        return ContextCompat.checkSelfPermission(context,

            Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED
                &&
                ContextCompat.checkSelfPermission(context,

                    Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED


    }

    fun reverseGeoCodeLocation(location: LocationData) :String?{
        val geocoder = Geocoder(context, Locale.getDefault())
        val coordinates = LatLng(location.latitude,location.longitude)
        val addresses : MutableList<Address>? = geocoder.getFromLocation(coordinates.latitude,coordinates.longitude,1)

//        print(addresses)
//        Log.d("TAGGG","Permission Granted : ${addresses}")
        return if (addresses?.isNotEmpty() == true){
            "${addresses[0].locality}, ${addresses[0].adminArea}, ${addresses[0].countryName}"
        }else{
            null
        }
    }
}