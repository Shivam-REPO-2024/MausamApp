package com.chakravyuh.mausam.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chakravyuh.mausam.Utilitis.GetIconResId
import com.chakravyuh.mausam.viewModels.WeatherViewModel
import okhttp3.Address

@Composable
fun WeatherCard2(
    modifier: Modifier = Modifier,
    icon: String? = null,
    day: String? = null,
    weather : String? = null,
    city: String?=null,
    state: String?=null,
    weatherViewModel: WeatherViewModel
                 ) {

    val weatherResponse = weatherViewModel.weatherResponseState.collectAsState().value

    Card(
        modifier = Modifier.fillMaxWidth().padding(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(30.dp),

        ){
        Column (modifier = Modifier
            .padding()
            .fillMaxWidth()
            .background(Brush.verticalGradient(
                listOf(
                    Color(0, 0, 0, 255),
                    Color(0, 0, 0, 20),

                    )
            )),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ){

                Column{
                    Text(
                        text ="$day",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White,
                        modifier=Modifier.padding(horizontal = 15.dp),

                        )
                    Spacer(modifier = Modifier.padding(2.dp))

                    Text(
                        text ="$weather",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Left,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.LightGray,
                        modifier=Modifier.padding(horizontal = 15.dp),


                        )

                }


                Image(
                    painter = painterResource(GetIconResId(icon.toString())),
                    contentDescription = "Clear Sky",
                    modifier = Modifier.size(130.dp).padding(vertical = 15.dp)

                )
            }

            val humidity = weatherResponse?.currentWeatherData?.main?.humidity
            val cloudiness = weatherResponse?.currentWeatherData?.clouds?.all
            val pressure = weatherResponse?.currentWeatherData?.main?.pressure



            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,


            ){
                SmallDetails("$humidity%", "Humidity")
                SmallDetails("$cloudiness%", "Cloudy")
                SmallDetails("$pressure hPa", "Pressure")
                SmallDetails("$city", "$state")
            }

            Spacer(modifier.padding(7.dp))


        }


    }

}