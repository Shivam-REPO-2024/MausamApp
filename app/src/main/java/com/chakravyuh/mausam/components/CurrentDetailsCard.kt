package com.chakravyuh.mausam.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chakravyuh.mausam.shimmer.LoadTextShimmer

@Composable
fun CurrentWeatherDetailsCard(
    image: Int,
    value: Double? =null,
    title: String,
    gust: Double

    ) {
    Card (
        modifier = Modifier.padding(10.dp).widthIn(max = 90.dp).heightIn(max = 210.dp, min = 210.dp ),
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(87, 65, 210, 168)
        )


    ){

        Column (
            modifier = Modifier.padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){

            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            Image(
                painter = painterResource(image),
                contentDescription = "Clear Sky",
                modifier = Modifier.size(80.dp).padding(5.dp)

            )

            Spacer(modifier = Modifier.padding(vertical = 2.dp))

            if (value ==null){
                LoadTextShimmer()
            }else
            {
                if (title.contains("Humidity")){
                    Text(
                        text = "$value%",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White,
                        modifier=Modifier.padding(2.dp),


                        )
                }
                else if(title.contains("Visibility")){

                    Text(
                        text = "${value/1000} Km",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White,
                        modifier=Modifier.padding(2.dp),


                        )
                }
                else if(title.contains("Wind Speed")){
                    val windSpeed = (value*gust)
                    val formatted = String.format("%.2f", windSpeed)
                    Text(
                        text = "$formatted Km/h",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White,
                        modifier=Modifier.padding(2.dp),


                        )
                }

            }
            Spacer(modifier = Modifier.padding(vertical = 2.dp))

            Text(
                text =title,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                modifier=Modifier.padding(horizontal = 6.dp),


                )
            Spacer(modifier = Modifier.padding(vertical = 2.dp))
        }

    }

}

