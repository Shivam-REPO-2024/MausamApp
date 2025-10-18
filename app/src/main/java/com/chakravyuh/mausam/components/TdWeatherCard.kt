package com.chakravyuh.mausam.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.chakravyuh.mausam.Utilitis.GetIconResId

@Composable
fun TdWeatherDetail(modifier: Modifier = Modifier,
                    image: String?=null,
                    time:String,
                    temp:String,
                    ) {
    Card (
        modifier = Modifier.padding(10.dp).widthIn(max = 90.dp)
            .heightIn(min=125.dp, max=125.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
//                Color(87, 65, 210, 168)
        )


    ){

        Column (
            modifier = Modifier
                .background(
                    Brush.linearGradient(listOf(
                        Color(87, 65, 210, 168),
                        Color(12, 27, 75, 255),

                    ),
                        start = Offset.Zero
                        )
                )
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            Spacer(modifier = Modifier.padding(vertical = 2.dp))

            Image(
                painter = painterResource(GetIconResId(image.toString())),
                contentDescription = "Clear Sky",
                modifier = Modifier.size(60.dp).padding(2.dp)

            )

            Spacer(modifier = Modifier.padding(vertical = 1.dp))

            Text(
                text = "$time",
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                fontSize = 13.sp,
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                modifier=Modifier.padding(horizontal = 3.dp),


                )
            Spacer(modifier = Modifier.padding(vertical = 2.dp))

            Text(
                text ="$temp°",
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                fontSize = 13.sp,
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                modifier=Modifier.padding(horizontal = 6.dp),


                )
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
        }

    }
}