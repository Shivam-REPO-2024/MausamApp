package com.chakravyuh.mausam.Screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chakravyuh.mausam.components.AppBar
import com.chakravyuh.mausam.components.TdWeatherDetail
import com.chakravyuh.mausam.components.WeatherCard
import com.chakravyuh.mausam.location.LocationUtils
import com.chakravyuh.mausam.viewModels.LocationViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import com.chakravyuh.mausam.Utilitis.Apikey
import com.chakravyuh.mausam.Utilitis.formatTimeTo12Hour
import com.chakravyuh.mausam.Utilitis.getFormattedDate
import com.chakravyuh.mausam.components.SearchBar
import com.chakravyuh.mausam.navigation.Screens

import com.chakravyuh.mausam.shimmer.LoadTextShimmer
import com.chakravyuh.mausam.viewModels.WeatherViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    context: Context,
    locationUtils: LocationUtils,
    locationviewModel: LocationViewModel,
    weatherViewModel: WeatherViewModel,
    navController: NavController

               ) {

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
    val scope = rememberCoroutineScope()

    val location = locationviewModel.location.collectAsState().value
    val weatherResponse = weatherViewModel.weatherResponseState.collectAsState().value
    val weatherMain = weatherResponse?.currentWeatherData?.weather[0]
    val currentWeatherResponse= weatherResponse?.currentWeatherData

            val timeZone=currentWeatherResponse?.timezone
            val timestamp = currentWeatherResponse?.dt




//    Log.d("TAGGG","Permission Granted : ${location}")

    val address = location?.let{
        locationUtils.reverseGeoCodeLocation(location)

    }
    val forecastToday = weatherViewModel.weatherForecastResponseState.collectAsState().value?.WeatherForecastData?.list
    val isLoading = weatherViewModel.weatherForecastResponseState.collectAsState().value?.isLoading
    val isLoadingCard = weatherViewModel.weatherResponseState.collectAsState().value?.isLoading

//    Log.d("TAGGG","Permission Granted : ${location}")

    LaunchedEffect(location) {

        location?.let {
            weatherViewModel.fetchCurrentWeater(location.latitude,location.longitude,"Enter Your APIKEY")
            weatherViewModel.fetchWeatherForecast(locationviewModel,"Enter Your APIKEY")
        }

    }
    Log.d("WEATHER",
        "Weather Response : ${weatherViewModel.weatherForecastResponseState.collectAsState().value?.WeatherForecastData}")

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onItemSelected = { onItemSelected ->
                    when(onItemSelected){
                        "Close" ->{scope.launch { drawerState.close() }}
                    }
                },
                drawerState,
                scope,
                locationviewModel,
                weatherViewModel,
                locationUtils

            )


        }
    ) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            AppBar("Home",{
                scope.launch {
                    drawerState.open()
                }
            })
        }
    ){ innerpadding->



        Column (


            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(22, 4, 135, 225),
                            Color(50, 65, 113, 232),

                        )
                    )
                )
        ){

            Spacer(modifier.padding(3.dp))

            if(isLoadingCard == true){
                println("LOading : "+isLoadingCard)
                LoadTextShimmer()
            }else{
                if (address!=null){
                    locationviewModel.updateAddress(address)
                    println("LOading : "+isLoadingCard)
                    Text(
                        text = address,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Left,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        color = Color.White,
                        modifier=Modifier.fillMaxWidth().padding(horizontal = 15.dp),

                        )
                }
                else{
                    Text(
                        text = "Address Not Found !!",
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Left,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        color = Color.White,
                        modifier=Modifier.fillMaxWidth().padding(horizontal = 15.dp),

                        )
                }
            }

//            if (location != null){
//                if (address!=null){
//                    locationviewModel.updateAddress(address)
//                    Text(
//                        text = address,
//                        fontWeight = FontWeight.Medium,
//                        textAlign = TextAlign.Left,
//                        fontSize = 20.sp,
//                        fontFamily = FontFamily.Serif,
//                        color = Color.White,
//                        modifier=Modifier.fillMaxWidth().padding(horizontal = 15.dp),
//
//                        )
//                }
//                else{
//                    Text(
//                        text = "Address Not Found !!",
//                        fontWeight = FontWeight.Medium,
//                        textAlign = TextAlign.Left,
//                        fontSize = 20.sp,
//                        fontFamily = FontFamily.Serif,
//                        color = Color.White,
//                        modifier=Modifier.fillMaxWidth().padding(horizontal = 15.dp),
//
//                        )
//                }
//            }else{
//                LoadTextShimmer()
//
//            }

            Spacer(modifier.padding(2.dp))


            val formatedDate = currentWeatherResponse?.let {
                getFormattedDate(it)
            }
                WeatherCard(
                    isLoadingCard = isLoadingCard == true,
                    weatherMain?.icon,
                    description = weatherMain?.description,
                    datetime =  formatedDate,
                    temp = currentWeatherResponse?.main?.feels_like,
                    humidity = currentWeatherResponse?.main?.humidity,
                    visibility = currentWeatherResponse?.visibility?.toDouble(),
                    windspeed = currentWeatherResponse?.wind?.speed?.toDouble(),
                    gust = currentWeatherResponse?.wind?.gust?.toDouble(),

                )
            Spacer(modifier.padding(1.dp))
            Row (
                modifier=Modifier.fillMaxWidth()
                    ,
                horizontalArrangement = Arrangement.SpaceBetween,

            ){
                Text(
                    text ="Today",
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Left,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White,
                    modifier=Modifier.padding(horizontal = 15.dp),

                    )
                Text(
                    text ="Next Week",
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Left,
                    fontSize = 17.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White,
                    modifier=Modifier.padding(horizontal = 15.dp).clickable(onClick = {
                        location?.let {
                            navController.navigate(Screens.WeeklyReportScreen.route)
                        }

                    }),

                    )
            }

            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(Date()) // today's date like "2025-10-16"

            val todayForecasts = forecastToday?.filter {
                it.dt_txt.startsWith(today)
            }



            todayForecasts?.forEach {
                println("${it.dt_txt} -> ${it.main.feels_like-273.15} -> ${it.weather[0].icon}")
            }

//            forecastToday?.forEach {
//                item ->
//                println(item.dt_txt)
//            }
            Spacer(modifier.padding(1.dp))
            LazyRow (
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){


                if(isLoading ==true || todayForecasts.isNullOrEmpty()){
                    items(5){

                        LoadTextShimmer(
                            modifier = Modifier
                                .size(130.dp).padding(10.dp).widthIn(min=120.dp,max = 120.dp).heightIn(min=125.dp, max=125.dp)
                                .clip(RoundedCornerShape(30.dp)))

                    }

                }else{
                items(todayForecasts?:emptyList()){
                    val date = it.dt_txt.split(" ")
                    val feels_like = it.main.feels_like
                    val temp = (feels_like.minus(273.15)).toInt()
                    val icon = it.weather[0].icon
                    val time = formatTimeTo12Hour(date[1].toString())

                        TdWeatherDetail(
                            time = time,
                            image = icon,
                            temp = " ${temp}"
                        )



                }
                }
            }



        }

    }
    }



}

@Composable
fun DrawerContent(onItemSelected: (String) -> Unit,
                  drawerState: DrawerState,
                  scope: CoroutineScope,
                  locationViewModel: LocationViewModel,
                  weatherViewModel: WeatherViewModel,
                  locationUtils: LocationUtils
                  ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x6F333379))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(17.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("MAUSAM", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold
                ,
                modifier = Modifier.padding(top = 20.dp),
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.width(10.dp))
            IconButton(
                onClick = {onItemSelected("Close") }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
        }
        Spacer(Modifier.height(30.dp))

        SearchBar(
            onSearch = { query->
                onItemSelected(query)
                locationViewModel.getCoordinates(
                    cityWithCountry = query,
                    limit = 1,
                    apiKey = Apikey,
                    weatherViewModel = weatherViewModel,
                    locationUtils
                )
                locationViewModel.setGPSLocation(false)
                locationUtils.stopLocationUpdates()
                println(locationViewModel.location.value)
                scope.launch {
                    drawerState.close()
                }
            },
            drawerState = drawerState
        )
        Spacer(Modifier.height(25.dp))

        OutlinedButton(
            onClick = {
                locationViewModel.setGPSLocation(true)
                locationUtils.requestLocationUpdates(locationViewModel) // start GPS updates


                scope.launch {
                    drawerState.close()
                }
            }
        ) {
            Text("Use Current Location", color = Color.White)
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
}
