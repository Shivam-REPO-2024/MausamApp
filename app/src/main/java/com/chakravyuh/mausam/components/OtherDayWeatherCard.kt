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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chakravyuh.mausam.R
import com.chakravyuh.mausam.Utilitis.GetIconResId
import com.chakravyuh.mausam.model.WeatherItem

@Composable
fun OtherDaysWeatherCard(
    icon:String?=null,
    day: String? = null,
    weather: String? = null,
    temp: Int? = null,

) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(50.dp),

        ){
        Column (modifier = Modifier
            .padding()
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    listOf(
                        Color(0, 0, 0, 117),
                        Color(0, 0, 0, 54),                    )
                )
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {

            Row (
                modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ){
                Column{
                    Text(
                        text ="$day",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White,
                        modifier=Modifier.padding(horizontal = 15.dp),

                        )
                    Spacer(modifier = Modifier.padding(2.dp))

                    Text(
                        text ="$weather",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Left,
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.LightGray,
                        modifier=Modifier.padding(horizontal = 15.dp),


                        )

                }


                Row (
                    modifier = Modifier.padding(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ){
                    Image(
                        painter = painterResource(GetIconResId(icon.toString())),
                        contentDescription = "Clear Sky",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(vertical = 10.dp)

                    )
                    Text(
                        text ="$temp°",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Left,
                        fontSize = 22.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.LightGray,
                        modifier=Modifier.padding(end = 10.dp),


                        )

                }

            }










        }


    }
}

