package com.chakravyuh.mausam.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chakravyuh.mausam.Utilitis.getFormattedDate
import com.chakravyuh.mausam.components.AppBar
import com.chakravyuh.mausam.components.OtherDaysWeatherCard
import com.chakravyuh.mausam.components.WeatherCard2
import com.chakravyuh.mausam.shimmer.LoadTextShimmer
import com.chakravyuh.mausam.viewModels.LocationViewModel
import com.chakravyuh.mausam.viewModels.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WeeklyReportScreen(navController : NavController,
                       weatherViewModel: WeatherViewModel,
                       locationViewModel: LocationViewModel
                       ) {

    val weatherResponse = weatherViewModel.weatherResponseState.collectAsState().value
    val weatherMain = weatherResponse?.currentWeatherData?.weather[0]
    val currentWeatherResponse= weatherResponse?.currentWeatherData
    val location = locationViewModel.address.collectAsState().value

    val forecastToday = weatherViewModel.weatherForecastResponseState.collectAsState().value?.WeatherForecastData?.list




    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            AppBar("Weekly Report",{
                navController.navigateUp()
            })
        }
    ) { innerpadding ->

        Column(
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
        ) {
            Spacer(Modifier.padding(10.dp))
            Text(
                text = "Next 7 Days",
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),

                )
            Spacer(Modifier.padding(2.dp))

            val formattedDate = currentWeatherResponse?.let {
                getFormattedDate(it)
            }
            val formattedDateList = formattedDate?.split(",")
            val day = formattedDateList?.get(0)
            val addressList = location?.split(",")
            val city = addressList?.get(0)?.trim()
            val state = addressList?.get(1)?.trim()

            WeatherCard2(
                icon = currentWeatherResponse?.weather[0]?.icon,
                day = day,
                weather = currentWeatherResponse?.weather[0]?.description,
               city = city,
                state = state,
                weatherViewModel = weatherViewModel
            )
            Spacer(Modifier.padding(1.dp))
            
            Spacer(Modifier.padding(1.dp))

            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(Date()) // today's date like "2025-10-16"


            val FiveDaysForecasts = forecastToday?.filterNot {
                it.dt_txt.startsWith(today)
            }?.filter { it.dt_txt.contains("12:00:00") }

           FiveDaysForecasts?.forEach {
               println(it.dt_txt)
           }
            LazyColumn (
                modifier = Modifier
            ){
                if (FiveDaysForecasts == null || FiveDaysForecasts.isEmpty()){

                    items(5){
                        LoadTextShimmer()
                    }
                }else{

                    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    val outputFormat = SimpleDateFormat("EEEE", Locale.getDefault()) // gives full day name like "Saturday"


                    items(FiveDaysForecasts?:emptyList()) {
                        val date = inputFormat.parse(it.dt_txt)
                        val dayName = outputFormat.format(date?:Date())
                        val feels_like = it.main.feels_like
                        val temp = (feels_like.minus(273.15)).toInt()


                    OtherDaysWeatherCard(
                        icon = it.weather[0].icon,
                        day = dayName,
                        weather = it.weather[0].description,
                        temp = temp


                    )
                }

                }
            }


        }

    }
}
@Preview(showBackground = true)
@Composable
private fun WeeklyReportScreenPreview() {




}