package com.chakravyuh.mausam

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.chakravyuh.mausam.Screens.HomeScreen
import com.chakravyuh.mausam.location.LocationUtils

import com.chakravyuh.mausam.ui.theme.MausamTheme
import com.chakravyuh.mausam.viewModels.LocationViewModel
import androidx.compose.runtime.collectAsState
import androidx.core.app.ActivityCompat
import com.chakravyuh.mausam.navigation.Navigation
import com.chakravyuh.mausam.viewModels.WeatherViewModel
import kotlinx.coroutines.delay
import kotlin.math.log

class MainActivity : ComponentActivity() {
    val locationViewModel = LocationViewModel()
    val weatherViewMode = WeatherViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val localContext = LocalContext.current

            val locationUtils = LocationUtils(localContext)

            val locationPermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestMultiplePermissions(),
                onResult = {
                        permission ->
                    if (permission[Manifest.permission.ACCESS_COARSE_LOCATION] == true && permission[Manifest.permission.ACCESS_FINE_LOCATION]==true){
                        // i have the permission
//                locationUtils.requestLocationUpdates(viewModel)
                    }
                    else{
                        // i dont have the permission
                        val rationaleRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                            localContext as MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                                || ActivityCompat.shouldShowRequestPermissionRationale(
                            localContext as MainActivity,
                            Manifest.permission.ACCESS_COARSE_LOCATION)

                        if (rationaleRequired){
                            Toast.makeText(localContext, "Location Permission Required", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(localContext, "Location Permission Denied, please enable in the settings app ", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
            MausamTheme {

                val location = locationViewModel.location.collectAsState().value
                LaunchedEffect(Unit) {
                    delay(100)
                    if(locationUtils.hasLocationPermission(localContext)){

                            if(locationViewModel.isGPSLocation.value) {
                                locationUtils.requestLocationUpdates(locationViewModel)
                            }

                    } else {
                        locationPermissionLauncher.launch(arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ))
                    }
                }
                Navigation(context = localContext,
                    locationUtils = locationUtils,
                    locationViewModel = locationViewModel,
                    weatherViewModel = weatherViewMode
                    )


//                HomeScreen(context = localContext,
//                    locationviewModel =  locationViewModel,
//                    locationUtils = locationUtils,
//                    weatherViewModel = weatherViewMode
//                    )
            }
        }
    }
}

