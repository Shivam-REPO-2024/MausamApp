package com.chakravyuh.mausam.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
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
import com.chakravyuh.mausam.R
import com.chakravyuh.mausam.Utilitis.GetIconResId
import com.chakravyuh.mausam.shimmer.LoadCircularShimmer
import com.chakravyuh.mausam.shimmer.LoadTextShimmer


@Composable
fun WeatherCard(
    isLoadingCard: Boolean,
    icon: String? =null,
    description: String? = "weather description",
    datetime: String? = null,
    temp: Double? = null,
    humidity: Int? =null,
    visibility: Double? = null,
    windspeed: Double? = null,
    gust: Double?


) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(25.dp),
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

            if (isLoadingCard){
                LoadCircularShimmer()
            }else{

                Image(
                    painter = painterResource(GetIconResId(icon.toString())),
                    contentDescription = "Clear Sky",
                    modifier = Modifier.size(130.dp).padding(vertical = 15.dp)

                )


            }
            Spacer(modifier = Modifier.padding(2.dp))

            if (isLoadingCard){
                LoadTextShimmer(modifier = Modifier.padding(horizontal = 30.dp))

            }else{

                Text(
                    text ="$description",
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White,
                    modifier=Modifier.fillMaxWidth().padding(horizontal = 15.dp),

                    )

            }
            Spacer(modifier = Modifier.padding(5.dp))


                if (isLoadingCard) {
                    LoadTextShimmer(
                        modifier = Modifier.padding(horizontal = 10.dp)
                            .heightIn(max= 20.dp).widthIn(max = 170.dp)
                    )
                }else{

                    Text(
                        text = "$datetime",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.LightGray,
                        modifier=Modifier.fillMaxWidth().padding(horizontal = 15.dp),


                        )

                }


            Spacer(modifier = Modifier.padding(10.dp))

            if(isLoadingCard){
                LoadTextShimmer(
                    modifier = Modifier.padding(horizontal = 10.dp)
                        .heightIn(max= 30.dp).widthIn(max = 100.dp)
                )
            }
            else{

                val tempCelsius = (temp?.minus(273.15))?.toInt()
//                Log.d("SUMM","$tempCelsius")


                Text(
                    text =" ${tempCelsius}°",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 60.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.LightGray,
                    modifier=Modifier.fillMaxWidth().padding(horizontal = 15.dp),


                    )
            }
            Spacer(modifier = Modifier.padding(2.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ){
                CurrentWeatherDetailsCard(
                    image = R.drawable.humidity,
                    value = humidity?.toDouble(),
                    title = "Humidity",
                    0.0

                )
                CurrentWeatherDetailsCard(
                    image = R.drawable.fog,
                    value = visibility,
                    title = "Visibility",
                    0.0

                )
                CurrentWeatherDetailsCard(
                    image = R.drawable.windsock,
                    value = windspeed,
                    title = "Wind Speed",
                    gust = gust?.toDouble() ?: 1.0

                )
            }


            Spacer(modifier = Modifier.padding(7.dp))

        }


    }
}